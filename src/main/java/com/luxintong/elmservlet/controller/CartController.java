package com.luxintong.elmservlet.controller;

import com.luxintong.elmservlet.po.Cart;
import com.luxintong.elmservlet.service.CartService;
import com.luxintong.elmservlet.service.impl.CartServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: CartController
 * @author: Lu Xintong
 * @description <p>CartController</p>
 * @date: 2023-12-15 17:14
 * @version: 1.0
 */
public class CartController {
	public Object listCart(HttpServletRequest request) {
		// 根据用户编号查询此用户所有购物车信息 ​ 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
		// public List<Deliveryaddress> listDeliveryAddressByUserId(String userId);
		// 获取前台的请求参数，/CartController/listCart?userId=12345671111&businessId=10001,并将其强转为Integer类型
		Cart cart = new Cart();
		cart.setUserId(request.getParameter("userId"));
		if (request.getParameter("businessId") != null) {
			cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		}
		CartService service = new CartServiceImpl();
		List<Cart> list = service.listCart(cart);
		return list;
	}
	
	public Object saveCart(HttpServletRequest request) {
		// 向购物车表中添加一条记录
		// public Integer saveCart(String userId,Integer businessId,Integer foodId) throws SQLException;
		// 获取前台的请求参数，/CartController/saveCart?userId=123123&businessId=10003&foodId=8,并将其强转为Integer类型
		Cart cart = new Cart();
		cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
		cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		cart.setUserId(request.getParameter("userId"));
		CartService service = new CartServiceImpl();
		int result = service.saveCart(cart);
		return result;
	}
	
	public Object updateCart(HttpServletRequest request) {
		// 根据用户编号、商家编号、食品编号更新数量
		// public Integer updateCart(String userId,Integer businessId,Integer foodId,Integer quantity);
		// 获取前台的请求参数，/CartController/updateCart?userId=123123&businessId=10003&foodId=8&quantity=15,并将其强转为Integer类型
		Cart cart = new Cart();
		cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
		cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		cart.setUserId(request.getParameter("userId"));
		cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
		CartService service = new CartServiceImpl();
		int result = service.updateCart(cart);
		return result;
	}
	
	public Object removeCart(HttpServletRequest request) {
		// 据用户编号、商家编号、食品编号删除购物车表中的一条食品记录​ 根据用户编号、商家编号删除购物车表中的多条条记录
		//  public Integer removeCart(String userId,Integer businessId,Integer foodId)
		// 获取前台的请求参数，/CartController/removeCart?userId=4522&businessId=10002&foodId=5,并将其强转为Integer类型
		Cart cart = new Cart();
		cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
		cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		cart.setUserId(request.getParameter("userId"));
		CartService service = new CartServiceImpl();
		int result = service.removeCart(cart);
		return result;
	}
}
