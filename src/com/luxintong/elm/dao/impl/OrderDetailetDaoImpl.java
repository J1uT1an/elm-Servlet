package com.luxintong.elm.dao.impl;

import com.luxintong.elm.dao.OrderDetailetDao;
import com.luxintong.elm.po.Food;
import com.luxintong.elm.po.Orderdetailet;
import com.luxintong.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: OrderDetailsDaoImpl
 * @author: Lu Xintong
 * @description <p>OrderDetailsDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class OrderDetailetDaoImpl implements OrderDetailetDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	// 批量添加到订单明细表中
	public Integer saveManyByOrderId(List<Orderdetailet> list) throws SQLException {
		int row = 0;
		// sql语句
		StringBuffer stringBuffer = new StringBuffer("insert into orderdetailet(orderId,foodId,quantity) values");
		//定义一个String类型的sql，用来接受下面添加句子后的sql
		String sql = null;
		// 遍历list集合，将获取到的值，赋值给sql语句相应的值
		for (int i = 0; i < list.size(); i++) {
			Orderdetailet orderdetailet = new Orderdetailet();
			orderdetailet = list.get(i);
			int orderId = orderdetailet.getOrderId();
			int foodId = orderdetailet.getFoodId();
			int quantity = orderdetailet.getQuantity();
			if (i == list.size() - 1) {
				sql = String.valueOf(stringBuffer.append("(" + orderId + "," + foodId + "," + quantity + ")"));
			} else {
				sql = String.valueOf(stringBuffer.append("(" + orderId + "," + foodId + "," + quantity + ")" + ","));
			}
		}
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			row = pst.executeUpdate();
		} finally {
			DBUtil.close(rs, pst);
		}
		return row;
	}
	
	@Override
	// 功能：根据订单编号查询所有订单明细信息
	public List<Orderdetailet> getOrderdetailetById(Integer orderId) throws SQLException {
		// 创建一个list集合用来保存查询到的信息
		List<Orderdetailet> list = new ArrayList<>();
		// sql语句
		String sql = "select * from orderdetailet oo,food \n" +
				"where oo.foodId = food.foodId \n" +
				"and orderId=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderId);
			rs = pst.executeQuery();
			// 遍历rs结果集
			while (rs.next()) {
				Orderdetailet orderdetailet = new Orderdetailet();
				orderdetailet.setOdId(rs.getInt("odId"));
				orderdetailet.setOrderId(rs.getInt("orderId"));
				orderdetailet.setFoodId(rs.getInt("foodId"));
				orderdetailet.setQuantity(rs.getInt("quantity"));
				Food food = new Food();
				food.setFoodId(rs.getInt("foodId"));
				food.setFoodName(rs.getString("foodName"));
				food.setFoodExplain(rs.getString("foodExplain"));
				food.setFoodImg(rs.getString("foodImg"));
				food.setFoodPrice(rs.getDouble("foodPrice"));
				food.setBusinessId(rs.getInt("businessId"));
				food.setRemarks(rs.getString("remarks"));
				orderdetailet.setFood(food);
				list.add(orderdetailet);
			}
		} finally {
			DBUtil.close(rs, pst);
		}
		return list;
	}
}
