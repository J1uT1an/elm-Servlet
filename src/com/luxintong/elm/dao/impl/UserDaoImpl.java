package com.luxintong.elm.dao.impl;

import com.luxintong.elm.dao.UserDao;
import com.luxintong.elm.po.User;
import com.luxintong.elm.util.DBUtil;

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
		// sql语句
		String sql = "select * from user where userId=? and password=?";
		try {
			// Connection从ThreadLocal中获取
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
	public Integer getUserById(String userId) {
		int row = 0;
		// sql语句
		String sql = "select count(*) from user where userId =?";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, userId);
			// 执行查询，并将查询结果保存到结果集里
			rs = pst.executeQuery();
			// 遍历结果集
			while (rs.next()) {
				row = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return row;
	}
	
	@Override
	// 向用户表中添加一条记录
	public Integer saveUser(String userId, String password, String userName, Integer userSex) throws SQLException {
		int row = 0;
		// 查询sql语句
		String sql = "insert into user(userId,password,userName,userSex) values(?,?,?,?)";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, userId);
			pst.setString(2, password);
			pst.setString(3, userName);
			pst.setInt(4, userSex);
			// 返回影响行数
			row = pst.executeUpdate();
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return row;
	}
	
}
