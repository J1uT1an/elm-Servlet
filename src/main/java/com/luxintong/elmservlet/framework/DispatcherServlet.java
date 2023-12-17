package com.luxintong.elmservlet.framework;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.framework
 * @className: DispatcherServlet
 * @author: Lu Xintong
 * @description DispatcherServlet类 自定义前端控制器拦截url格式要求： /控制器类名/控制器方法名
 * <p>
 * 1. 本工程采用：基于Servlet的简易MVC架构。
 * 2. 本工程采用：约定优于配置的原则来搭建简易MVC框架。
 * 3. 本工程中规定：请求url与Controller方法映射示例：http://localhost:8080/elm/Controller类名/Controller方法名
 * 4. 本工程中不需要任何配置文件。
 * </p>
 * @date: 2023-12-15 17:15
 * @version: 1.0
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 中文编码处理
		 * 处理字符编码(因为目前只有一个 Servlet, 所以先在 Servlet 里处理，没有用过滤器)
		 * */
		// 客户端向服务器端请求的编码格式（否则会产生乱码）  默认： IOS8859-1
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 设置从服务器端向客户端返回的内容的类型   MIME类型：application/json
		response.setContentType("application/json;charset=UTF-8");
		
		// 获取到客户端的请求路径(url的部分路径 /Controller类名/Controller方法名)
		String path = request.getServletPath();
		
		// 根据请求路径,解析 Controller 的类名和方法名
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/") + 1);
		
		PrintWriter out = null;
		
		// 判断请求路径，根据不同的请求，分发给不同的业务处理器
		try {
			// 通过 Controller 类全名获取此类的所有信息
			Class clazz = Class.forName("com.luxintong.elmservlet.controller." + className);
			// 将获取到的 clazz 类转换为对象(Controller 的对象)
			Object controller = clazz.newInstance();
			// 通过 clazz.getMethod() 获取 Controller 类对象中的方法, new Class[]{HttpServletRequest.class}形参
			Method method = clazz.getMethod(methodName, HttpServletRequest.class);
			// 调用获取到的方法 method.invoke(), new Object[] {request}
			Object result = method.invoke(controller, request);
			
			// 获取向客户端响应的输出流, 用于向网页输出内容
			out = response.getWriter();
			ObjectMapper objectMapper = new ObjectMapper();
			// 将处理结果 result 以 json 的格式响应给客户端浏览器
			out.print(objectMapper.writeValueAsString(result));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DispatcherServlet信息：请求 url: " + path);
			System.out.println("DispatcherServlet信息：类名: " + className + "\t方法名：" + methodName);
		} finally {
			out.close();
		}
	}
}
