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

package cn.enbug.shop.common;

import cn.enbug.shop.captcha.CaptchaController;
import cn.enbug.shop.forget.ForgetController;
import cn.enbug.shop.good.GoodController;
import cn.enbug.shop.index.IndexController;
import cn.enbug.shop.login.LoginController;
import cn.enbug.shop.order.OrderController;
import cn.enbug.shop.register.RegisterController;
import cn.enbug.shop.search.SearchController;
import cn.enbug.shop.shop.all.ShopAllController;
import cn.enbug.shop.shop.center.ShopCenterController;
import cn.enbug.shop.shop.good.GoodAdminController;
import cn.enbug.shop.shop.modify.ShopModifyController;
import cn.enbug.shop.shop.order.ShopOrderController;
import cn.enbug.shop.store.StoreController;
import cn.enbug.shop.suggest.SuggestController;
import cn.enbug.shop.upload.UploadController;
import cn.enbug.shop.user.center.UserCenterController;
import cn.enbug.shop.user.modify.UserModifyController;
import cn.enbug.shop.user.order.UserOrderController;
import cn.enbug.shop.user.pay.UserPayController;
import cn.enbug.shop.user.shopcar.ShopcarController;
import com.jfinal.config.Routes;

/**
 * This is the route config of the program.
 *
 * @author Yang Zhizhuang
 * @author Hu Wenqiang
 * @version 1.0.4
 * @since 1.0.0
 */
public class FrontRoutes extends Routes {

    /**
     * @see com.jfinal.config.Routes#config()
     */
    @Override
    public void config() {

        setBaseViewPath("/_view");

        add("/", IndexController.class, "index");
        add("/login", LoginController.class);
        add("/register", RegisterController.class);
        add("/captcha", CaptchaController.class);
        add("/upload", UploadController.class);
        add("/good", GoodController.class);
        add("/search", SearchController.class);
        add("/suggest", SuggestController.class);
        add("/store", StoreController.class);
        add("/order", OrderController.class);
        add("/forget", ForgetController.class);

        add("/shop/center", ShopCenterController.class);
        add("/shop/all", ShopAllController.class);
        add("/shop/good", GoodAdminController.class);
        add("/shop/modify", ShopModifyController.class);
        add("/shop/order", ShopOrderController.class);

        add("/user/shopcar", ShopcarController.class);
        add("/user/order", UserOrderController.class);
        add("/user/pay", UserPayController.class);
        add("/user/center", UserCenterController.class);
        add("/user/modify", UserModifyController.class);

    }

}
