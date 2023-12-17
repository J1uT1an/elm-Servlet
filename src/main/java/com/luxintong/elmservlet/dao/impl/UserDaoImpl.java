package com.luxintong.elmservlet.dao.impl;

import com.luxintong.elmservlet.dao.UserDao;
import com.luxintong.elmservlet.po.User;
import com.luxintong.elmservlet.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: UserDaoImpl
 * @author: Lu Xintong
 * @description <p>UserDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class UserDaoImpl implements UserDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public User getUserByIdByPass(String userId, String password) throws SQLException {
		// 根据用户编号与密码查询用户信息
		User user = null;
		// sql 语句
		String sql = "select * from user where userId = ? and password = ?";
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, userId);
			pst.setString(2, password);
			// 执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			// 遍历结果集，并将每行数据保存到List集合中
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserSex(rs.getInt("userSex"));
				user.setUserImg(rs.getString("userImg"));
				user.setDelTag(rs.getInt("delTag"));
			}
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return user;
	}
	
	
	@Override
	// 根据用户编号查询用户表返回的行数
	public Integer getUserById(String userId) throws Exception {
		int result = 0;
		// sql 语句
		String sql = "select count(*) from user where userId = ?";
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, userId);
			// 执行查询，并将查询结果保存到结果集里
			rs = pst.executeQuery();
			// 遍历结果集
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return result;
	}
	
	@Override
	// 向用户表中添加一条记录
	public Integer saveUser(User user) throws Exception {
		int result = 0;
		// 查询 sql 语句
		String sql = "insert into user values(?,?,?,?,?,1)";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, user.getUserId());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getUserName());
			pst.setInt(4, user.getUserSex());
			pst.setString(5, user.getUserImg());
			// 返回影响行数
			result = pst.executeUpdate();
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return result;
	}
}
