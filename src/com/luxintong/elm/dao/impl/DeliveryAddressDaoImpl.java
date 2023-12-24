package com.luxintong.elm.dao.impl;

import com.luxintong.elm.dao.DeliveryAddressDao;
import com.luxintong.elm.po.Deliveryaddress;
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
 * @className: DeliveryAddressDaoImpl
 * @author: Lu Xintong
 * @description <p>DeliveryAddressDaoImpl</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	@Override
	public List<Deliveryaddress> listDeliveryAddressByUserId(String userId) throws SQLException {
		// 根据用户编号查询所属送货地址
		List<Deliveryaddress> list = new ArrayList<>();
		// 查询sql语句
		String sql = "select * from deliveryaddress where userId = ?";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, userId);
			// 执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			// 遍历结果集，并将每行数据保存到List集合中
			while (rs.next()) {
				Deliveryaddress deliveryaddress = new Deliveryaddress();
				deliveryaddress.setDaId(rs.getInt("daId"));
				deliveryaddress.setContactName(rs.getString("contactName"));
				deliveryaddress.setContactSex(rs.getInt("contactSex"));
				deliveryaddress.setContactTel(rs.getString("contactTel"));
				deliveryaddress.setAddress(rs.getString("address"));
				deliveryaddress.setUserId(rs.getString("userId"));
				list.add(deliveryaddress);
			}
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return list;
	}
	
	@Override
	public Deliveryaddress getDeliveryAddressById(Integer daId) throws SQLException {
		// 根据送货地址编号查询送货地址
		Deliveryaddress deliveryaddress = null;
		// 查询sql语句
		String sql = "select * from deliveryaddress where daId = ?";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setInt(1, daId);
			// 执行查询，并将查询结果放到结果集里
			rs = pst.executeQuery();
			// 遍历结果集，并将每行数据保存到List集合中
			while (rs.next()) {
				deliveryaddress = new Deliveryaddress();
				deliveryaddress.setDaId(rs.getInt("daId"));
				deliveryaddress.setContactName(rs.getString("contactName"));
				deliveryaddress.setContactSex(rs.getInt("contactSex"));
				deliveryaddress.setContactTel(rs.getString("contactTel"));
				deliveryaddress.setAddress(rs.getString("address"));
				deliveryaddress.setUserId(rs.getString("userId"));
			}
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(rs, pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return deliveryaddress;
	}
	
	@Override
	public Integer saveDeliveryAddress(String contactName, Integer contactSex, String contactTel, String address, String userId) throws SQLException {
		// 向送货地址表中添加一条记录
		int row = 0;
		// 查询sql语句
		String sql = "insert into deliveryaddress(contactName,contactSex,contactTel,address,userId) values(?,?,?,?,?)";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, contactName);
			pst.setInt(2, contactSex);
			pst.setString(3, contactTel);
			pst.setString(4, address);
			pst.setString(5, userId);
			// 返回影响行数
			row = pst.executeUpdate();
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return row;
	}
	
	@Override
	public Integer updateDeliveryAddress(Integer daId, String contactName, Integer contactSex, String contactTel, String address, String userId) throws SQLException {
		// 根据送货地址编号更新送货地址信息
		// 查询sql语句
		String sql = "update deliveryaddress set contactName = ?,contactSex = ?,contactTel = ?,address = ?,userId = ? where daId = ?";
		int row = 0;
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setString(1, contactName);
			pst.setInt(2, contactSex);
			pst.setString(3, contactTel);
			pst.setString(4, address);
			pst.setString(5, userId);
			pst.setInt(6, daId);
			// 返回影响行数
			row = pst.executeUpdate();
		} finally {
			// 这里不能处理异常，也就是没有catch，只有finally
			DBUtil.close(pst);
			// 这里负责关闭PreparedStatement和ResultSet
		}
		return row;
	}
	
	@Override
	public Integer removeDeliveryAddress(Integer daId) throws SQLException {
		// 根据送货地址编号删除一条记录
		int row = 0;
		// 查询sql语句
		String sql = "delete from deliveryaddress where daId = ?";
		try {
			// Connection从ThreadLocal中获取
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			// 将？替换成具体的值
			pst.setInt(1, daId);
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
