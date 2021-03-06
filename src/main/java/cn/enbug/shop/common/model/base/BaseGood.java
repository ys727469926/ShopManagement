package cn.enbug.shop.common.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseGood<M extends BaseGood<M>> extends Model<M> implements IBean {

    public java.lang.Integer getId() {
        return get("id");
    }

    public M setId(java.lang.Integer id) {
        set("id", id);
        return (M) this;
    }

    public java.lang.String getGoodName() {
        return get("good_name");
    }

    public M setGoodName(java.lang.String goodName) {
        set("good_name", goodName);
        return (M) this;
    }

    public java.lang.String getUuid() {
        return get("uuid");
    }

    public M setUuid(java.lang.String uuid) {
        set("uuid", uuid);
        return (M) this;
    }

    public java.lang.Integer getShopId() {
        return get("shop_id");
    }

    public M setShopId(java.lang.Integer shopId) {
        set("shop_id", shopId);
        return (M) this;
    }

    public java.lang.String getDescription() {
        return get("description");
    }

    public M setDescription(java.lang.String description) {
        set("description", description);
        return (M) this;
    }

    public java.lang.String getAvator() {
        return get("avator");
    }

    public M setAvator(java.lang.String avator) {
        set("avator", avator);
        return (M) this;
    }

    public java.lang.Integer getSaleCount() {
        return get("sale_count");
    }

    public M setSaleCount(java.lang.Integer saleCount) {
        set("sale_count", saleCount);
        return (M) this;
    }

    public java.math.BigDecimal getPrice() {
        return get("price");
    }

    public M setPrice(java.math.BigDecimal price) {
        set("price", price);
        return (M) this;
    }

    public java.lang.Integer getStatus() {
        return get("status");
    }

    public M setStatus(java.lang.Integer status) {
        set("status", status);
        return (M) this;
    }

    public java.lang.Integer getNumber() {
        return get("number");
    }

    public M setNumber(java.lang.Integer number) {
        set("number", number);
        return (M) this;
    }

}
