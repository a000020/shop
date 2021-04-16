package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.District;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictMapperTests {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Test
	public void findByParent(){
		String parent = "886";
		List<District> list=  districtMapper.findByParent(parent);
		for (District district : list) {
			System.err.println(district);
		}
		System.err.println("END.");
	}
	
	
	@Test
	public void findByCode(){
		String Code = "260";
		District data=  districtMapper.findByCode(Code);
		System.err.println("data="+data);
		System.err.println("END.");
	}
	
	
	
}
