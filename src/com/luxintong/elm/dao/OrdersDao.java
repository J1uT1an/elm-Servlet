package com.luxintong.elm.dao;


import com.luxintong.elm.po.Orders;

import java.sql.SQLException;
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
	/**
	 * 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
	 *
	 * @param userId
	 * @param businessId
	 * @param orderTotal
	 * @param daId
	 * @return
	 */
	public Integer saveOrdersById(String userId, Integer businessId, Double orderTotal, Integer daId) throws SQLException;
	
	
	/**
	 * 功能：根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	 *
	 * @param orderId
	 * @return
	 */
	public Orders getOrdersById(Integer orderId) throws SQLException;
	
	/**
	 * 功能：根据用户编号查询此用户的所有订单信息
	 *
	 * @param userId
	 * @return
	 */
	public List<Orders> listOrdersByUserId(String userId) throws SQLException;
}
