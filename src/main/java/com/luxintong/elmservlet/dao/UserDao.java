package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.User;

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
	 * @return Exception
	 * @throws
	 */
	User getUserByIdByPass(String userId, String password) throws Exception;
	
	/**
	 * 根据用户编号查询用户表返回的行数
	 *
	 * @param userId
	 * @return Exception
	 * @return
	 */
	Integer getUserById(String userId) throws Exception;
	
	/**
	 * 向用户表中添加一条记录
	 *
	 * @param user
	 * @return Exception
	 * @return
	 */
	Integer saveUser(User user) throws Exception;
}
