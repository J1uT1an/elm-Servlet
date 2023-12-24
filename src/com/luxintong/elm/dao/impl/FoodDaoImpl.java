package com.luxintong.elm.dao.impl;

import com.luxintong.elm.dao.FoodDao;
import com.luxintong.elm.po.Food;
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
 * @className: FoodDaoImpl
 * @author: Lu Xintong
 * @description <p>FoodDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class FoodDaoImpl implements FoodDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) throws SQLException {
		// 根据商家编号查询所属食品信息
		// 创建List集合，保存结果集中的数据
		List<Food> list = new ArrayList<>();
		
		// 查询sql语句
		String sql = "select * from food where businessId = ?";
		try {
			// 获取连接，Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			// 获取预处理
			pst = con.prepareStatement(sql);
			// 处理sql语句
			pst.setInt(1, businessId);
			// 将处理结果保存到结果集
			rs = pst.executeQuery();
			// 遍历结果集，将结果集中的数据保存到List集合中
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
