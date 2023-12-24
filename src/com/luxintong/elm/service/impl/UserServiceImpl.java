package com.luxintong.elm.service.impl;

import com.luxintong.elm.dao.UserDao;
import com.luxintong.elm.dao.impl.UserDaoImpl;
import com.luxintong.elm.po.User;
import com.luxintong.elm.service.UserService;
import com.luxintong.elm.util.DBUtil;

import java.sql.SQLException;

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
	// 根据用户编号与密码查询用户信息
	public User getUserByIdByPass(String userId, String password) {
		User user = new User();
		UserDao userDao = new UserDaoImpl();
		try {
			user = userDao.getUserByIdByPass(userId, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return user;
	}
	
	@Override
//    根据用户编号查询用户表返回的行数
	public Integer getUserById(String userId) {
		int count = 0;
		UserDao userDao = new UserDaoImpl();
		try {
			count = userDao.getUserById(userId);
		} finally {
			DBUtil.close();
		}
		
		return count;
	}
	
	@Override
//    向用户表中添加一条记录
	public Integer saveUser(String userId, String password, String userName, Integer userSex) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		int row = 0;
		try {
			//开启一个事物
			DBUtil.beginTransaction();
			row = userDao.saveUser(userId, password, userName, userSex);
			DBUtil.getConnection().commit();
		} catch (SQLException e) {
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
