package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.OrderDetailet;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao
 * @className: OrderDetailetDao
 * @author: Lu Xintong
 * @description <p>OrderDetailetDao</p>
 * @date: 2023-12-15 17:10
 * @version: 1.0
 */
public interface OrderDetailetDao {
	Integer saveOrderDetailetBatch(List<OrderDetailet> list) throws Exception;
	
	List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId) throws Exception;
}
