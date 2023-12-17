package com.luxintong.elmservlet.controller;

import com.luxintong.elmservlet.po.User;
import com.luxintong.elmservlet.service.UserService;
import com.luxintong.elmservlet.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: UserController
 * @author: Lu Xintong
 * @description <p>UserController</p>
 * @date: 2023-12-15 17:14
 * @version: 1.0
 */
public class UserController {
	public Object getUserByIdByPass(HttpServletRequest request) throws Exception {
		// 根据用户编号与密码查询用户信息
		// public User getUserByIdByPass(String userId, String password);
		// 获取前台的请求参数，/UserController/getUserByIdByPass?userId=12345671111&password=123,并将其强转为Integer类型
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		UserService service = new UserServiceImpl();
		User user = service.getUserByIdByPass(userId, password);
		return user;
	}
	
	public Object getUserById(HttpServletRequest request) throws Exception {
		// 根据用户编号查询用户表返回的行数
		// public Integer getUserById(String userId);
		// 获取前台的请求参数，/UserController/getUserById?userId=12345671111,并将其强转为Integer类型
		String userId = request.getParameter("userId");
		UserService service = new UserServiceImpl();
		int result = service.getUserById(userId);
		return result;
	}
	
	public Object saveUser(HttpServletRequest request) throws Exception {
		// 向用户表中添加一条记录
		// public Integer saveUser(String userId,String password,String userName,Integer userSex)
		// 获取前台的请求参数，/UserController/saveUser?userId=126666&password=8888&userName=张张&userSex=0,并将其强转为Integer类型
		User user = new User();
		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setUserName(request.getParameter("userName"));
		user.setUserSex(Integer.valueOf(request.getParameter("userSex")));
		UserService service = new UserServiceImpl();
		int result = service.saveUser(user);
		return result;
	}
}
