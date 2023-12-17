package com.luxintong.elmservlet.service.impl;

import com.luxintong.elmservlet.dao.FoodDao;
import com.luxintong.elmservlet.dao.impl.FoodDaoImpl;
import com.luxintong.elmservlet.po.Food;
import com.luxintong.elmservlet.service.FoodService;
import com.luxintong.elmservlet.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: FoodServiceImpl
 * @author: Lu Xintong
 * @description <p>FoodServiceImpl</p>
 * @date: 2023-12-15 17:18
 * @version: 1.0
 */
public class FoodServiceImpl implements FoodService {
	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) {
		List<Food> list = new ArrayList<>();
		FoodDao dao = new FoodDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listFoodByBusinessId(businessId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
}
