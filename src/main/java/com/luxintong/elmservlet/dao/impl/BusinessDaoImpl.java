package com.luxintong.elmservlet.dao.impl;

import com.luxintong.elmservlet.dao.BusinessDao;
import com.luxintong.elmservlet.po.Business;
import com.luxintong.elmservlet.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: BusinessDaoImpl
 * @author: Lu Xintong
 * @description <p>BusinessDaoImpl</p>
 * @date: 2023-12-15 17:12
 * @version: 1.0
 */
public class BusinessDaoImpl implements BusinessDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws SQLException {
		
		// 根据点餐分类编号查询所属商家信息
		List<Business> list = new ArrayList<>();
		
		// 查询sql语句
		String sql = "select * from business where orderTypeId = ? order by businessId";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			
			// 将？替换成具体的值
			pst.setInt(1, orderTypeId);
			
			// 执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			
			// 遍历结果集，并将每行数据保存到List集合中
			while (rs.next()) {
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
				list.add(business);
			}
		} finally {
			//这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			//这里负责关闭PreparedStatement和ResultSet
		}
		return list;
	}
	
	@Override
	public Business getBusinessById(Integer businessId) throws SQLException {
		// 根据商家编号查询商家信息
		Business business = null;
		
		// 查询sql语句
		String sql = "select * from business where businessId = ?";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			
			// 将？替换成具体的值
			pst.setInt(1, businessId);
			
			// 执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			
			// 遍历结果集，并将每行数据保存到List集合中
			while (rs.next()) {
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setBusinessImg(rs.getString("businessImg"));
				business.setOrderTypeId(rs.getInt("orderTypeId"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				business.setRemarks(rs.getString("remarks"));
			}
		} finally {
			//这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			//这里负责关闭PreparedStatement和ResultSet
		}
		return business;
	}
}
