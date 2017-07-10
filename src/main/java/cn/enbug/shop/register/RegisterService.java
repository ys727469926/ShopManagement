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

package cn.enbug.shop.register;

import cn.enbug.shop.common.kit.Ret;
import cn.enbug.shop.common.service.EmailService;
import cn.enbug.shop.common.service.ShortMessageCaptchaService;
import cn.enbug.shop.common.service.UserService;

/**
 * 注册服务
 *
 * @author Hu Wenqiang
 * @author Yang Zhizhuang
 * @version 1.0.3
 * @since 1.0.0
 */
class RegisterService {

    static final RegisterService ME = new RegisterService();
    private static final UserService SRV = UserService.getInstance();

    /**
     * 邮箱注册
     *
     * @param email email
     * @param ip    ip地址
     * @return 结果
     */
    Ret registerByEmail(String email, String ip) {
        boolean b = SRV.initUserByEmail(email, ip);
        return b ? Ret.succeed() : Ret.fail();
    }

    /**
     * 手机号注册
     *
     * @param phone 手机号
     * @param ip    ip地址
     * @return 结果
     */
    Ret registerByPhone(String phone, String ip) {
        boolean b = SRV.initUserByPhoneNumber(phone, ip);
        return b ? Ret.succeed() : Ret.fail();
    }

    /**
     * handle register step 2.
     *
     * @param code active code
     * @return boolean
     */
    boolean handleStep2(String code) {
        return ShortMessageCaptchaService.ME.validateActiveCodeForPhoneNumber(code) ||
                EmailService.getInstance().validateActiveCodeForEmail(code);
    }

}
