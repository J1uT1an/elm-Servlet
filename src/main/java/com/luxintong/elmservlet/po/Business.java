package com.luxintong.elmservlet.po;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.po
 * @className: Business
 * @author: Lu Xintong
 * @description <p>Business</p>
 * @date: 2023-12-15 17:15
 * @version: 1.0
 */
public class Business {
	private Integer businessId;
	private String businessName;
	private String businessAddress;
	private String businessExplain;
	private String businessImg;
	private Integer orderTypeId;
	private Double starPrice; // 起送费
	private Double deliveryPrice; // 配送费
	private String remarks;
	
	public Integer getBusinessId() {
		return businessId;
	}
	
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	
	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getBusinessAddress() {
		return businessAddress;
	}
	
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	
	public String getBusinessExplain() {
		return businessExplain;
	}
	
	public void setBusinessExplain(String businessExplain) {
		this.businessExplain = businessExplain;
	}
	
	public String getBusinessImg() {
		return businessImg;
	}
	
	public void setBusinessImg(String businessImg) {
		this.businessImg = businessImg;
	}
	
	public Integer getOrderTypeId() {
		return orderTypeId;
	}
	
	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}
	
	public Double getStarPrice() {
		return starPrice;
	}
	
	public void setStarPrice(Double starPrice) {
		this.starPrice = starPrice;
	}
	
	public Double getDeliveryPrice() {
		return deliveryPrice;
	}
	
	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
