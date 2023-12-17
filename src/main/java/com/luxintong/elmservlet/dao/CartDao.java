package com.luxintong.elmservlet.dao;

import com.luxintong.elmservlet.po.Cart;

import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.dao
 * @className: CartDao
 * @author: Lu Xintong
 * @description <p>CartDao</p>
 * @date: 2023-12-15 17:10
 * @version: 1.0
 */
public interface CartDao {
	/**
	 * 向购物车表中添加一条记录
	 *
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	Integer saveCart(Cart cart) throws Exception;
	
	/**
	 * 根据用户编号、商家编号、食品编号更新数量
	 *
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	Integer updateCart(Cart cart) throws Exception;
	
	/**
	 * 根据用户编号、商家编号、食品编号删除购物车表中的一条食品记录​ 根据用户编号、商家编号删除购物车表中的多条条记录
	 *
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	Integer removeCart(Cart cart) throws Exception;
	
	/**
	 * 根据用户编号查询此用户所有购物车信息​ 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
	 *
	 * @param cart
	 * @return
	 * @throws Exception
	 */
	List<Cart> listCart(Cart cart) throws Exception;
}
