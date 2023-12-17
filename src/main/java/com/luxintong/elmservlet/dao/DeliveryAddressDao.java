package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.DeliveryAddress;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao
 * @className: DeliveryAddressDao
 * @author: Lu Xintong
 * @description <p>DeliveryAddressDao</p>
 * @date: 2023-12-15 17:10
 * @version: 1.0
 */
public interface DeliveryAddressDao {
	/**
	 * 根据用户编号查询所属送货地址
	 *
	 * @param userId
	 * @return
	 */
	List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception;
	
	/**
	 * 根据送货地址编号查询送货地址
	 *
	 * @param daId
	 * @return
	 */
	DeliveryAddress getDeliveryAddressById(Integer daId) throws Exception;
	
	/**
	 * 向送货地址表中添加一条记录
	 *
	 * @param deliveryAddress
	 * @return
	 */
	Integer saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;
	
	/**
	 * 根据送货地址编号更新送货地址信息
	 *
	 * @param deliveryAddress
	 * @return
	 */
	Integer updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;
	
	/**
	 * 根据送货地址编号删除一条记录
	 *
	 * @param daId
	 * @return
	 */
	Integer removeDeliveryAddress(Integer daId) throws Exception;
	
}
