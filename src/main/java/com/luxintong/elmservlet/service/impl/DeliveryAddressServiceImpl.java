package com.luxintong.elmservlet.service.impl;

import com.luxintong.elmservlet.dao.DeliveryAddressDao;
import com.luxintong.elmservlet.dao.impl.DeliveryAddressDaoImpl;
import com.luxintong.elmservlet.po.DeliveryAddress;
import com.luxintong.elmservlet.service.DeliveryAddressService;
import com.luxintong.elmservlet.util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: DeliveryAddressServiceImpl
 * @author: Lu Xintong
 * @description <p>DeliveryAddressServiceImpl</p>
 * @date: 2023-12-15 17:18
 * @version: 1.0
 */
public class DeliveryAddressServiceImpl implements DeliveryAddressService {
	@Override
	// 根据用户编号查询所属送货地址
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		List<DeliveryAddress> list = new ArrayList<>();
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listDeliveryAddressByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
	
	@Override
	// 根据送货地址编号查询送货地址
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		DeliveryAddress deliveryAddress = null;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			deliveryAddress = dao.getDeliveryAddressById(daId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return deliveryAddress;
	}
	
	
	@Override
	// 向送货地址表中添加一条记录
	public Integer saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		int result = 0;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.saveDeliveryAddress(deliveryAddress);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
	
	@Override
	// 根据送货地址编号更新送货地址信息
	public Integer updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		int result = 0;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.updateDeliveryAddress(deliveryAddress);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
	
	@Override
	// 根据送货地址编号删除一条记录
	public Integer removeDeliveryAddress(Integer daId) {
		DeliveryAddressDao deliveryAddressDao = new DeliveryAddressDaoImpl();
		int row = 0;
		try {
			// 开启一个事物
			DBUtil.beginTransaction();
			row = deliveryAddressDao.removeDeliveryAddress(daId);
			DBUtil.getConnection().commit();
		} catch (SQLException e) {
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
