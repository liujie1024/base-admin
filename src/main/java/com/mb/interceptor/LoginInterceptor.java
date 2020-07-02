package com.mb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mb.util.MbConstant;

/**
 * 用户登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String path = request.getContextPath();
		HttpSession session = request.getSession(true);
		Object obj = session.getAttribute(MbConstant.USERINFO);// 从session里面获取用户名的信息
		if (obj == null || "".equals(obj.toString())) {// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
			response.sendRedirect(path + "/login/sessionTime.do");
		}
		return true;
	}

}
