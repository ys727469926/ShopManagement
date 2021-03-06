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

import cn.enbug.shop.common.exception.CleanShopcarException;
import cn.enbug.shop.common.kit.RedisKit;
import cn.enbug.shop.common.model.Good;
import cn.enbug.shop.common.model.Shop;
import cn.enbug.shop.common.model.ShopCar;
import cn.enbug.shop.common.model.User;
import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yang Zhizhuang
 * @author Hu Wenqiang
 * @version 1.0.6
 * @since 1.0.0
 */
public class ShopCarService {

    public static final ShopCarService ME = Duang.duang(ShopCarService.class);
    private static final ShopCar SHOP_CAR_DAO = new ShopCar().dao();

    /**
     * get shop car list.
     *
     * @param user User Object
     * @return List&lt;ShopCar&gt;
     */
    public List<ShopCar> getShopCarListByUser(User user) {
        return null == user ? new ArrayList<>() : SHOP_CAR_DAO.find(SHOP_CAR_DAO.getSqlPara("shopcar.findByUserId", user.getId()));
    }

    /**
     * get shop car list.
     *
     * @param token token
     * @return List&lt;ShopCar&gt;
     */
    public List<ShopCar> getShopCarListByToken(String token) {
        return getShopCarListByUser(RedisKit.getUserByToken(token));
    }

    public List<Record> getShopCarRecordListByUser(User user) {
        if (null == user) {
            return new ArrayList<>();
        }
        return Db.find(Db.getSqlPara("shopcar.findRecordByUserId", user.getId()));
    }

    public List<Record> getShopCarRecordListByToken(String token) {
        User user = RedisKit.getUserByToken(token);
        return getShopCarRecordListByUser(user);
    }

    /**
     * get shop car.
     *
     * @param user User Object
     * @param good Good Object
     * @return ShopCar Object
     */
    public ShopCar getShopCarByUserAndGood(User user, Good good) {
        return (null == user || null == good) ? null : SHOP_CAR_DAO.findFirst(SHOP_CAR_DAO.getSqlPara("shopcar.findByUserIdAndGoodId", user.getId(), good.getId()));
    }

    /**
     * get shop car.
     *
     * @param token token
     * @param uuid  good uuid
     * @return ShopCar Object
     */
    public ShopCar getShopCarByTokenAndGoodUuid(String token, String uuid) {
        return getShopCarByUserAndGood(RedisKit.getUserByToken(token), GoodService.ME.findGoodByUuid(uuid));
    }

    public ShopCar getShopCarById(int id) {
        return SHOP_CAR_DAO.findFirst(SHOP_CAR_DAO.getSqlPara("shopcar.findById", id));
    }

    /**
     * add
     *
     * @param token token
     * @param good  Good Object
     * @param count good count
     * @return boolean
     */
    public boolean add(String token, Good good, int count) {
        return add(RedisKit.getUserByToken(token), good, count);
    }

    /**
     * add
     *
     * @param token    token
     * @param goodUuid good uuid
     * @param count    count
     * @return boolean
     */
    public boolean add(String token, String goodUuid, int count) {
        Good good = GoodService.ME.findGoodByUuid(goodUuid);
        return add(token, good, count);
    }

    public boolean add(User user, Good good, int count) {
        if (null == good || null == user) {
            return false;
        }
        if (good.getNumber() < count) {
            return false;
        }
        ShopCar shopCar = getShopCarByUserAndGood(user, good);
        if (null == shopCar) {
            Shop shop = ShopService.ME.findShopById(good.getShopId());
            if (null == shop) {
                return false;
            }
            shopCar = new ShopCar();
            shopCar.setShopId(good.getShopId());
            shopCar.setGoodId(good.getId());
            shopCar.setUserId(user.getId());
            shopCar.setCount(count);
            return shopCar.save();
        } else {
            shopCar.setCount(shopCar.getCount() + count);
            return shopCar.update();
        }
    }

    /**
     * delete
     *
     * @param token    token
     * @param goodUuid good uuid
     * @return boolean
     */
    public boolean del(String token, String goodUuid) {
        ShopCar shopCar = getShopCarByTokenAndGoodUuid(token, goodUuid);
        return null == shopCar || shopCar.delete();
    }

    /**
     * delete
     *
     * @param token token
     * @param id    shopcar id
     * @return boolean
     */
    public boolean del(String token, int id) {
        if (null == UserService.ME.validateToken(token)) {
            return false;
        }
        ShopCar shopCar = getShopCarById(id);
        return null == shopCar || shopCar.delete();
    }

    @Before(Tx.class)
    public boolean clean(String token) {
        List<ShopCar> list = getShopCarListByToken(token);
        if (null == list) {
            return false;
        }
        for (ShopCar car : list) {
            if (!car.delete()) {
                throw new CleanShopcarException("Fail to clean shopcar.");
            }
        }
        return true;
    }

    /**
     * modify good count
     *
     * @param token    token
     * @param goodUuid good uuid
     * @param count    count
     * @return boolean
     */
    public boolean modifyCount(String token, String goodUuid, int count) {
        ShopCar shopCar = getShopCarByTokenAndGoodUuid(token, goodUuid);
        return null != shopCar && shopCar.setCount(count).update();
    }

    /**
     * modify good count
     *
     * @param token token
     * @param id    shopcar id
     * @param count count
     * @return boolean
     */
    public boolean modifyCount(String token, int id, int count) {
        if (null == UserService.ME.validateToken(token)) {
            return false;
        }
        ShopCar shopCar = getShopCarById(id);
        return null != shopCar && shopCar.setCount(count).update();
    }

}
