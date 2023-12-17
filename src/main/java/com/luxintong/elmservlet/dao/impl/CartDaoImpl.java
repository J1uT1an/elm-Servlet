package com.luxintong.elmservlet.dao.impl;

import com.luxintong.elmservlet.dao.CartDao;
import com.luxintong.elmservlet.po.Business;
import com.luxintong.elmservlet.po.Cart;
import com.luxintong.elmservlet.po.Food;
import com.luxintong.elmservlet.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: CartDaoImpl
 * @author: Lu Xintong
 * @description <p>CartDaoImpl</p>
 * @date: 2023-12-15 17:12
 * @version: 1.0
 */
public class CartDaoImpl implements CartDao {
	
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	// 根据用户编号查询此用户所有购物车信息 ​ 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
	public List<Cart> listCart(Cart cart) throws Exception {
		// 创建 List 集合，用来保存查询结果
		List<Cart> list = new ArrayList();
		
		// sql 语句
		StringBuffer sql = new StringBuffer("select * from cart,business,food \n" +
				"where cart.businessId=business.businessId and cart.foodId=food.foodId \n" +
				"and cart.userId=?");
		if (cart.getBusinessId() != null) {
			sql.append("and cart.businessId=?" + cart.getBusinessId());
		}
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			// 把？换成具体的值
			pst.setString(1, cart.getUserId());
			if (cart.getBusinessId() != null) {
				pst.setInt(2, cart.getBusinessId());
			}
			// 执行查询，并将查询结果保存在结果集中
			rs = pst.executeQuery();
			
			// 遍历结果集，将数据保存到 List 集合中
			while (rs.next()) {
				cart = new Cart();
				cart.setCartId(rs.getInt("cartId"));
				cart.setFoodId(rs.getInt("foodId"));
				cart.setBusinessId(rs.getInt("businessId"));
				cart.setUserId(rs.getString("userId"));
				cart.setQuantity(rs.getInt("quantity"));
				
				Food food = new Food();
				food.setFoodId(rs.getInt("foodId"));
				food.setFoodName(rs.getString("foodName"));
				food.setFoodExplain(rs.getString("foodExplain"));
				food.setFoodImg(rs.getString("foodImg"));
				food.setFoodPrice(rs.getDouble("foodPrice"));
				food.setBusinessId(rs.getInt("businessId"));
				food.setRemarks(rs.getString("remarks"));
				cart.setFood(food);
				
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setRemarks(rs.getString("remarks"));
				cart.setBusiness(business);
				
				list.add(cart);
			}
		} finally {
			DBUtil.close(rs, pst);
		}
		return list;
	}
	
	@Override
	// 向购物车表中添加一条记录
	public Integer saveCart(Cart cart) throws Exception {
		int result = 0;
		// sql 语句
		String sql = "insert into cart values(null,?,?,?,1)";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setInt(1, cart.getFoodId());
			pst.setInt(2, cart.getBusinessId());
			pst.setString(3, cart.getUserId());
			result = pst.executeUpdate();
		} finally {
			DBUtil.close(pst);
		}
		return result;
	}
	
	@Override
	// 根据用户编号、商家编号、食品编号更新数量
	public Integer updateCart(Cart cart) throws Exception {
		int result = 0;
		// sql 语句
		String sql = "update cart set quantity=? where userId=? and businessId=? and foodId=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setInt(1, cart.getQuantity());
			pst.setString(2, cart.getUserId());
			pst.setInt(3, cart.getBusinessId());
			pst.setInt(4, cart.getFoodId());
			// pst.executeUpdate 这个方法是返回影响数据库的行数
			result = pst.executeUpdate();
		} finally {
			DBUtil.close(pst);
		}
		return result;
	}
	
	@Override
	// 根据用户编号、商家编号、食品编号删除购物车表中的一条食品记录 ​ 根据用户编号、商家编号删除购物车表中的多条条记录
	public Integer removeCart(Cart cart) throws Exception {
		int result = 0;
		// sql 语句
		StringBuffer sql = new StringBuffer("delete from cart where userId = ? and businessId = ?");
		if (cart.getFoodId() != null) {
			sql.append(" and foodId = ?" + cart.getFood());
		}
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			//将？替换成具体的值
			pst.setString(1, cart.getUserId());
			pst.setInt(2, cart.getBusinessId());
			if (cart.getFoodId() != null) {
				pst.setInt(3, cart.getFoodId());
			}
			result = pst.executeUpdate();
		} finally {
			DBUtil.close(pst);
		}
		return result;
	}
}
