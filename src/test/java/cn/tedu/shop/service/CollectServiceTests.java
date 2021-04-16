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
import cn.tedu.shop.entity.Collect;
import cn.tedu.shop.entity.District;
import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectServiceTests {
	
	@Autowired
	private ICollectService service;
	
	@Test
	public void addToCollect(){
		
		try {
			Collect collect = new Collect();
			collect.setGid(100L);
			Integer uid = 17;
			String username = "管理員";
			service.addToCollect(collect, uid, username);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	

	@Test
	public void delete(){
		try{
			Integer uid = 17;
			Integer coid = 1;
			service.delete(coid, uid);
			System.err.println("OK.");
		}catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	
}
