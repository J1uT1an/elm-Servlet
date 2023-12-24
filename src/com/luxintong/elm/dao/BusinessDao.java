package com.luxintong.elm.dao;

import com.luxintong.elm.po.Business;

import java.sql.SQLException;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao.impl
 * @className: BusinessDao
 * @author: Lu Xintong
 * @description <p>BusinessDao</p>
 * @date: 2023-12-15 17:09
 * @version: 1.0
 */
public interface BusinessDao {
	/**
	 * 根据点餐分类编号查询所属商家信息
	 *
	 * @param orderTypeId
	 * @return
	 */
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws SQLException;
	
	/**
	 * 根据商家编号查询商家信息
	 *
	 * @param businessId
	 * @return
	 */
	public Business getBusinessById(Integer businessId) throws SQLException;
}
