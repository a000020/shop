package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.tedu.shop.entity.Goods;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTests {
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Test
	public void findHotList(){
		
		List<Goods>  list=goodsMapper.findHotList();
		for (Goods goods : list) {
			System.err.println(goods);
		}
		System.err.println("OK.");
	}
	
	@Test
	public void findNewList(){
		
		List<Goods>  list=goodsMapper.findNewList();
		for (Goods goods : list) {
			System.err.println(goods);
		}
		System.err.println("OK.");
	}
	
	@Test
	public void findById(){
		Long id = 6L;
		Goods data =goodsMapper.findById(id);
		System.err.println(data);
	}
	
	@Test
	public void findByItemType(){
		String itemType = "watch";
		List<Goods>  list=goodsMapper.findByItemType(itemType);
		for (Goods goods : list) {
			System.err.println(goods);
		}
		System.err.println("OK.");
	}
	
	@Test
	public void findByType(){
		String itemType = "laptop";
		Integer page = 2;
		List<Goods> list=goodsMapper.findByType(itemType,page);
		for (Goods goods : list) {
			System.err.println(goods);
		}
		System.err.println("OK.");
	}
	
	@Test
	public void findByTitle(){
		String title = "%藍%";
		Integer page = 1;
		List<Goods> list=goodsMapper.findByTitle(title,page);
		for (Goods goods : list) {
			System.err.println(goods);
		}
		System.err.println("OK.");
	}
	
	
}
