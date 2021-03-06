package com.xxx.market.service.api.ump;

import java.util.Date;

import com.xxx.market.service.common.AbstractParamDto;
@SuppressWarnings("serial")
public class FullCutParamDto  extends AbstractParamDto{
      private Long id;
      private Long sellerId;
      private String fullCutName;
      private Date startDate;
      private Date endDate;
      private String setItem;
      private String productIds;
      
      public FullCutParamDto(Long sellerId,String fullCutName,Date startDate,Date endDate,String setItem,String productIds) {
		super();
		this.sellerId=sellerId;
		this.fullCutName=fullCutName;
		this.startDate=startDate;
		this.endDate=endDate;
		this.setItem=setItem;
		this.productIds=productIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getFullCutName() {
		return fullCutName;
	}
	public void setFullCutName(String fullCutName) {
		this.fullCutName = fullCutName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSetItem() {
		return setItem;
	}
	public void setSetItem(String setItem) {
		this.setItem = setItem;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
      
      
      


}
