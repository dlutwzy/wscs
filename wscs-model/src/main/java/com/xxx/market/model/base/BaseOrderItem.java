package com.xxx.market.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOrderItem<M extends BaseOrderItem<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public void setOrderId(java.lang.Long orderId) {
		set("order_id", orderId);
	}

	public java.lang.Long getOrderId() {
		return get("order_id");
	}

	public void setProductId(java.lang.Long productId) {
		set("product_id", productId);
	}

	public java.lang.Long getProductId() {
		return get("product_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setPrice(java.math.BigDecimal price) {
		set("price", price);
	}

	public java.math.BigDecimal getPrice() {
		return get("price");
	}

	public void setSn(java.lang.String sn) {
		set("sn", sn);
	}

	public java.lang.String getSn() {
		return get("sn");
	}

	public void setProductImg(java.lang.String productImg) {
		set("product_img", productImg);
	}

	public java.lang.String getProductImg() {
		return get("product_img");
	}

	public void setQuantity(java.lang.Integer quantity) {
		set("quantity", quantity);
	}

	public java.lang.Integer getQuantity() {
		return get("quantity");
	}

	public void setSpecificationValue(java.lang.String specificationValue) {
		set("specification_value", specificationValue);
	}

	public java.lang.String getSpecificationValue() {
		return get("specification_value");
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

	public void setActive(java.lang.Boolean active) {
		set("active", active);
	}

	public java.lang.Boolean getActive() {
		return get("active");
	}

}
