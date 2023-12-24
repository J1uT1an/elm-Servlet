package com.luxintong.elm.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.controller
 * @className: LoginController
 * @author: Lu Xintong
 * @description <p>LoginController</p>
 * TODO:一个正常的java类，不是Servlet，无需继承HttpServlet -- 未实装
 * @date: 2023-12-15 17:14
 * @version: 1.0
 */
public class LoginController {
	public Object getAll(HttpServletRequest request) {
		List<String> list = new ArrayList<>();
		list.add("fender");
		list.add("delete");
		list.add("ok");
		return list;
	}
	
}
