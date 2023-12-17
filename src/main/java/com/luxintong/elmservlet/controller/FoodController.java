package com.luxintong.elmservlet.controller;

import com.luxintong.elmservlet.po.Food;
import com.luxintong.elmservlet.service.FoodService;
import com.luxintong.elmservlet.service.impl.FoodServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: FoodController
 * @author: Lu Xintong
 * @description <p>FoodController</p>
 * @date: 2023-12-15 17:14
 * @version: 1.0
 */
public class FoodController {
	public Object listFoodByBusinessId(HttpServletRequest request) throws Exception {
		// 获取前台的请求参数，/FoodController/listFoodByBusinessId?businessId=10001
		Integer businessId = Integer.valueOf(request.getParameter("businessId"));
		FoodService service = new FoodServiceImpl();
		List<Food> list = service.listFoodByBusinessId(businessId);
		return list;
	}
}
