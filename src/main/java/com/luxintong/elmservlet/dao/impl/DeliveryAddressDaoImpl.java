package com.luxintong.elmservlet.dao.impl;

import com.luxintong.elmservlet.dao.DeliveryAddressDao;
import com.luxintong.elmservlet.po.DeliveryAddress;
import com.luxintong.elmservlet.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: DeliveryAddressDaoImpl
 * @author: Lu Xintong
 * @description <p>DeliveryAddressDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception {
		// 根据用户编号查询所属送货地址
		List<DeliveryAddress> list = new ArrayList<>();
		// 查询 sql 语句
		String sql = "select * from deliveryaddress where userId = ? order by daId";
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, userId);
			// 执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			
			// 遍历结果集，并将每行数据保存到 List 集合中
			while (rs.next()) {
				DeliveryAddress deliveryaddress = new DeliveryAddress();
				
				deliveryaddress.setDaId(rs.getInt("daId"));
				deliveryaddress.setContactName(rs.getString("contactName"));
				deliveryaddress.setContactSex(rs.getInt("contactSex"));
				deliveryaddress.setContactTel(rs.getString("contactTel"));
				deliveryaddress.setAddress(rs.getString("address"));
				deliveryaddress.setUserId(rs.getString("userId"));
				
				list.add(deliveryaddress);
			}
		} finally {
			//这里不能处理异常，也就是没有 catch，只有 finally
			DBUtil.close(rs, pst);
			// 这里负责关闭 PreparedStatement 和 ResultSet
		}
		return list;
	}
	
	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) throws Exception {
		// 根据送货地址编号查询送货地址
		DeliveryAddress deliveryaddress = null;
		// 查询 sql 语句
		String sql = "select * from deliveryaddress where daId = ?";
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setInt(1, daId);
			//  执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			
			// 遍历结果集，并将每行数据保存到 List 集合中
			while (rs.next()) {
				deliveryaddress = new DeliveryAddress();
				
				deliveryaddress.setDaId(rs.getInt("daId"));
				deliveryaddress.setContactName(rs.getString("contactName"));
				deliveryaddress.setContactSex(rs.getInt("contactSex"));
				deliveryaddress.setContactTel(rs.getString("contactTel"));
				deliveryaddress.setAddress(rs.getString("address"));
				deliveryaddress.setUserId(rs.getString("userId"));
			}
		} finally {
			//这里不能处理异常，也就是没有 catch，只有 finally
			DBUtil.close(rs, pst);
			// 这里负责关闭 PreparedStatement 和 ResultSet
		}
		return deliveryaddress;
	}
	
	@Override
	public Integer saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
		// 向送货地址表中添加一条记录
		int result = 0;
		// 查询 sql 语句
		String sql = "insert into deliveryaddress values(?,?,?,?,?)";
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, deliveryAddress.getContactName());
			pst.setInt(2, deliveryAddress.getContactSex());
			pst.setString(3, deliveryAddress.getContactTel());
			pst.setString(4, deliveryAddress.getAddress());
			pst.setString(5, deliveryAddress.getUserId());
			// 返回影响行数
			result = pst.executeUpdate();
		} finally {
			// 这里不能处理异常，也就是没有 catch，只有 finally
			DBUtil.close(pst);
			// 这里负责关闭 PreparedStatement 和 ResultSet
		}
		return result;
	}
	
	@Override
	public Integer updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
		// 根据送货地址编号更新送货地址信息
		int result = 0;
		// 查询 sql 语句
		String sql = "update deliveryaddress set contactName = ?, contactSex = ?, contactTel = ?, address = ? where daId = ?";
		
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, deliveryAddress.getContactName());
			pst.setInt(2, deliveryAddress.getContactSex());
			pst.setString(3, deliveryAddress.getContactTel());
			pst.setString(4, deliveryAddress.getAddress());
			pst.setInt(5, deliveryAddress.getDaId());
			// 返回影响行数
			result = pst.executeUpdate();
			
		} finally {
			// 这里不能处理异常，也就是没有 catch，只有 finally
			DBUtil.close(pst);
			// 这里负责关闭 PreparedStatement 和ResultSet
		}
		return result;
	}
	
	@Override
	public Integer removeDeliveryAddress(Integer daId) throws Exception {
		// 根据送货地址编号删除一条记录
		int result = 0;
		// 查询 sql 语句
		String sql = "delete from deliveryaddress where daId = ?";
		try {
			// Connection 从 ThreadLocal 中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setInt(1, daId);
			// 返回影响行数
			result = pst.executeUpdate();
		} finally {
			// 这里不能处理异常，也就是没有 catch，只有 finally
			DBUtil.close(pst);
			// 这里负责关闭 PreparedStatement 和 ResultSet
		}
		return result;
	}
}
