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

package cn.enbug.shop.good;

import cn.enbug.shop.common.controller.BaseController;
import cn.enbug.shop.common.interceptor.UserInterceptor;
import cn.enbug.shop.common.model.Good;
import cn.enbug.shop.common.service.CommentService;
import cn.enbug.shop.common.service.GoodService;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.GET;

/**
 * @author Yang Zhizhuang
 * @version 1.0.0
 * @since 1.0.0
 */
@Before({GET.class, UserInterceptor.class, GoodValidator.class})
public class GoodController extends BaseController {

    public void index() {
        String uuid = getPara();
        Good good = GoodService.ME.findGoodByUuid(uuid);
        if (null == good) {
            redirect("/search");
            return;
        }
        setAttr("comments", CommentService.ME.getCommentRecordByGoodId(good.getId()));
        setAttr("good", good);
        render("index.html");
    }

}
