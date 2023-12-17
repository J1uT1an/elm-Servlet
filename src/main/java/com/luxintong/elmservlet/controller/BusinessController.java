package com.luxintong.elmservlet.controller;

import com.luxintong.elmservlet.po.Business;
import com.luxintong.elmservlet.service.BusinessService;
import com.luxintong.elmservlet.service.impl.BusinessServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: BusinessController
 * @author: Lu Xintong
 * @description <p>BusinessController</p>
 * @date: 2023-12-15 17:13
 * @version: 1.0
 */
public class BusinessController {
	public Object listBusinessByOrderTypeId(HttpServletRequest request) {
		// 根据点餐分类编号查询所属商家信息
		// 获取前台的请求参数，/BusinessController/listBusinessByOrderTypeId?orderTypeId=1
		Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
		BusinessService service = new BusinessServiceImpl();
		List<Business> list = service.listBusinessByOrderTypeId(orderTypeId);
		return list;
	}
	
	public Business getBusinessById(HttpServletRequest request) {
		// 根据商家编号查询商家信息
		// 获取前台的请求参数，/BusinessController/getBusinessById?BusinessId=10001
		Integer businessId = Integer.valueOf(request.getParameter("businessId"));
		BusinessService service = new BusinessServiceImpl();
		Business business = service.getBusinessById(businessId);
		return business;
	}
}
