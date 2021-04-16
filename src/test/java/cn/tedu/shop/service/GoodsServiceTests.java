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
import cn.tedu.shop.entity.District;
import cn.tedu.shop.entity.Goods;
import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTests {
	
	@Autowired
	private IGoodsService service;
	
	@Test
	public void getHotList(){
		
		try {
			
			List<Goods>  list=service.getHotList();
			for (Goods goods : list) {
				System.err.println(goods);
			}
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getNewList(){
		
		try {
			
			List<Goods>  list=service.getNewList();
			for (Goods goods : list) {
				System.err.println(goods);
			}
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void a(){
		String itemType = "watch";
		Integer page = 2;
		List<Goods> list =service.getByType(itemType, page);
		for (Goods goods : list) {
			System.err.println(goods);
		}
	}
	
}
