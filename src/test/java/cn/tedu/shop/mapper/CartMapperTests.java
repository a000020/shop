package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.entity.District;
import cn.tedu.shop.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Test
	public void insert(){
		Cart cart =new Cart();
		cart.setGid(400L);
		cart.setUid(17);
		cart.setNum(20);
		Integer rows =cartMapper.insert(cart);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void delete(){
		Integer cid = 5;
		Integer rows =cartMapper.deleteByCid(cid);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void deleteByUid(){
		Integer uid = 17;
		Integer rows =cartMapper.clearByUid(uid);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void updateNum(){
		Integer rows =cartMapper.updateNum(1, 5,"管理員", new Date());
		System.err.println("rows="+rows);
	}
	

	@Test
	public void findByUidAndGid(){
		Cart rows =cartMapper.findByUidAndGid(17, 400L);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void findByUid(){
		Integer uid = 17;
		List<CartVO> list= cartMapper.findByUid(uid);
		for (CartVO cartVO : list) {
				System.err.println(cartVO);
			
		}
	}
	
	@Test
	public void findByCid(){
		Integer cid = 5;
		Cart data =cartMapper.findByCid(cid);
		System.err.println(data);
	}
	
	@Test
	public void findByCids(){
		Integer[] cids = {16};
		List<CartVO> list= cartMapper.findByCids(cids);
		for (CartVO cartVO : list) {
				System.err.println(cartVO);
			
		}
	}
}
