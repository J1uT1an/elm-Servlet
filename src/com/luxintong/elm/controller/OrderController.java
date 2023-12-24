package com.luxintong.elm.controller;

import com.luxintong.elm.po.Orders;
import com.luxintong.elm.service.OrderService;
import com.luxintong.elm.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: OrderController
 * @author: Lu Xintong
 * @description <p>OrderController</p>
 * @date: 2023-12-15 17:14
 * @version: 1.0
 */
public class OrderController {
	public Object saveOrdersById(HttpServletRequest request) {
		// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
		// public Integer saveOrdersById(String userId,Integer businessId,Double orderTotal,Integer daId);
		// 获取前台的请求参数，/OrderController/saveOrdersById?userId=17838530529&businessId=10001&orderTotal=98&daId=2
		String userId = request.getParameter("userId");
		Integer businessId = Integer.parseInt(request.getParameter("businessId"));
		Double orderTotal = Double.valueOf(request.getParameter("orderTotal"));
		Integer daId = Integer.parseInt(request.getParameter("daId"));
		OrderService orderService = new OrderServiceImpl();
		int row = orderService.saveOrdersById(userId, businessId, orderTotal, daId);
		return row;
	}
	
	public Object createOrders(HttpServletRequest request) {
		/**    合成方法 -- 未实装 TODO: 2023/12/17
		 * 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
		 * 然后根据用户编号、商家编号从购物车表中查询所有数据，
		 * 批量添加到订单明细表中，​
		 * 然后根据用户编号、商家编号删除购物车表中的数据。
		 * */
		// public Integer createOrders(String userId, Integer businessId, Integer daId, Double orderTotal)
		//  获取前台的请求参数，/OrderController/createOrders?userId=17838530529&businessId=10001&daId=2&orderTotal=98
		String userId = request.getParameter("userId");
		Integer businessId = Integer.parseInt(request.getParameter("businessId"));
		Integer daId = Integer.parseInt(request.getParameter("daId"));
		Double orderTotal = Double.valueOf(request.getParameter("orderTotal"));
		OrderService orderService = new OrderServiceImpl();
		int row = orderService.createOrders(userId, businessId, daId, orderTotal);
		return row;
	}
	
	public Object getOrdersById(HttpServletRequest request) {
		// 功能：根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
		// public List<Orders> getOrdersById(Integer orderId)
		// 获取前台的请求参数，/OrderController/getOrdersById?orderId=6
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		OrderService orderService = new OrderServiceImpl();
		Orders orders = orderService.getOrdersById(orderId);
		return orders;
	}
	
	public Object listOrdersByUserId(HttpServletRequest request) {
		// 功能：根据用户编号查询此用户的所有订单信息
		// public List<Orders> listOrdersByUserId(String userId)
		// 获取前台的请求参数，/OrderController/listOrdersByUserId?userId=11111111111
		List<Orders> list = new ArrayList<>();
		String userId = request.getParameter("userId");
		OrderService orderService = new OrderServiceImpl();
		list = orderService.listOrdersByUserId(userId);
		return list;
	}
}