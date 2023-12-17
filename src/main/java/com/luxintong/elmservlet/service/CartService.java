package com.luxintong.elmservlet.service;

import com.luxintong.elmservlet.po.Cart;

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
	List<Cart> listCart(Cart cart);
	
	/**
	 * 向购物车表中添加一条记录
	 *
	 * @param cart
	 * @return
	 */
	Integer saveCart(Cart cart);
	
	/**
	 * 根据用户编号、商家编号、食品编号更新数量
	 *
	 * @param cart
	 * @return
	 */
	Integer updateCart(Cart cart);
	
	/**
	 * 根据用户编号、商家编号、食品编号删除购物车表中的一条食品记录​ 根据用户编号、商家编号删除购物车表中的多条条记录
	 *
	 * @param cart
	 * @return
	 */
	Integer removeCart(Cart cart);
}
