package com.luxintong.elm.service.impl;

import com.luxintong.elm.dao.DeliveryAddressDao;
import com.luxintong.elm.dao.impl.DeliveryAddressDaoImpl;
import com.luxintong.elm.po.Deliveryaddress;
import com.luxintong.elm.service.DeliveryAddressService;
import com.luxintong.elm.util.DBUtil;

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
	public List<Deliveryaddress> listDeliveryAddressByUserId(String userId) {
		List<Deliveryaddress> list = new ArrayList<>();
		DeliveryAddressDao deliveryAddressDao = new DeliveryAddressDaoImpl();
		try {
			list = deliveryAddressDao.listDeliveryAddressByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
	
	@Override
	// 根据送货地址编号查询送货地址
	public Deliveryaddress getDeliveryAddressById(Integer daId) {
		Deliveryaddress deliveryaddress = new Deliveryaddress();
		DeliveryAddressDao deliveryAddressDao = new DeliveryAddressDaoImpl();
		try {
			deliveryaddress = deliveryAddressDao.getDeliveryAddressById(daId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return deliveryaddress;
	}
	
	@Override
	// 向送货地址表中添加一条记录
	public Integer saveDeliveryAddress(String contactName, Integer contactSex, String contactTel, String address, String userId) {
		DeliveryAddressDao deliveryAddressDao = new DeliveryAddressDaoImpl();
		int row = 0;
		try {
			// 开启一个事物
			DBUtil.beginTransaction();
			row = deliveryAddressDao.saveDeliveryAddress(contactName, contactSex, contactTel, address, userId);
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
	
	@Override
	// 根据送货地址编号更新送货地址信息
	public Integer updateDeliveryAddress(Integer daId, String contactName, Integer contactSex, String contactTel, String address, String userId) {
		DeliveryAddressDao deliveryAddressDao = new DeliveryAddressDaoImpl();
		int row = 0;
		try {
			// 开启一个事物
			DBUtil.beginTransaction();
			row = deliveryAddressDao.updateDeliveryAddress(daId, contactName, contactSex, contactTel, address, userId);
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
