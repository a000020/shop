package cn.tedu.shop.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.qos.logback.classic.pattern.MessageConverter;
import cn.tedu.shop.interceptor.LoginInterceptor;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//攔截器對象
		HandlerInterceptor interceptor = new LoginInterceptor();
		//白名單
		List<String> excludePaths = new ArrayList<>();
		excludePaths.add("/bootstrap3/**");
		excludePaths.add("/images/**");
		excludePaths.add("/js/**");
		excludePaths.add("/web/reg.html");
		excludePaths.add("/web/index.html");
		excludePaths.add("/web/login.html");
		excludePaths.add("/users/reg");
		excludePaths.add("/users/login");
		excludePaths.add("/web/product.html");
		excludePaths.add("/users/get_info");
		excludePaths.add("/users/log_out");
		excludePaths.add("/districts/");
		excludePaths.add("/goods/**");
		excludePaths.add("/web/type.html");
		excludePaths.add("/web/search.html");
		excludePaths.add("/web/question.html");
		excludePaths.add("/web/notice.html");
		excludePaths.add("/web/delivery.html");
		excludePaths.add("/web/return.html");
		//註冊攔截器,並設置黑白名單
		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(excludePaths);
	}
	
}
