package cn.ssdut153.shop.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseLog<M extends BaseLog<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

	public M setOperation(java.lang.String operation) {
		set("operation", operation);
		return (M)this;
	}

	public java.lang.String getOperation() {
		return get("operation");
	}

	public M setHappenTime(java.util.Date happenTime) {
		set("happen_time", happenTime);
		return (M)this;
	}

	public java.util.Date getHappenTime() {
		return get("happen_time");
	}

	public M setIp(java.lang.String ip) {
		set("ip", ip);
		return (M)this;
	}

	public java.lang.String getIp() {
		return get("ip");
	}

	public M setJoinId(java.lang.Integer joinId) {
		set("join_id", joinId);
		return (M)this;
	}

	public java.lang.Integer getJoinId() {
		return get("join_id");
	}

}
