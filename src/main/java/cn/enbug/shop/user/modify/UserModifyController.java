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

package cn.enbug.shop.user.modify;

import cn.enbug.shop.common.controller.BaseController;
import cn.enbug.shop.common.interceptor.NeedLogInInterceptor;
import cn.enbug.shop.common.kit.RedisKit;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.POST;

import java.math.BigDecimal;

/**
 * @author Yang Zhizhuang
 * @version 1.0.3
 * @since 1.0.0
 */
@Before({POST.class, NeedLogInInterceptor.class})
public class UserModifyController extends BaseController {

    public void avator() {
        renderJson(UserModifyService.ME.setAvator(getCookie(RedisKit.COOKIE_ID), getPara("avator")));
    }

    public void addaddress() {
        renderJson(UserModifyService.ME.addAddress(getCookie(RedisKit.COOKIE_ID), getPara("name"),
                getPara("phone"), getPara("address")));
    }

    public void charge() {
        renderJson(UserModifyService.ME.charge(getCookie(RedisKit.COOKIE_ID), new BigDecimal(getPara("value"))));
    }

    public void defaultaddress() {
        renderJson(UserModifyService.ME.setDefaultAddress(getCookie(RedisKit.COOKIE_ID), getParaToInt("id")));
    }

    public void bindphone() {
        renderJson(UserModifyService.ME.bindPhone(getCookie(RedisKit.COOKIE_ID), getPara("phone"), getPara("phone_captcha")));
    }

    public void bindemail() {
        renderJson(UserModifyService.ME.bindEmail(getCookie(RedisKit.COOKIE_ID), getPara("email")));
    }

    @Clear(POST.class)
    public void active() {
        UserModifyService.ME.activeEmail(getPara());
        redirect("/user/center");

    }

}
