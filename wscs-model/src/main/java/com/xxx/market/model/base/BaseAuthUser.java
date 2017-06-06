package com.xxx.market.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAuthUser<M extends BaseAuthUser<M>> extends Model<M> implements IBean {

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

	public void setAppId(java.lang.String appId) {
		set("app_id", appId);
	}

	public java.lang.String getAppId() {
		return get("app_id");
	}

	public void setAuthorizerAccessToken(java.lang.String authorizerAccessToken) {
		set("authorizer_access_token", authorizerAccessToken);
	}

	public java.lang.String getAuthorizerAccessToken() {
		return get("authorizer_access_token");
	}

	public void setAuthorizerRefreshToken(java.lang.String authorizerRefreshToken) {
		set("authorizer_refresh_token", authorizerRefreshToken);
	}

	public java.lang.String getAuthorizerRefreshToken() {
		return get("authorizer_refresh_token");
	}

	public void setExpiresIn(java.lang.Integer expiresIn) {
		set("expires_in", expiresIn);
	}

	public java.lang.Integer getExpiresIn() {
		return get("expires_in");
	}

	public void setSelectFuncInfo(java.lang.String selectFuncInfo) {
		set("select_func_info", selectFuncInfo);
	}

	public java.lang.String getSelectFuncInfo() {
		return get("select_func_info");
	}

	public void setNickName(java.lang.String nickName) {
		set("nick_name", nickName);
	}

	public java.lang.String getNickName() {
		return get("nick_name");
	}

	public void setHeadImg(java.lang.String headImg) {
		set("head_img", headImg);
	}

	public java.lang.String getHeadImg() {
		return get("head_img");
	}

	public void setServiceTypeInfo(java.lang.String serviceTypeInfo) {
		set("service_type_info", serviceTypeInfo);
	}

	public java.lang.String getServiceTypeInfo() {
		return get("service_type_info");
	}

	public void setVerifyTypeInfo(java.lang.String verifyTypeInfo) {
		set("verify_type_info", verifyTypeInfo);
	}

	public java.lang.String getVerifyTypeInfo() {
		return get("verify_type_info");
	}

	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	public java.lang.String getUserName() {
		return get("user_name");
	}

	public void setPrincipalName(java.lang.String principalName) {
		set("principal_name", principalName);
	}

	public java.lang.String getPrincipalName() {
		return get("principal_name");
	}

	public void setBusinessInfo(java.lang.String businessInfo) {
		set("business_info", businessInfo);
	}

	public java.lang.String getBusinessInfo() {
		return get("business_info");
	}

	public void setAlias(java.lang.String alias) {
		set("alias", alias);
	}

	public java.lang.String getAlias() {
		return get("alias");
	}

	public void setQrcodeUrl(java.lang.String qrcodeUrl) {
		set("qrcode_url", qrcodeUrl);
	}

	public java.lang.String getQrcodeUrl() {
		return get("qrcode_url");
	}

	public void setFuncInfo(java.lang.String funcInfo) {
		set("func_info", funcInfo);
	}

	public java.lang.String getFuncInfo() {
		return get("func_info");
	}

	public void setPayMchId(java.lang.String payMchId) {
		set("pay_mch_id", payMchId);
	}

	public java.lang.String getPayMchId() {
		return get("pay_mch_id");
	}

	public void setPaySecretKey(java.lang.String paySecretKey) {
		set("pay_secret_key", paySecretKey);
	}

	public java.lang.String getPaySecretKey() {
		return get("pay_secret_key");
	}

	public void setIsUsed(java.lang.Boolean isUsed) {
		set("is_used", isUsed);
	}

	public java.lang.Boolean getIsUsed() {
		return get("is_used");
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
