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
 * limitations under the License.
 */

package cn.enbug.shop.shop.good;

import cn.enbug.shop.captcha.ImageCaptchaValidator;
import cn.enbug.shop.common.controller.BaseController;
import cn.enbug.shop.common.kit.RedisKit;
import cn.enbug.shop.shop.HasShopInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;

/**
 * @author Yang Zhizhuang
 * @version 1.0.1
 * @since 1.0.0
 */
@Before(HasShopInterceptor.class)
public class GoodAdminController extends BaseController {

    @Before(GET.class)
    public void index() {
        render("index.html");
    }

    @Before(GET.class)
    public void newGood() {
        render("newGood.html");
    }

    @Clear(HasShopInterceptor.class)
    @Before({POST.class, ImageCaptchaValidator.class})
    public void add() {
        String token = getCookie(RedisKit.TOKEN);
        String name = getPara("name");
        String description = getPara("description");
        String ip = getIp();
        double price = getParaToDouble("price", 0.0);
        int number = getParaToInt("number", 0);
        String avator = getPara("avator", "good/default.jpg");
        renderJson(GoodAdminService.ME.add(token, ip, name, description, price, avator, number));
    }

}
