package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.Food;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao
 * @className: FoodDao
 * @author: Lu Xintong
 * @description <p>FoodDao</p>
 * @date: 2023-12-15 17:10
 * @version: 1.0
 */
public interface FoodDao {
	/**
	 * 根据商家编号查询所属食品信息
	 *
	 * @param businessId
	 * @return
	 */
	List<Food> listFoodByBusinessId(Integer businessId) throws Exception;
}
