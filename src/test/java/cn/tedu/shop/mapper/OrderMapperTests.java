package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Order;
import cn.tedu.shop.entity.OrderItem;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	public void insertOrder(){
		Order order = new Order();
		order.setUid(17);
		order.setRecvAddress("宜蘭");
		order.setRecvName("帥哥");
		order.setRecvPhone("1234");
		order.setTotalPrice(100L);
		order.setState(0);
		Integer rows =orderMapper.insertOrder(order);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void insertOrderItem() {
		OrderItem orderItem = new OrderItem();
		orderItem.setOid(1);
		Integer rows = orderMapper.insertOrderItem(orderItem);
		System.err.println("rows=" + rows);
	}
		
	
	@Test
	public void findByUid(){
		Integer uid = 17;
		Order data = orderMapper.findByUid(uid);
		System.err.println(data);
	}
	
	@Test
	public void a(){
		Integer uid = 17;
		List<Order> data = orderMapper.findOrders(uid);
		System.err.println(data);
	}
	
	
	
	
}
