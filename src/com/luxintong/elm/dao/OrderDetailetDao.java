package com.luxintong.elm.dao;

import com.luxintong.elm.po.Orderdetailet;

import java.sql.SQLException;
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
	
	/**
	 * 批量添加到订单明细表中
	 *
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public Integer saveManyByOrderId(List<Orderdetailet> list) throws SQLException;
	
	/**
	 * 功能：根据订单编号查询所有订单明细信息
	 *
	 * @param orderId
	 * @return
	 */
	
	public List<Orderdetailet> getOrderdetailetById(Integer orderId) throws SQLException;
}
