package cn.enbug.shop.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseComment<M extends BaseComment<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setGoodId(java.lang.Integer goodId) {
		set("good_id", goodId);
		return (M)this;
	}

	public java.lang.Integer getGoodId() {
		return get("good_id");
	}

	public M setBuyerId(java.lang.Integer buyerId) {
		set("buyer_id", buyerId);
		return (M)this;
	}

	public java.lang.Integer getBuyerId() {
		return get("buyer_id");
	}

	public M setIsGood(java.lang.Integer isGood) {
		set("is_good", isGood);
		return (M)this;
	}

	public java.lang.Integer getIsGood() {
		return get("is_good");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}

	public java.lang.String getDescription() {
		return get("description");
	}

	public M setShopId(java.lang.Integer shopId) {
		set("shop_id", shopId);
		return (M)this;
	}

	public java.lang.Integer getShopId() {
		return get("shop_id");
	}

}
