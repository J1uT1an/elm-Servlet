package com.luxintong.elm.service;

import com.luxintong.elm.po.Food;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service
 * @className: FoodService
 * @author: Lu Xintong
 * @description <p>FoodService</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public interface FoodService {
	/**
	 * 根据商家编号查询所属食品信息
	 *
	 * @param businessId
	 * @return
	 */
	public List<Food> listFoodByBusinessId(Integer businessId);
}
