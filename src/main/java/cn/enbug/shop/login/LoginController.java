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

package cn.enbug.shop.login;

import cn.enbug.shop.captcha.ImageCaptchaValidator;
import cn.enbug.shop.common.controller.BaseController;
import cn.enbug.shop.common.interceptor.LoginInterceptor;
import cn.enbug.shop.common.kit.RedisKit;
import cn.enbug.shop.common.kit.Ret;
import cn.enbug.shop.common.kit.UrlKit;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.ext.interceptor.POST;

/**
 * 登录
 *
 * @author Hu Wenqiang
 * @author Yang Zhizhuang
 * @version 1.0.11
 * @since 1.0.0
 */
@Before({NoUrlPara.class, LoginInterceptor.class})
public class LoginController extends BaseController {

    private static final LoginService srv = LoginService.me;

    /**
     * 登录页面
     */
    @Before(GET.class)
    public void index() {
        render("index.html");
    }

    /**
     * 用户名登录
     */
    @Before({POST.class, ImageCaptchaValidator.class, UsernameLoginValidator.class})
    public void username() {
        String username = getPara("username");
        String password = getPara("pwd");
        String ip = getIp();
        Ret ret = srv.loginByUsername(username, password, ip);
        if (ret.isSucceed()) {
            String token = ret.getAs(RedisKit.COOKIE_ID);
            setCookie(RedisKit.COOKIE_ID, token, 60 * 60);
        }
        renderJson(ret);
    }

    /**
     * 手机号登录
     */
    @Before({POST.class, PhoneLoginValidator.class})
    public void phone() {
        String phone = getPara("phone");
        String ip = getIp();
        Ret ret = srv.loginByPhone(phone, ip);
        if (ret.isSucceed()) {
            String token = ret.getAs(RedisKit.COOKIE_ID);
            setCookie(RedisKit.COOKIE_ID, token, 60 * 60);
        }
        renderJson(ret);
    }

    /**
     * 邮箱登录
     */
    @Before({POST.class, ImageCaptchaValidator.class, EmailLoginValidator.class})
    public void email() {
        String email = getPara("email");
        String password = getPara("pwd");
        String ip = getIp();
        Ret ret = srv.loginByEmail(email, password, ip);
        if (ret.isSucceed()) {
            String token = ret.getAs(RedisKit.COOKIE_ID);
            setCookie(RedisKit.COOKIE_ID, token, 60 * 60);
        }
        renderJson(ret);
    }

    @ActionKey("/logout")
    @Clear(LoginInterceptor.class)
    @Before(GET.class)
    public void logout() {
        RedisKit.delToken(getCookie(RedisKit.TOKEN));
        removeCookie(RedisKit.COOKIE_ID);
        String url = UrlKit.decode(getRequest().getQueryString(), "utf-8");
        int index = url.indexOf('?');
        if (index != -1) {
            url = url.substring(0, index);
        }
        redirect(url);
    }

}
