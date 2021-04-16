package cn.tedu.shop.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insert(){
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		user.setAddress("宜蘭市延平路");
		user.setZip("260");
		user.setEmail("lmes_90106");
		Integer rows = userMapper.insert(user);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void updatePassword(){
		Integer uid = 1;
		String password = "5678";
		String modifiedUser = "超級管理員";
		Date modifiedTime = new Date();
		Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void updateInfo(){
		User user = new User();
		Integer uid = 17;
		String email = "1234";
		String phone = "1234";
		String address = "1234";
		String zip = "1234";
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
		Integer rows = userMapper.updateInfo(user);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void updateAvatar(){
		Integer uid = 17;
		String modifiedUser = "超級管理員";
		Date modifiedTime = new Date();
		String avatar = "123";
		Integer rows = userMapper.updateAvatar(uid, avatar , modifiedUser, modifiedTime);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void findByUsername(){
		String username = "root";
		User rows = userMapper.findByUsername(username);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void findByUid(){
		Integer uid = 17;
		User user = userMapper.findByUid(uid);
		System.err.println("user="+user);
	}
	
}
