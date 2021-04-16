package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.entity.Collect;
import cn.tedu.shop.vo.CartVO;
import cn.tedu.shop.vo.CollectVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectMapperTests {
	
	@Autowired
	private CollectMapper collectMapper;
	
	@Test
	public void insert(){
		Collect collect =new Collect();
		collect.setGid(400L);
		collect.setUid(17);
		Integer rows =collectMapper.insert(collect);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void delete(){
		Integer coid = 3;
		Integer rows =collectMapper.deleteByCoid(coid);
		System.err.println("rows="+rows);
	}
	
	
	@Test
	public void findByUidAndGid(){
		Collect rows = collectMapper.findByUidAndGid(17, 400L);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void findByUid(){
		Integer uid = 17;
		List<CollectVO> list= collectMapper.findByUid(uid);
		for (CollectVO collectVO : list) {
				System.err.println(collectVO);
			
		}
	}
	
}
