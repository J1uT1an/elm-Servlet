package com.luxintong.elmservlet.service.impl;

import com.luxintong.elmservlet.dao.CartDao;
import com.luxintong.elmservlet.dao.impl.CartDaoImpl;
import com.luxintong.elmservlet.po.Cart;
import com.luxintong.elmservlet.service.CartService;
import com.luxintong.elmservlet.util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: CartServiceImpl
 * @author: Lu Xintong
 * @description <p>CartServiceImpl</p>
 * @date: 2023-12-15 17:18
 * @version: 1.0
 */
public class CartServiceImpl implements CartService {
	@Override
	public List<Cart> listCart(Cart cart) {
		List<Cart> list = new ArrayList<>();
		CartDao cartDao = new CartDaoImpl();
		try {
			list = cartDao.listCart(cart);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
	
	@Override
	public Integer saveCart(String userId, Integer businessId, Integer foodId) {
		CartDao cartDao = new CartDaoImpl();
		int row = 0;
		try {
			//开启一个事物
			DBUtil.beginTransaction();
			row = cartDao.saveCart(userId, businessId, foodId);
			DBUtil.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	@Override
	public Integer updateCart(String userId, Integer businessId, Integer foodId, Integer quantity) {
		
		CartDao cartDao = new CartDaoImpl();
		int row = 0;
		try {
			row = cartDao.updateCart(userId, businessId, foodId, quantity);
			DBUtil.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return row;
	}
	
	@Override
	public Integer removeCart(String userId, Integer businessId, Integer foodId) {
		CartDao cartDao = new CartDaoImpl();
		int row = 0;
		try {
			row = cartDao.removeCart(userId, businessId, foodId);
			DBUtil.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			DBUtil.close();
		}
		return row;
	}
}
