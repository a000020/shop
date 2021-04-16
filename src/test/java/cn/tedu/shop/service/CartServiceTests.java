package cn.tedu.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Address;
import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.entity.District;
import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
	
	@Autowired
	private ICartService service;
	
	@Test
	public void addToCart(){
		
		try {
			Cart cart = new Cart();
			cart.setGid(100L);
			cart.setNum(20);
			Integer uid = 17;
			String username = "管理員";
			service.addToCart(cart, uid, username);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void less(){
		try{
			Integer cid = 5;
			Integer uid = 17;
			String username = "系統管理員";
			service.less(cid, uid, username);
			System.err.println("OK.");
		}catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	
	
}
