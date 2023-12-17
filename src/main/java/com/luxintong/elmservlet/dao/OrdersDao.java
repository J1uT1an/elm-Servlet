package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.Orders;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao
 * @className: OrdersDao
 * @author: Lu Xintong
 * @description <p>OrdersDao</p>
 * @date: 2023-12-15 17:11
 * @version: 1.0
 */
public interface OrdersDao {
	Integer saveOrders(Orders orders) throws Exception;
	
	Orders getOrdersById(Integer orderId) throws Exception;
	
	List<Orders> listOrdersByUserId(String userId) throws Exception;
	
}
