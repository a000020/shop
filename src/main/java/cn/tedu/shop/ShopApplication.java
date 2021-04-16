package cn.tedu.shop;


import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import com.paypal.orders.ApplicationContext;


@MapperScan("cn.tedu.shop.mapper")
@SpringBootApplication
@Configuration
public class ShopApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	
	@Bean
	public MultipartConfigElement getMultipartConfig(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		
		DataSize maxFileSize = DataSize.ofMegabytes(50);
		factory.setMaxFileSize(maxFileSize);
		
		DataSize maxRequestSize = DataSize.ofMegabytes(100);
		factory.setMaxRequestSize(maxRequestSize);
		
		return factory.createMultipartConfig();
	}
	
	
}
