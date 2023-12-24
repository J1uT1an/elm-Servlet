package com.luxintong.elm.dao;

import com.luxintong.elm.po.Food;

import java.sql.SQLException;
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
	public List<Food> listFoodByBusinessId(Integer businessId) throws SQLException;
}
