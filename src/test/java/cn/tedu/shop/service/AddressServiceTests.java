package cn.tedu.shop.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Address;
import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
	
	@Autowired
	private IAddressService service;
	
	@Test
	public void addnew(){
		
		try {
			String username ="超級管理員";
			Address address = new Address();
			Integer uid = 17;
			address.setUid(uid);
			address.setName("張三");
			address.setCityCode("800");
			address.setCityName("宜蘭縣");
			address.setAreaCode("801");
			address.setAreaName("宜蘭");
			address.setAddress("我家");
			address.setZip("260");
			address.setPhone("1234");
			service.addnew(address, uid, username);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void delete(){
		
		try {
			String username ="超級管理員";
			Integer uid = 17;
			Integer aid = 5;
			service.delete(aid, uid, username);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void setDefault(){
		
		try {
			String username ="超級管理員";
			Integer uid = 17;
			Integer aid =2;
			service.setDefault(aid, uid, username);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getByUid(){
		Integer uid =17;
		
		List<Address> list= service.getByUid(uid);
		for (Address address : list) {
			System.err.println(address);
		}
	}
	
	
	
}
