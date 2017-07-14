package cn.enbug.shop.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class MappingKit {

    public static void mapping(ActiveRecordPlugin arp) {
        arp.addMapping("t_comment", "id", Comment.class);
        arp.addMapping("t_file", "id", File.class);
        arp.addMapping("t_good", "id", Good.class);
        arp.addMapping("t_log", "id", Log.class);
        arp.addMapping("t_order", "id", Order.class);
        arp.addMapping("t_shop", "id", Shop.class);
        arp.addMapping("t_shop_car", "id", ShopCar.class);
        arp.addMapping("t_user", "id", User.class);
    }
}

