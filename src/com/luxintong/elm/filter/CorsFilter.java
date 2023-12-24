package com.luxintong.elm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: <h3>elm-Servlet</h3>
 * @package: com.luxintong.elmservlet.filter
 * @className: CorsFilter
 * @author: Lu Xintong
 * @description <p>CorsFilter</p>
 * @date: 2023-12-15 17:15
 * @version: 1.0
 */
@WebFilter("/*")
public class CorsFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		//注意：这里设置只允许http://localhost:8081进行跨域访问
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3628800");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	@Override
	public void destroy() {
	}
}

