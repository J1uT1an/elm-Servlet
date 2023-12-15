package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.User;

import java.sql.SQLException;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao
 * @className: UserDao
 * @author: Lu Xintong
 * @description <p>UserDao</p>
 * @date: 2023-12-15 17:11
 * @version: 1.0
 */
public interface UserDao {
	/**
	 * 根据用户编号与密码查询用户信息
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	User getUserByIdByPass(String userId, String password) throws SQLException;
	
	/**
	 * 根据用户编号查询用户表返回的行数
	 *
	 * @param userId
	 * @return
	 */
	Integer getUserById(String userId);
	
	/**
	 * 向用户表中添加一条记录
	 *
	 * @param userId
	 * @param password
	 * @param userName
	 * @param userSex
	 * @return
	 */
	Integer saveUser(String userId, String password, String userName, Integer userSex) throws SQLException;
}
