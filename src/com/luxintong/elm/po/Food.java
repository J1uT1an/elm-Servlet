package com.luxintong.elm.po;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.po
 * @className: Food
 * @author: Lu Xintong
 * @description <p>Food</p>
 * @date: 2023-12-15 17:16
 * @version: 1.0
 */
public class Food {
	private Integer foodId;
	private String foodName;
	private String foodExplain;
	private String foodImg;
	private Double foodPrice;
	private Integer businessId;
	private String remarks;
	
	public Integer getFoodId() {
		return foodId;
	}
	
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	
	public String getFoodName() {
		return foodName;
	}
	
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public String getFoodExplain() {
		return foodExplain;
	}
	
	public void setFoodExplain(String foodExplain) {
		this.foodExplain = foodExplain;
	}
	
	public String getFoodImg() {
		return foodImg;
	}
	
	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}
	
	public Double getFoodPrice() {
		return foodPrice;
	}
	
	public void setFoodPrice(Double foodPrice) {
		this.foodPrice = foodPrice;
	}
	
	public Integer getBusinessId() {
		return businessId;
	}
	
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
