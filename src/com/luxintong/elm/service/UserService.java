package com.luxintong.elm.service;

import com.luxintong.elm.po.User;

import java.sql.SQLException;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service
 * @className: UserService
 * @author: Lu Xintong
 * @description <p>UserService</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public interface UserService {
	/**
	 * 根据用户编号与密码查询用户信息
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	public User getUserByIdByPass(String userId, String password);
	
	/**
	 * 根据用户编号查询用户表返回的行数
	 *
	 * @param userId
	 * @return
	 */
	public Integer getUserById(String userId);
	
	/**
	 * 向用户表中添加一条记录
	 *
	 * @param userId
	 * @param password
	 * @param userName
	 * @param userSex
	 * @return
	 */
	public Integer saveUser(String userId, String password, String userName, Integer userSex) throws SQLException;
}
