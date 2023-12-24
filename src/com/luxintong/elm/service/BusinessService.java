package com.luxintong.elm.service;

import com.luxintong.elm.po.Business;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: BusinessService
 * @author: Lu Xintong
 * @description <p>BusinessService</p>
 * @date: 2023-12-15 17:16
 * @version: 1.0
 */
public interface BusinessService {
	/**
	 * 根据点餐分类编号查询所属商家信息
	 *
	 * @param orderTypeId
	 * @return
	 */
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	
	/**
	 * 根据商家编号查询商家信息
	 *
	 * @param businessId
	 * @return
	 */
	public Business getBusinessById(Integer businessId);
}
