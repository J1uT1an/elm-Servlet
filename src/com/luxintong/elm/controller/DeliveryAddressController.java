package com.luxintong.elm.controller;

import com.luxintong.elm.po.Deliveryaddress;
import com.luxintong.elm.service.DeliveryAddressService;
import com.luxintong.elm.service.impl.DeliveryAddressServiceImpl;

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
	public Object listDeliveryAddressByUserId(HttpServletRequest request) {
		// 根据用户编号查询所属送货地址
		// public List<Deliveryaddress> listDeliveryAddressByUserId(String userId);
		// 获取前台的请求参数，/DeliveryaddressController/listDeliveryAddressByUserId?userId=11111111111,并将其强转为Integer类型
		String userId = request.getParameter("userId");
		DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();
		List<Deliveryaddress> list = deliveryAddressService.listDeliveryAddressByUserId(userId);
		return list;
	}
	
	public Object getDeliveryAddressById(HttpServletRequest request) {
		//  根据送货地址编号查询送货地址
		// public Deliveryaddress getDeliveryAddressById(Integer daId);
		// 获取前台的请求参数，/DeliveryaddressController/getDeliveryAddressById?daId=5,并将其强转为Integer类型
		Integer daId = Integer.parseInt(request.getParameter("daId"));
		DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();
		Deliveryaddress deliveryaddress = new Deliveryaddress();
		deliveryaddress = deliveryAddressService.getDeliveryAddressById(daId);
		return deliveryaddress;
	}
	
	public Object saveDeliveryAddress(HttpServletRequest request) {
		// 向送货地址表中添加一条记录
		// public Integer saveDeliveryAddress (String contactName,Integer contactSex,String contactTel,String address,String userId)
		// 获取前台的请求参数，/DeliveryaddressController/saveDeliveryAddress?contactName=henan&contactSex=0&contactTel=11654&address=pingdingshan&userId=13568682444,并将其强转为Integer类型
		String contactName = request.getParameter("contactName");
		Integer contactSex = Integer.parseInt(request.getParameter("contactSex"));
		String contactTel = request.getParameter("contactTel");
		String address = request.getParameter("address");
		String userId = request.getParameter("userId");
		DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();
		int row = deliveryAddressService.saveDeliveryAddress(contactName, contactSex, contactTel, address, userId);
		return row;
	}
	
	public Object updateDeliveryAddress(HttpServletRequest request) {
		// 根据送货地址编号更新送货地址信息
		// public Integer updateDeliveryAddress (Integer daId,String contactName,Integer contactSex,String contactTel,String address,String userId)
		// 获取前台的请求参数，/DeliveryaddressController/updateDeliveryAddress?daId=2&contactName=beijing&contactSex=0&contactTel=11654&address=pingdingshan&userId=13568682444,并将其强转为Integer类型
		Integer daId = Integer.parseInt(request.getParameter("daId"));
		String contactName = request.getParameter("contactName");
		Integer contactSex = Integer.parseInt(request.getParameter("contactSex"));
		String contactTel = request.getParameter("contactTel");
		String address = request.getParameter("address");
		String userId = request.getParameter("userId");
		DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();
		int row = deliveryAddressService.updateDeliveryAddress(daId, contactName, contactSex, contactTel, address, userId);
		
		return row;
	}
	
	public Object removeDeliveryAddress(HttpServletRequest request) {
		// 根据送货地址编号删除一条记录
		// public Integer removeDeliveryAddress (Integer daId)
		// 获取前台的请求参数，/DeliveryaddressController/removeDeliveryAddress?daId=3,并将其强转为Integer类型
		Integer daId = Integer.parseInt(request.getParameter("daId"));
		DeliveryAddressService deliveryAddressService = new DeliveryAddressServiceImpl();
		int row = deliveryAddressService.removeDeliveryAddress(daId);
		
		return row;
	}
}
