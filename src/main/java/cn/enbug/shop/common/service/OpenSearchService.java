/*
 * Copyright (c) 2017 EnBug Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.enbug.shop.common.service;

import com.aliyun.opensearch.CloudsearchClient;
import com.aliyun.opensearch.CloudsearchDoc;
import com.aliyun.opensearch.CloudsearchSearch;
import com.aliyun.opensearch.CloudsearchSuggest;
import com.aliyun.opensearch.object.KeyTypeEnum;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yang Zhizhuang
 * @version 1.0.1
 * @since 1.0.0
 */
public class OpenSearchService {

    /**
     * singleton
     */
    public static final OpenSearchService ME = new OpenSearchService();
    private static final Logger LOG = LoggerFactory.getLogger(OpenSearchService.class);
    private static final String INDEX_NAME = PropKit.get("openSearcher.appName");
    private static final String TABLE_NAME = "main";
    private static CloudsearchClient client;
    Map<String, Object> opts = new HashMap<String, Object>();

    private OpenSearchService() {
        try {
            client = new CloudsearchClient(RsaService.ME.decrypt(PropKit.get("openSearcher.key")),
                    RsaService.ME.decrypt(PropKit.get("openSearcher.secret")),
                    PropKit.get("openSearcher.host"),
                    opts,
                    KeyTypeEnum.ALIYUN);
        } catch (UnknownHostException e) {
            LOG.error(e.toString(), e);
        }
    }

    private boolean isSuccess(String json) {
        HashMap map = JsonKit.parse(json, HashMap.class);
        return map.get("status").toString().equalsIgnoreCase("ok");
    }

    private ArrayList handleSearch(String json) {
        HashMap jMap = JsonKit.parse(json, HashMap.class);
        if (!jMap.get("status").toString().equalsIgnoreCase("OK")) {
            return null;
        }
        HashMap result = JsonKit.parse(jMap.get("result").toString(), HashMap.class);
        if (Integer.parseInt(result.get("num").toString()) == 0) {
            return null;
        }
        return JsonKit.parse(result.get("items").toString(), ArrayList.class);
    }

    /**
     * push
     *
     * @param data OpenSearchPushRequestBuilder.build
     * @return String
     * @throws IOException IOException
     */
    public boolean push(String data) throws IOException {
        CloudsearchDoc doc = new CloudsearchDoc(INDEX_NAME, client);
        return isSuccess(doc.push(data, TABLE_NAME));
    }

    /**
     * add
     *
     * @param fields hash map
     * @return String
     * @throws IOException IOException
     */
    public boolean add(HashMap<String, Object> fields) throws IOException {
        CloudsearchDoc doc = new CloudsearchDoc(INDEX_NAME, client);
        doc.add(fields);
        return isSuccess(doc.push(TABLE_NAME));
    }

    /**
     * update
     *
     * @param fields hash map
     * @return String
     * @throws IOException IOException
     */
    public boolean update(HashMap<String, Object> fields) throws IOException {
        CloudsearchDoc doc = new CloudsearchDoc(INDEX_NAME, client);
        doc.update(fields);
        return isSuccess(doc.push(TABLE_NAME));
    }

    /**
     * search
     *
     * @param index            index type
     * @param keyWord          key word
     * @param filter           filter
     * @param sortKey          sort key
     * @param positiveSequence positive sequence
     * @return ArrayList
     * @throws IOException IOException
     */
    public ArrayList search(String index, String keyWord, String filter, String sortKey, boolean positiveSequence) throws IOException {
        CloudsearchSearch search = new CloudsearchSearch(client);
        // 添加指定搜索的应用：
        search.addIndex(INDEX_NAME);
        // 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引到”字段。）
        if (StrKit.isBlank(index)) {
            search.setQueryString("'" + keyWord + "'");
        } else {
            search.setQueryString(index + ":'" + keyWord + "'");
        }
        // 指定搜索返回的格式。
        search.setFormat("json");
        // 设定过滤条件
        search.addFilter(filter);
        // 设定排序方式 + 表示正序 - 表示降序
        search.addSort(sortKey, positiveSequence ? "+" : "-");
        // 返回搜索结果
        return handleSearch(search.search());
    }

    /**
     * search
     *
     * @param index            index type
     * @param keyWord          key word
     * @param sortKey          sort key
     * @param positiveSequence positive sequence
     * @return ArrayList
     * @throws IOException IOException
     */
    public ArrayList search(String index, String keyWord, String sortKey, boolean positiveSequence) throws IOException {
        CloudsearchSearch search = new CloudsearchSearch(client);
        // 添加指定搜索的应用：
        search.addIndex(INDEX_NAME);
        // 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引到”字段。）
        if (StrKit.isBlank(index)) {
            search.setQueryString("'" + keyWord + "'");
        } else {
            search.setQueryString(index + ":'" + keyWord + "'");
        }
        // 指定搜索返回的格式。
        search.setFormat("json");
        // 设定排序方式 + 表示正序 - 表示降序
        search.addSort(sortKey, positiveSequence ? "+" : "-");
        // 返回搜索结果
        return handleSearch(search.search());
    }

    /**
     * search
     *
     * @param index   index type
     * @param keyWord key word
     * @param filter  filter
     * @return ArrayList
     * @throws IOException IOException
     */
    public ArrayList search(String index, String keyWord, String filter) throws IOException {
        CloudsearchSearch search = new CloudsearchSearch(client);
        // 添加指定搜索的应用：
        search.addIndex(INDEX_NAME);
        // 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引到”字段。）
        if (StrKit.isBlank(index)) {
            search.setQueryString("'" + keyWord + "'");
        } else {
            search.setQueryString(index + ":'" + keyWord + "'");
        }
        // 指定搜索返回的格式。
        search.setFormat("json");
        // 设定过滤条件
        search.addFilter(filter);
        // 返回搜索结果
        return handleSearch(search.search());
    }

    /**
     * search
     *
     * @param index   index type
     * @param keyWord key word
     * @return ArrayList
     * @throws IOException IOException
     */
    public ArrayList search(String index, String keyWord) throws IOException {
        CloudsearchSearch search = new CloudsearchSearch(client);
        // 添加指定搜索的应用：
        search.addIndex(INDEX_NAME);
        // 指定搜索的关键词，这里要指定在哪个索引上搜索，如果不指定的话默认在使用“default”索引（索引字段名称是您在您的数据结构中的“索引到”字段。）
        if (StrKit.isBlank(index)) {
            search.setQueryString("'" + keyWord + "'");
        } else {
            search.setQueryString(index + ":'" + keyWord + "'");
        }
        // 指定搜索返回的格式。
        search.setFormat("json");
        // 返回搜索结果
        return handleSearch(search.search());
    }

    /**
     * search suggestions
     *
     * @param suggestName suggest name
     * @param keyWord     key word
     * @return ArrayList&lt;Map&lt;"suggestions",value&gt;
     * @throws IOException IOException
     */
    public String suggest(String suggestName, String keyWord) throws IOException {
        CloudsearchSuggest suggest = new CloudsearchSuggest(INDEX_NAME,
                suggestName, client);

        suggest.setHit(5);
        suggest.setQuery(keyWord);
        String result = suggest.search();
        HashMap jsonResult = JsonKit.parse(result, HashMap.class);
        if (jsonResult.containsKey("errors")) {
            return null;
        }
        return jsonResult.get("suggestions").toString();
        // arrayList<String,Map> --> Map<"suggestions",value>
    }

}
