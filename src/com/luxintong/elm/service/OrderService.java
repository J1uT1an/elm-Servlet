package com.luxintong.elm.service;

import com.luxintong.elm.po.Orders;

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
public interface OrderService {
	
	/**
	 * 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
	 *
	 * @param userId
	 * @param businessId
	 * @param orderTotal
	 * @param daId
	 * @return
	 */
	public Integer saveOrdersById(String userId, Integer businessId, Double orderTotal, Integer daId);
	
	/**
	 * 合成方法
	 * 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
	 * 然后根据用户编号、商家编号从购物车表中查询所有数据，
	 * 批量添加到订单明细表中，​
	 * 然后根据用户编号、商家编号删除购物车表中的数据。
	 *
	 * @param userId
	 * @param businessId
	 * @param daId
	 * @param orderTotal
	 * @return
	 */
	public Integer createOrders(String userId, Integer businessId, Integer daId, Double orderTotal);
	
	/**
	 * 功能：根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	 *
	 * @param orderId
	 * @return
	 */
	public Orders getOrdersById(Integer orderId);
	
	/**
	 * 功能：根据用户编号查询此用户的所有订单信息
	 *
	 * @param userId
	 * @return
	 */
	public List<Orders> listOrdersByUserId(String userId);
	
}
