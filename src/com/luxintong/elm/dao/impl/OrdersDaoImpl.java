package com.luxintong.elm.dao.impl;

import com.luxintong.elm.dao.OrdersDao;
import com.luxintong.elm.po.Orders;
import com.luxintong.elm.util.CommonUtil;
import com.luxintong.elm.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: OrderDaoImpl
 * @author: Lu Xintong
 * @description <p>OrderDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class OrdersDaoImpl implements OrdersDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
	public Integer saveOrdersById(String userId, Integer businessId, Double orderTotal, Integer daId) throws SQLException {
		int row = 0;
		String date = CommonUtil.getCurrentDate();
		// sql语句
		String sql = "insert into orders(userId,businessId,orderTotal,daId,orderDate,orderState) values(?,?,?,?,?,1)";
		try {
			con = DBUtil.getConnection();
			// Statement.RETURN_GENERATED_KEYS该方法可以获取到自动生成的订单编号，
			pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// 将？替换成具体的值
			pst.setString(1, userId);
			pst.setInt(2, businessId);
			pst.setDouble(3, orderTotal);
			pst.setInt(4, daId);
			pst.setString(5, date);
			
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			while (rs.next()) {
				row = rs.getInt(1);
			}
		} finally {
			DBUtil.close(rs, pst);
		}
		return row;
	}
	
	@Override
	// 功能：根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	public Orders getOrdersById(Integer orderId) throws SQLException {
		Orders orders = null;
		// List<Orderdetailet> list = new ArrayList<>();
		// Orderdetailet orderdetailet = null;
		// sql语句
		String sql = "select * from orders where orderId=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderId);
			rs = pst.executeQuery();
			// 遍历rs结果集
			while (rs.next()) {
				orders = new Orders();
				orders.setOrderId(rs.getInt("orderId"));
				orders.setUserId(rs.getString("userId"));
				orders.setBusinessId(rs.getInt("businessId"));
				orders.setOrderDate(rs.getString("orderDate"));
				orders.setOrderTotal(rs.getDouble("orderTotal"));
				orders.setDaId(rs.getInt("daId"));
				orders.setOrderState(rs.getInt("orderState"));
				//  Integer orderId = orderdetailet.setOrderId(rs.getInt("orderId"));
				// orderdetailet.setFoodId(rs.getInt("foodId"));
				// orderdetailet.setQuantity(rs.getInt("quantity"));
				// orderdetailet.setOrderId(rs.getInt("orderId"));
				// list.add(orderdetailet);
				// orders.setList(list);
			}
		} finally {
			DBUtil.close(rs, pst);
		}
		return orders;
	}
	
	@Override
	// 功能：根据用户编号查询此用户的所有订单信息
	public List<Orders> listOrdersByUserId(String userId) throws SQLException {
		// 创建一个list集合用来保存查询到的信息
		List<Orders> list = new ArrayList<>();
		// sql语句
		String sql = "select * from orders where userId = ?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			rs = pst.executeQuery();
			// 遍历rs结果集
			while (rs.next()) {
				Orders orders = new Orders();
				orders.setOrderId(rs.getInt("orderId"));
				orders.setUserId(rs.getString("userId"));
				orders.setBusinessId(rs.getInt("businessId"));
				orders.setOrderDate(rs.getString("orderDate"));
				orders.setOrderTotal(rs.getDouble("orderTotal"));
				orders.setDaId(rs.getInt("daId"));
				orders.setOrderState(rs.getInt("orderState"));
				
				list.add(orders);
			}
		} finally {
			DBUtil.close(rs, pst);
		}
		return list;
	}
}
