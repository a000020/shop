package cn.tedu.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登入攔截器
 * @author User
 *
 */

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//驗證用戶是否登入,已登入放行,未登入重定向到登入頁面
		//獲取Session對象
		HttpSession session = request.getSession();
		if(session.getAttribute("uid")==null){
			//重定向到登入頁面
			response.sendRedirect("/web/login.html");
			//執行攔截
			return false;
		}
		
		return true;
	}
	
}
