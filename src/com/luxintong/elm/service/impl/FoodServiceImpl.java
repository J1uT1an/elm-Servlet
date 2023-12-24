package com.luxintong.elm.service.impl;

import com.luxintong.elm.dao.FoodDao;
import com.luxintong.elm.dao.impl.FoodDaoImpl;
import com.luxintong.elm.po.Food;
import com.luxintong.elm.service.FoodService;
import com.luxintong.elm.util.DBUtil;

import java.sql.SQLException;
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
		FoodDao foodDao = new FoodDaoImpl();
		try {
			list = foodDao.listFoodByBusinessId(businessId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
}
