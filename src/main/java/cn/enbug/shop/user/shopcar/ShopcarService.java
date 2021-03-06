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

package cn.enbug.shop.user.shopcar;

import cn.enbug.shop.common.kit.RedisKit;
import cn.enbug.shop.common.kit.Ret;
import cn.enbug.shop.common.model.Good;
import cn.enbug.shop.common.model.User;
import cn.enbug.shop.common.service.GoodService;
import cn.enbug.shop.common.service.ShopCarService;

/**
 * @author Yang Zhizhuang
 * @version 1.0.0
 * @since 1.0.0
 */
public class ShopcarService {

    public static final ShopcarService ME = new ShopcarService();
    private static final ShopCarService SRV = ShopCarService.ME;

    public Ret add(String token, String uuid, int count) {
        User user = RedisKit.getUserByToken(token);
        if (null == user) {
            return Ret.fail("登录超时").set("code", -1);
        }
        Good good = GoodService.ME.findGoodByUuid(uuid);
        if (null == good) {
            return Ret.fail("商品不存在").set("code", -2);
        }
        return SRV.add(user, good, count) ? Ret.succeed() : Ret.fail("库存不足。").set("code", -3);
    }

    public Ret modifyCount(String token, String uuid, int count) {
        return SRV.modifyCount(token, uuid, count) ? Ret.succeed() : Ret.fail("Fail.");
    }

    public Ret del(String token, String uuid) {
        return SRV.del(token, uuid) ? Ret.succeed() : Ret.fail("Fail.");
    }

    public Ret modifyCount(String token, int id, int count) {
        return SRV.modifyCount(token, id, count) ? Ret.succeed() : Ret.fail("Fail.");
    }

    public Ret del(String token, int id) {
        return SRV.del(token, id) ? Ret.succeed() : Ret.fail("Fail.");
    }

}
