package com.luxintong.elmservlet.dao.impl;

import com.luxintong.elmservlet.dao.FoodDao;
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
 * @className: FoodDaoImpl
 * @author: Lu Xintong
 * @description <p>FoodDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class FoodDaoImpl implements FoodDao {
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) throws Exception {
		// 根据商家编号查询所属食品信息
		// 创建 List 集合，保存结果集中的数据
		List<Food> list = new ArrayList<>();
		// 查询sql语句
		String sql = "select * from food where businessId = ? order by foodId";
		try {
			// 获取连接，Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			// 获取预处理
			pst = con.prepareStatement(sql);
			// 处理 sql 语句
			pst.setInt(1, businessId);
			// 将处理结果保存到结果集
			rs = pst.executeQuery();
			// 遍历结果集，将结果集中的数据保存到 List 集合中
			while (rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt("foodId"));
				food.setFoodName(rs.getString("foodName"));
				food.setFoodExplain(rs.getString("foodExplain"));
				food.setFoodImg(rs.getString("foodImg"));
				food.setFoodPrice(rs.getDouble("foodPrice"));
				food.setBusinessId(rs.getInt("businessId"));
				food.setRemarks(rs.getString("remarks"));
				list.add(food);
			}
		} finally {
			DBUtil.close(rs, pst);
		}
		return list;
	}
}
