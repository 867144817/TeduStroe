package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/*
 * 继承HandlerInterceptorAdapter的好处是
 * 可以减少实现HandlerInterceptor接口时候的空方法
 * 称为：适配器(Adapter)模式
 */
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AccessInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user = request.getSession().getAttribute("user");
		if(user == null) {
			System.out.println("没有登陆");
			String path = request.getContextPath()+"/user/showLogin.do";
			response.sendRedirect(path);	
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	
}
