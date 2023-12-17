package com.luxintong.elmservlet.service.impl;

import com.luxintong.elmservlet.dao.UserDao;
import com.luxintong.elmservlet.dao.impl.UserDaoImpl;
import com.luxintong.elmservlet.po.User;
import com.luxintong.elmservlet.service.UserService;
import com.luxintong.elmservlet.util.DBUtil;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: UserServiceImpl
 * @author: Lu Xintong
 * @description <p>UserServiceImpl</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public class UserServiceImpl implements UserService {
	@Override
	public User getUserByIdByPass(String userId, String password) {
		// 根据用户编号与密码查询用户信息
		User user = null;
		UserDao dao = new UserDaoImpl();
		try {
			DBUtil.getConnection();
			user = dao.getUserByIdByPass(userId, password);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return user;
	}
	
	@Override
	public Integer getUserById(String userId) {
		// 根据用户编号查询用户表返回的行数
		int result = 0;
		UserDao dao = new UserDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
	
	@Override
	public Integer saveUser(User user) {
		// 向用户表中添加一条记录
		int result = 0;
		UserDao dao = new UserDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
}


