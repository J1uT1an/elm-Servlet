package com.luxintong.elmservlet.service.impl;

import com.luxintong.elmservlet.dao.BusinessDao;
import com.luxintong.elmservlet.dao.impl.BusinessDaoImpl;
import com.luxintong.elmservlet.po.Business;
import com.luxintong.elmservlet.service.BusinessService;
import com.luxintong.elmservlet.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: BusinessServiceImpl
 * @author: Lu Xintong
 * @description <p>BusinessServiceImpl</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public class BusinessServiceImpl implements BusinessService {
	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		List<Business> list = new ArrayList<>();
		BusinessDao dao = new BusinessDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listBusinessByOrderTypeId(orderTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
	
	@Override
	public Business getBusinessById(Integer businessId) {
		Business business = null;
		BusinessDao dao = new BusinessDaoImpl();
		try {
			DBUtil.getConnection();
			business = dao.getBusinessById(businessId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return business;
	}
}
