package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTests {
	
	@Autowired
	private AddressMapper AddressMapper;
	
	@Test
	public void insert(){
		Address address = new Address();
		address.setUid(17);
		address.setName("張三");
		address.setCityCode("800");
		address.setCityName("宜蘭縣");
		address.setAreaCode("801");
		address.setAreaName("宜蘭");
		address.setAddress("我家");
		address.setZip("260");
		address.setPhone("1234");
		Integer rows = AddressMapper.insert(address);
		
		System.err.println("rows="+rows);
	}
	@Test
	public void deleteByAid(){
		Integer aid = 4;
		Integer rows =AddressMapper.deleteByAid(aid);
		System.err.println("rows="+rows);
	}
	
	
	@Test
	public void updateDefault(){
		Integer aid = 1;
		String modifiedUser = "管理員";
		Date modifiedTime = new Date();
		Integer rows =AddressMapper.updateDefault(aid, modifiedUser, modifiedTime);
		System.err.println("rows="+rows);
	}
	@Test
	public void updateNonDefault(){
		Integer uid =17;
		Integer rows =AddressMapper.updateNonDefault(uid);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void countByUid(){
		Integer uid = 17;
		Integer rows= AddressMapper.countByUid(uid);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void findByUid(){
		Integer uid =17;
		
		List<Address> list= AddressMapper.findByUid(uid);
		for (Address address : list) {
			System.err.println(address);
		}
	}
	@Test
	public void findByAid(){
		Integer aid = 1;
		Address data =AddressMapper.findByAid(aid);
		System.err.println(data);
	}
	
	@Test
	public void findLastModified(){
		Integer uid = 17;
		Address data =AddressMapper.findLastModified(uid);
		System.err.println("data="+data);
	}
	
	
}
