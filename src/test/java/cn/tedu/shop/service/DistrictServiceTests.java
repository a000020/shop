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
import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTests {
	
	@Autowired
	private IDistrictService service;
	
	@Test
	public void getByParent(){
		
		try {
			String parent = "886";
			List<District> list = service.getByParent(parent);
			for (District district : list) {
				System.err.println(district);
			}
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
	
}
