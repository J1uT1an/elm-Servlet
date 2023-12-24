package com.luxintong.elm.service.impl;

import com.luxintong.elm.dao.BusinessDao;
import com.luxintong.elm.dao.CartDao;
import com.luxintong.elm.dao.OrderDetailetDao;
import com.luxintong.elm.dao.OrdersDao;
import com.luxintong.elm.dao.impl.BusinessDaoImpl;
import com.luxintong.elm.dao.impl.CartDaoImpl;
import com.luxintong.elm.dao.impl.OrderDetailetDaoImpl;
import com.luxintong.elm.dao.impl.OrdersDaoImpl;
import com.luxintong.elm.po.Business;
import com.luxintong.elm.po.Cart;
import com.luxintong.elm.po.Orderdetailet;
import com.luxintong.elm.po.Orders;
import com.luxintong.elm.service.OrderService;
import com.luxintong.elm.util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.service.impl
 * @className: OrderServiceImpl
 * @author: Lu Xintong
 * @description <p>OrderServiceImpl</p>
 * @date: 2023-12-15 17:18
 * @version: 1.0
 */
public class OrderServiceImpl implements OrderService {
	@Override
	// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，​ 并获取自动生成的订单编号，​
	public Integer saveOrdersById(String userId, Integer businessId, Double orderTotal, Integer daId) {
		int row = 0;
		OrdersDao ordersDao = new OrdersDaoImpl();
		try {
			DBUtil.beginTransaction();
			row = ordersDao.saveOrdersById(userId, businessId, orderTotal, daId);
			DBUtil.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.rollbackTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return row;
	}
	
	@Override
	// 合成方法
	public Integer createOrders(String userId, Integer businessId, Integer daId, Double orderTotal) {
		// 定义一个int变量，用来确认业务流程是否走完int deleteok，deleteok==0，说明没有成功，deleteok==1，说明成功了
		int deleteok = 0;
		// 通过saveOrdersById方法将数据添加成功后会获取到订单编号
		int orderId = saveOrdersById(userId, businessId, orderTotal, daId);
		// 在订单表中，根据saveOrdersById方法获取到的订单编号查询相应的UserId
		OrdersDao ordersDao = new OrdersDaoImpl();
		try {
			// 开启一个事物
			DBUtil.beginTransaction();
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setBusinessId(businessId);
			// 根据获取到的用户编号以及商家编号，查询当前用户当前订单购物车中的信息
			CartDao cartDao = new CartDaoImpl();
			// 将查询到的信息保存到list集合中
			List<Cart> list = cartDao.listCart(cart);
			// 创建一个orderdetailet类型的list集合，用来保存遍历List<Cart> list 之后的结果
			List<Orderdetailet> list1 = new ArrayList<>();
			// 遍历list集合，获取到foodId和quantity
			for (int i = 0; i < list.size(); i++) {
				Cart cart1 = list.get(i);
				int foodId = cart1.getFoodId();
				int quantity = cart1.getQuantity();
				// 创建一个Orderdetailet，将获取到的foodId和quantity的值赋值给Orderdetailet相应的值
				Orderdetailet orderdetailet = new Orderdetailet();
				orderdetailet.setFoodId(foodId);
				orderdetailet.setQuantity(quantity);
				orderdetailet.setOrderId(orderId);
				// 将设置完值的Orderdetailet对象，添加到List<Orderdetailet>集合中
				list1.add(orderdetailet);
			}
			// 遍历 List<Orderdetailet> list1集合，调用批量插入的方法，将数据批量插入到订单明细表中
			OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
			int row = orderDetailetDao.saveManyByOrderId(list1);
			// 如果添加成功的话，就调用cartDao中的removeCart方法，清空购物车中购买的食物
			if (row != 0) {
				deleteok = cartDao.removeCart(userId, businessId, null);
			}
			DBUtil.getConnection().commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		if (deleteok != 0) {
			return orderId;
		}
		return null;
	}
	
	@Override
	// 功能：根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	public Orders getOrdersById(Integer orderId) {
		Orders orders = null;
		OrdersDao ordersDao = new OrdersDaoImpl();
		OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
		BusinessDao businessDao = new BusinessDaoImpl();
		try {
			// 开启一个事物
			DBUtil.beginTransaction();
			// 调用orderDao中的方法，根据订单编号查询订单信息
			orders = ordersDao.getOrdersById(orderId);
			// 获取orders中的business的值
			int businessId = orders.getBusinessId();
			// 调用businessDao中的方法获取到商家信息
			Business business = businessDao.getBusinessById(businessId);
			// 调用orderDetailetDao中的方法获取到订单明细的信息
			List<Orderdetailet> list = orderDetailetDao.getOrderdetailetById(orderId);
			// 给orders对象设置business，list，foodlist
			orders.setBusiness(business);
			orders.setList(list);
			DBUtil.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				DBUtil.rollbackTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return orders;
	}
	
	@Override
	// 功能：根据用户编号查询此用户的所有订单信息
	public List<Orders> listOrdersByUserId(String userId) {
		List<Orders> list = new ArrayList<>();
		OrdersDao ordersDao = new OrdersDaoImpl();
		OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();
		BusinessDao businessDao = new BusinessDaoImpl();
		try {
			// 开启一个事物
			DBUtil.beginTransaction();
			list = ordersDao.listOrdersByUserId(userId);
			// 获取orders中的business的值
			for (int i = 0; i < list.size(); i++) {
				int businessId = list.get(i).getBusinessId();
				
				int orderId = list.get(i).getOrderId();
				// 调用businessDao中的方法获取到商家信息
				Business business = businessDao.getBusinessById(businessId);
				// 调用orderDetailetDao中的方法获取到订单明细的信息
				List<Orderdetailet> orderdetailetlist = orderDetailetDao.getOrderdetailetById(orderId);
				// 给orders对象设置business，list，foodlist
				list.get(i).setBusiness(business);
				list.get(i).setList(orderdetailetlist);
			}
			DBUtil.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				DBUtil.rollbackTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
}
