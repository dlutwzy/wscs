package com.xxx.market.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCashbackSet<M extends BaseCashbackSet<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setCashbackId(java.lang.Long cashbackId) {
		set("cashback_id", cashbackId);
	}

	public java.lang.Long getCashbackId() {
		return get("cashback_id");
	}

	public void setProductId(java.lang.Long productId) {
		set("product_id", productId);
	}

	public java.lang.Long getProductId() {
		return get("product_id");
	}

}
