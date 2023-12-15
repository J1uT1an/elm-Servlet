package com.luxintong.elmservlet.po;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.po
 * @className: User
 * @author: Lu Xintong
 * @description <p>User</p>
 * @date: 2023-12-15 17:16
 * @version: 1.0
 */
public class User {
	private String userId;
	private String password;
	private String userName;
	private Integer userSex;
	private String userImg;
	private Integer delTag;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getUserSex() {
		return userSex;
	}
	
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	
	public String getUserImg() {
		return userImg;
	}
	
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	public Integer getDelTag() {
		return delTag;
	}
	
	public void setDelTag(Integer delTag) {
		this.delTag = delTag;
	}
}
