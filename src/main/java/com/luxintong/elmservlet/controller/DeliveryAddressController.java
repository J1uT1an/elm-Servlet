package com.luxintong.elmservlet.controller;

import com.luxintong.elmservlet.po.DeliveryAddress;
import com.luxintong.elmservlet.service.DeliveryAddressService;
import com.luxintong.elmservlet.service.impl.DeliveryAddressServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: DeliveryAddressController
 * @author: Lu Xintong
 * @description <p>DeliveryAddressController</p>
 * @date: 2023-12-15 17:14
 * @version: 1.0
 */
public class DeliveryAddressController {
	public Object listDeliveryAddressByUserId(HttpServletRequest request) throws Exception {
		// 根据用户编号查询所属送货地址
		// public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
		// 获取前台的请求参数，/DeliveryAddressController/listDeliveryAddressByUserId?userId=11111111111
		String userId = request.getParameter("userId");
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		List<DeliveryAddress> list = service.listDeliveryAddressByUserId(userId);
		return list;
	}
	
	public Object getDeliveryAddressById(HttpServletRequest request) throws Exception {
		// 根据送货地址编号查询送货地址
		// public DeliveryAddress getDeliveryAddressById(Integer daId);
		// 获取前台的请求参数，/DeliveryAddressController/getDeliveryAddressById?daId=5
		Integer daId = Integer.valueOf(request.getParameter("daId"));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		DeliveryAddress deliveryAddress = service.getDeliveryAddressById(daId);
		return deliveryAddress;
	}
	
	public Object saveDeliveryAddress(HttpServletRequest request) throws Exception {
		// 向送货地址表中添加一条记录
		// public Integer saveDeliveryAddress (DeliveryAddress deliveryAddress)
		// 获取前台的请求参数，/DeliveryAddressController/saveDeliveryAddress?contactName=henan&contactSex=0&contactTel=11654&address=pingdingshan&userId=13568682444,并将其强转为Integer类型
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setContactName(request.getParameter("contactName"));
		deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
		deliveryAddress.setContactTel(request.getParameter("contactTel"));
		deliveryAddress.setAddress(request.getParameter("address"));
		deliveryAddress.setUserId(request.getParameter("userId"));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		int result = service.saveDeliveryAddress(deliveryAddress);
		return result;
	}
	
	public Object updateDeliveryAddress(HttpServletRequest request) throws Exception {
		// 根据送货地址编号更新送货地址信息
		// public Integer updateDeliveryAddress (DeliveryAddress deliveryAddress)
		// 获取前台的请求参数，/DeliveryAddressController/updateDeliveryAddress?daId=2&contactName=beijing&contactSex=0&contactTel=11654&address=pingdingshan&userId=13568682444,并将其强转为Integer类型
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setContactName(request.getParameter("contactName"));
		deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
		deliveryAddress.setContactTel(request.getParameter("contactTel"));
		deliveryAddress.setAddress(request.getParameter("address"));
		deliveryAddress.setDaId(Integer.valueOf(request.getParameter("daId")));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		int result = service.updateDeliveryAddress(deliveryAddress);
		return result;
	}
	
	public Object removeDeliveryAddress(HttpServletRequest request) throws Exception {
		// 根据送货地址编号删除一条记录
		// public Integer removeDeliveryAddress (Integer daId)
		// 获取前台的请求参数，/DeliveryAddressController/removeDeliveryAddress?daId=3,并将其强转为Integer类型
		Integer daId = Integer.valueOf(request.getParameter("daId"));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		int result = service.removeDeliveryAddress(daId);
		return result;
	}
}
