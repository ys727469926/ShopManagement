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
import cn.enbug.shop.common.validator.BaseValidator;
import cn.enbug.shop.login.LoginService;
import com.jfinal.core.Controller;

/**
 * 手机号注册验证器
 *
 * @author Hu Wenqiang
 * @version 1.0.2
 * @since 1.0.0
 */
public class PhoneRegisterValidator extends BaseValidator {

    @Override
    protected void validate(Controller c) {
        validatePhone("phone", Ret.MSG, "wrong format phone");
        validatePhoneCaptcha("phone", "phone_captcha", Ret.MSG, "wrong phone captcha");
        if (null != LoginService.me.findUserByPhone(c.getPara("phone"))) {
            addError(Ret.MSG, "phone already used.");
        }
    }

}
