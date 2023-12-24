package com.luxintong.elm.service;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service
 * @className: OrderDetailetService
 * @author: Lu Xintong
 * @description <p>OrderDetailetService</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public interface OrderDetailetService {
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
}
