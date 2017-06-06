package com.xxx.market.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBuyerUser<M extends BaseBuyerUser<M>> extends Model<M> implements IBean {

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

	public void setAuthAppId(java.lang.String authAppId) {
		set("auth_app_id", authAppId);
	}

	public java.lang.String getAuthAppId() {
		return get("auth_app_id");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setHeadimgurl(java.lang.String headimgurl) {
		set("headimgurl", headimgurl);
	}

	public java.lang.String getHeadimgurl() {
		return get("headimgurl");
	}

	public void setOpenId(java.lang.String openId) {
		set("open_id", openId);
	}

	public java.lang.String getOpenId() {
		return get("open_id");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}

	public java.lang.String getEmail() {
		return get("email");
	}

	public void setSex(java.lang.Integer sex) {
		set("sex", sex);
	}

	public java.lang.Integer getSex() {
		return get("sex");
	}

	public void setLanguage(java.lang.String language) {
		set("language", language);
	}

	public java.lang.String getLanguage() {
		return get("language");
	}

	public void setCity(java.lang.String city) {
		set("city", city);
	}

	public java.lang.String getCity() {
		return get("city");
	}

	public void setProvince(java.lang.String province) {
		set("province", province);
	}

	public java.lang.String getProvince() {
		return get("province");
	}

	public void setCountry(java.lang.String country) {
		set("country", country);
	}

	public java.lang.String getCountry() {
		return get("country");
	}

	public void setSubscribe(java.lang.Integer subscribe) {
		set("subscribe", subscribe);
	}

	public java.lang.Integer getSubscribe() {
		return get("subscribe");
	}

	public void setSubscribeTime(java.util.Date subscribeTime) {
		set("subscribe_time", subscribeTime);
	}

	public java.util.Date getSubscribeTime() {
		return get("subscribe_time");
	}

	public void setGroupid(java.lang.Integer groupid) {
		set("groupid", groupid);
	}

	public java.lang.Integer getGroupid() {
		return get("groupid");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setAccessIp(java.lang.String accessIp) {
		set("access_ip", accessIp);
	}

	public java.lang.String getAccessIp() {
		return get("access_ip");
	}

	public void setAccessToken(java.lang.String accessToken) {
		set("access_token", accessToken);
	}

	public java.lang.String getAccessToken() {
		return get("access_token");
	}

	public void setRefreshToken(java.lang.String refreshToken) {
		set("refresh_token", refreshToken);
	}

	public java.lang.String getRefreshToken() {
		return get("refresh_token");
	}

	public void setTokenExpiresIn(java.lang.String tokenExpiresIn) {
		set("token_expires_in", tokenExpiresIn);
	}

	public java.lang.String getTokenExpiresIn() {
		return get("token_expires_in");
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		set("last_login_time", lastLoginTime);
	}

	public java.util.Date getLastLoginTime() {
		return get("last_login_time");
	}

	public void setUnionid(java.lang.String unionid) {
		set("unionid", unionid);
	}

	public java.lang.String getUnionid() {
		return get("unionid");
	}

	public void setScore(java.lang.Integer score) {
		set("score", score);
	}

	public java.lang.Integer getScore() {
		return get("score");
	}

	public void setIsReceiver(java.lang.Integer isReceiver) {
		set("is_receiver", isReceiver);
	}

	public java.lang.Integer getIsReceiver() {
		return get("is_receiver");
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
