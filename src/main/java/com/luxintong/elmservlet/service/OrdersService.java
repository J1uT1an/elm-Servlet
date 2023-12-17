package com.luxintong.elmservlet.service;

import com.luxintong.elmservlet.po.Orders;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service
 * @className: OrderService
 * @author: Lu Xintong
 * @description <p>OrderService</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public interface OrdersService {
	Integer createOrders(String userId, Integer businessId, Integer daId, Double orderTotal);
	
	Orders getOrdersById(Integer orderId);
	
	List<Orders> listOrdersByUserId(String userId);
}
