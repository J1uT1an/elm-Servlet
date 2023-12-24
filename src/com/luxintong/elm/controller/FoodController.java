package com.luxintong.elm.controller;

import com.luxintong.elm.po.Food;
import com.luxintong.elm.service.FoodService;
import com.luxintong.elm.service.impl.FoodServiceImpl;

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
	public Object listFoodByBusinessId(HttpServletRequest request) {
		// 获取前台的请求参数，/FoodController/listFoodByBusinessId?businessId=10001,并将其强转为Integer类型
		Integer businessId = Integer.parseInt(request.getParameter("businessId"));
		FoodService foodService = new FoodServiceImpl();
		List<Food> list = foodService.listFoodByBusinessId(businessId);
		return list;
	}
}
