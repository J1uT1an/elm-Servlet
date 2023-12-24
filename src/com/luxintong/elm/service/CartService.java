package com.luxintong.elm.service;

import com.luxintong.elm.po.Cart;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service
 * @className: CartService
 * @author: Lu Xintong
 * @description <p>CartService</p>
 * @date: 2023-12-15 17:17
 * @version: 1.0
 */
public interface CartService {
	/**
	 * 根据用户编号查询此用户所有购物车信息​ 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
	 *
	 * @param cart
	 * @return
	 */
	public List<Cart> listCart(Cart cart);
	
	/**
	 * 向购物车表中添加一条记录
	 *
	 * @param userId
	 * @param businessId
	 * @param foodId
	 * @return
	 */
	public Integer saveCart(String userId, Integer businessId, Integer foodId);
	
	/**
	 * 根据用户编号、商家编号、食品编号更新数量
	 *
	 * @param userId
	 * @param businessId
	 * @param foodId
	 * @param quantity
	 * @return
	 */
	public Integer updateCart(String userId, Integer businessId, Integer foodId, Integer quantity);
	
	/**
	 * 根据用户编号、商家编号、食品编号删除购物车表中的一条食品记录​ 根据用户编号、商家编号删除购物车表中的多条条记录
	 *
	 * @param userId
	 * @param businessId
	 * @param foodId
	 * @return
	 */
	public Integer removeCart(String userId, Integer businessId, Integer foodId);
}
