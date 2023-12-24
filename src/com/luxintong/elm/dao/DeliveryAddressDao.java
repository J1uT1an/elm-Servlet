package com.luxintong.elm.dao;

import com.luxintong.elm.po.Deliveryaddress;

import java.sql.SQLException;
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
	public List<Deliveryaddress> listDeliveryAddressByUserId(String userId) throws SQLException;
	
	/**
	 * 根据送货地址编号查询送货地址
	 *
	 * @param daId
	 * @return
	 */
	public Deliveryaddress getDeliveryAddressById(Integer daId) throws SQLException;
	
	/**
	 * 向送货地址表中添加一条记录
	 *
	 * @param contactName
	 * @param contactSex
	 * @param contactTel
	 * @param address
	 * @param userId
	 * @return
	 */
	public Integer saveDeliveryAddress(String contactName, Integer contactSex, String contactTel, String address, String userId) throws SQLException;
	
	/**
	 * 根据送货地址编号更新送货地址信息
	 *
	 * @param daId
	 * @param contactName
	 * @param contactSex
	 * @param contactTel
	 * @param address
	 * @param userId
	 * @return
	 */
	public Integer updateDeliveryAddress(Integer daId, String contactName, Integer contactSex, String contactTel, String address, String userId) throws SQLException;
	
	/**
	 * 根据送货地址编号删除一条记录
	 *
	 * @param daId
	 * @return
	 */
	public Integer removeDeliveryAddress(Integer daId) throws SQLException;
}
