package com.luxintong.elm.framework;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.处理字符编码（因为目前只有一个servlet，所以先在servlet里处理，没有用过滤器）
		// 客户端向服务器端请求的编码格式（否则会产生乱码）  默认： IOS8859-1
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 设置从服务器端向客户端返回的内容的类型   MIME类型：application/json
		response.setContentType("application/json;charset=UTF-8");
		
		// 获取打印流用于向网页输出内容
		PrintWriter out = response.getWriter();
		
		// 2.获取到客户端的请求路径(url的部分路径) /Controller类名/Controller方法名
		String path = request.getServletPath();
		// 3.根据获取到的路径得到类名和方法名
		String className = path.substring(1, path.lastIndexOf("/"));
		String methodName = path.substring(path.lastIndexOf("/") + 1);
		
		try {
			// 4.通过Class.forName()获取到Controller中类的信息
			Class clazz = Class.forName("com.luxintong.elm.controller." + className);
			// 将获取到的clazz类转换为对象（LoginController的对象）
			Object controller = clazz.newInstance();
			// 5.通过clazz.getMethod()获取clazz中的方法,,new Class[]{HttpServletRequest.class}形参
			Method method = clazz.getMethod(methodName, new Class[]{HttpServletRequest.class});
			// 6.调用方法method.invoke()
			Object result = method.invoke(controller, new Object[]{request});
			// 7.将处理结果result以json的格式响应给客户端浏览器
			ObjectMapper objectMapper = new ObjectMapper();
			out.print(objectMapper.writeValueAsString(result));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
