package com.luxintong.elmservlet.service.impl;

import com.luxintong.elmservlet.dao.CartDao;
import com.luxintong.elmservlet.dao.impl.CartDaoImpl;
import com.luxintong.elmservlet.po.Cart;
import com.luxintong.elmservlet.service.CartService;
import com.luxintong.elmservlet.util.DBUtil;

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
		List<Cart> list = new ArrayList();
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
	
	@Override
	public Integer saveCart(Cart cart) {
		int result = 0;
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.saveCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
	
	@Override
	public Integer updateCart(Cart cart) {
		int result = 0;
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.updateCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
	
	@Override
	public Integer removeCart(Cart cart) {
		int result = 0;
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.removeCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
}
