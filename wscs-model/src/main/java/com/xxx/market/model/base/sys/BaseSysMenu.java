package com.xxx.market.model.base.sys;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSysMenu<M extends BaseSysMenu<M>> extends Model<M> implements IBean {

	public void setId(Long id) {
		set("id", id);
	}

	public Long getId() {
		return get("id");
	}

	public void setSellerId(Long sellerId) {
		set("seller_id", sellerId);
	}

	public Long getSellerId() {
		return get("seller_id");
	}

	public void setBuyerId(Long buyerId) {
		set("buyer_id", buyerId);
	}

	public Long getBuyerId() {
		return get("buyer_id");
	}

	public void setParentId(Long parentId) {
		set("parent_id", parentId);
	}

	public Long getParentId() {
		return get("parent_id");
	}

	public void setAgentName(String agentName) {
		set("agent_name", agentName);
	}

	public String getAgentName() {
		return get("agent_name");
	}

	public void setAgentPhone(String agentPhone) {
		set("agent_phone", agentPhone);
	}

	public String getAgentPhone() {
		return get("agent_phone");
	}

	public void setAreaId(Long areaId) {
		set("area_id", areaId);
	}

	public Long getAreaId() {
		return get("area_id");
	}

	public void setAreaTreePath(String areaTreePath) {
		set("area_tree_path", areaTreePath);
	}

	public String getAreaTreePath() {
		return get("area_tree_path");
	}

	public void setAgentAddr(String agentAddr) {
		set("agent_addr", agentAddr);
	}

	public String getAgentAddr() {
		return get("agent_addr");
	}

	public void setExpireDate(java.util.Date expireDate) {
		set("expire_date", expireDate);
	}

	public java.util.Date getExpireDate() {
		return get("expire_date");
	}

	public void setAuditDate(java.util.Date auditDate) {
		set("audit_date", auditDate);
	}

	public java.util.Date getAuditDate() {
		return get("audit_date");
	}

	public void setStatus(Integer status) {
		set("status", status);
	}

	public Integer getStatus() {
		return get("status");
	}

	public void setActive(Boolean active) {
		set("active", active);
	}

	public Boolean getActive() {
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
