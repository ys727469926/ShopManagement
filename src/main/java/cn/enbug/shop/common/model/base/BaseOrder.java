package cn.enbug.shop.common.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseOrder<M extends BaseOrder<M>> extends Model<M> implements IBean {

    public java.lang.Integer getId() {
        return get("id");
    }

    public M setId(java.lang.Integer id) {
        set("id", id);
        return (M) this;
    }

    public java.lang.Integer getUserId() {
        return get("user_id");
    }

    public M setUserId(java.lang.Integer userId) {
        set("user_id", userId);
        return (M) this;
    }

    public java.lang.Integer getShopId() {
        return get("shop_id");
    }

    public M setShopId(java.lang.Integer shopId) {
        set("shop_id", shopId);
        return (M) this;
    }

    public java.lang.Integer getOwnerId() {
        return get("owner_id");
    }

    public M setOwnerId(java.lang.Integer ownerId) {
        set("owner_id", ownerId);
        return (M) this;
    }

    public java.lang.Integer getGoodId() {
        return get("good_id");
    }

    public M setGoodId(java.lang.Integer goodId) {
        set("good_id", goodId);
        return (M) this;
    }

    public java.lang.Integer getCount() {
        return get("count");
    }

    public M setCount(java.lang.Integer count) {
        set("count", count);
        return (M) this;
    }

    public java.math.BigDecimal getPrice() {
        return get("price");
    }

    public M setPrice(java.math.BigDecimal price) {
        set("price", price);
        return (M) this;
    }

    public java.lang.String getOrderNumber() {
        return get("order_number");
    }

    public M setOrderNumber(java.lang.String orderNumber) {
        set("order_number", orderNumber);
        return (M) this;
    }

    public java.lang.Integer getOrderStatus() {
        return get("order_status");
    }

    public M setOrderStatus(java.lang.Integer orderStatus) {
        set("order_status", orderStatus);
        return (M) this;
    }

    public java.util.Date getOrderTime() {
        return get("order_time");
    }

    public M setOrderTime(java.util.Date orderTime) {
        set("order_time", orderTime);
        return (M) this;
    }

    public java.util.Date getDealTime() {
        return get("deal_time");
    }

    public M setDealTime(java.util.Date dealTime) {
        set("deal_time", dealTime);
        return (M) this;
    }

    public java.util.Date getFinishTime() {
        return get("finish_time");
    }

    public M setFinishTime(java.util.Date finishTime) {
        set("finish_time", finishTime);
        return (M) this;
    }

    public java.lang.Integer getAddressId() {
        return get("address_id");
    }

    public M setAddressId(java.lang.Integer addressId) {
        set("address_id", addressId);
        return (M) this;
    }

    public java.lang.String getGoodName() {
        return get("good_name");
    }

    public M setGoodName(java.lang.String goodName) {
        set("good_name", goodName);
        return (M) this;
    }

    public java.lang.String getShopName() {
        return get("shop_name");
    }

    public M setShopName(java.lang.String shopName) {
        set("shop_name", shopName);
        return (M) this;
    }

    public java.lang.String getAvator() {
        return get("avator");
    }

    public M setAvator(java.lang.String avator) {
        set("avator", avator);
        return (M) this;
    }

    public java.lang.String getGoodUuid() {
        return get("good_uuid");
    }

    public M setGoodUuid(java.lang.String goodUuid) {
        set("good_uuid", goodUuid);
        return (M) this;
    }

    public java.lang.String getShopUuid() {
        return get("shop_uuid");
    }

    public M setShopUuid(java.lang.String shopUuid) {
        set("shop_uuid", shopUuid);
        return (M) this;
    }

    public java.lang.String getReciverName() {
        return get("reciver_name");
    }

    public M setReciverName(java.lang.String reciverName) {
        set("reciver_name", reciverName);
        return (M) this;
    }

    public java.lang.String getReciverPhone() {
        return get("reciver_phone");
    }

    public M setReciverPhone(java.lang.String reciverPhone) {
        set("reciver_phone", reciverPhone);
        return (M) this;
    }

    public java.lang.String getReciverAddress() {
        return get("reciver_address");
    }

    public M setReciverAddress(java.lang.String reciverAddress) {
        set("reciver_address", reciverAddress);
        return (M) this;
    }

}
