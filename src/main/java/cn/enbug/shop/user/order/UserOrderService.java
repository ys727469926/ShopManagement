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

package cn.enbug.shop.user.order;

import cn.enbug.shop.common.model.OrderNumber;
import cn.enbug.shop.common.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yang Zhizhuang
 * @author Hu Wenqiang
 * @version 1.0.2
 * @since 1.0.0
 */
public class UserOrderService {

    public static final UserOrderService ME = new UserOrderService();

    public List<Object> getUnpayedList(List<OrderNumber> orderNumbers) {
        if (null == orderNumbers) {
            return new ArrayList<>();
        }
        List<Object> list = new ArrayList<>();
        for (OrderNumber o : orderNumbers) {
            Map<String, Object> order = new HashMap<>();
            order.put("orderNumber", o.getOrderNumber());
            order.put("list", OrderService.ME.getOrderListByOrderNumber(o.getOrderNumber()));
            list.add(order);
        }
        return list;
    }

}
