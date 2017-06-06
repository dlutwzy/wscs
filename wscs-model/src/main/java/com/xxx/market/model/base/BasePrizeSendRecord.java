package com.xxx.market.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePrizeSendRecord<M extends BasePrizeSendRecord<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setPrizeId(java.lang.Long prizeId) {
		set("prize_id", prizeId);
	}

	public java.lang.Long getPrizeId() {
		return get("prize_id");
	}

	public void setBuyerId(java.lang.Long buyerId) {
		set("buyer_id", buyerId);
	}

	public java.lang.Long getBuyerId() {
		return get("buyer_id");
	}

	public void setActivityId(java.lang.Long activityId) {
		set("activity_id", activityId);
	}

	public java.lang.Long getActivityId() {
		return get("activity_id");
	}

	public void setActivityType(java.lang.Integer activityType) {
		set("activity_type", activityType);
	}

	public java.lang.Integer getActivityType() {
		return get("activity_type");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public void setActive(java.lang.Integer active) {
		set("active", active);
	}

	public java.lang.Integer getActive() {
		return get("active");
	}

	public void setCreated(java.util.Date created) {
		set("created", created);
	}

	public java.util.Date getCreated() {
		return get("created");
	}

	public void setUpdated(java.util.Date updated) {
		set("updated", updated);
	}

	public java.util.Date getUpdated() {
		return get("updated");
	}

}
