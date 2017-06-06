package com.xxx.market.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseShop<M extends BaseShop<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setSellerId(java.lang.Long sellerId) {
		set("seller_id", sellerId);
	}

	public java.lang.Long getSellerId() {
		return get("seller_id");
	}

	public void setShopName(java.lang.String shopName) {
		set("shop_name", shopName);
	}

	public java.lang.String getShopName() {
		return get("shop_name");
	}

	public void setShopLogo(java.lang.String shopLogo) {
		set("shop_logo", shopLogo);
	}

	public java.lang.String getShopLogo() {
		return get("shop_logo");
	}

	public void setShopSign(java.lang.String shopSign) {
		set("shop_sign", shopSign);
	}

	public java.lang.String getShopSign() {
		return get("shop_sign");
	}

	public void setShopContact(java.lang.String shopContact) {
		set("shop_contact", shopContact);
	}

	public java.lang.String getShopContact() {
		return get("shop_contact");
	}

	public void setShopContactPhone(java.lang.String shopContactPhone) {
		set("shop_contact_phone", shopContactPhone);
	}

	public java.lang.String getShopContactPhone() {
		return get("shop_contact_phone");
	}

	public void setActive(java.lang.Boolean active) {
		set("active", active);
	}

	public java.lang.Boolean getActive() {
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