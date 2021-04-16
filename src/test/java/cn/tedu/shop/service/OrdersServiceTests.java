package cn.tedu.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Order;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrdersServiceTests {
	
	@Autowired
	private IOrderService service;
	
	@Test
	public void getOrders(){
		
		try {
			Integer uid = 17;
			List<Order> list = service.getOrders(uid);
			for (Order order : list) {
				System.err.println(order);
			}
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
	
}
