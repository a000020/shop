package cn.tedu.shop.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private IUserService service;
	
	@Test
	public void reg(){
		
		try {
			User user = new User();
			user.setUsername("root");
			user.setPassword("1234");
			user.setAddress("宜蘭市延平路");
			user.setZip("260");
			user.setEmail("lmes_90106");
			service.reg(user);
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void login(){
		try {
			String username = "root";
			String password = "1234";
			User user = service.login(username, password);
			System.err.println(user);
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void changePassword(){
		try {
			Integer uid = 3;
			String username = "超級管理員";
			String oldPassword = "5678";
			String newPassword = "1234";
			service.changePassword(uid, username, oldPassword, newPassword);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void updateInfo(){
		try {
			User user = new User();
			Integer uid = 1;
			String email = "5678";
			String phone = "5678";
			String address = "5678";
			String zip = "5678";
			Integer gender = 2;
			String modifiedUser = "超級管理員";
			Date modifiedTime = new Date();
			
			user.setUid(uid);
			user.setEmail(email);
			user.setPhone(phone);
			user.setAddress(address);
			user.setZip(zip);
			user.setGender(gender);
			user.setModifiedUser(modifiedUser);
			user.setModifiedTime(modifiedTime);
			service.changeInfo(user);
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void changeAvatar(){
		try {
			Integer uid = 17;
			String username = "超級管理員";
			String avatar = "456";
			service.changeAvatar(uid, username, avatar );
			System.err.println("OK.");
		} catch(ServiceException e){
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
		
	}
	
}
