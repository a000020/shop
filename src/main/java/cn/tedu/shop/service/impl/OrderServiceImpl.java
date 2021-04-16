package cn.tedu.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.orders.ApplicationContext;
import com.paypal.orders.OrderRequest;

import cn.tedu.shop.entity.Address;
import cn.tedu.shop.entity.Order;
import cn.tedu.shop.entity.OrderItem;
import cn.tedu.shop.mapper.OrderMapper;
import cn.tedu.shop.service.IAddressService;
import cn.tedu.shop.service.ICartService;
import cn.tedu.shop.service.IOrderService;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.vo.CartVO;
@Service
public class OrderServiceImpl implements IOrderService {

	
	@Autowired
	private OrderMapper OrderMapper;
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private ICartService cartService;
	
	@Transactional
	private OrderRequest buildMinimumRequestBody(){
		ApplicationContext applicationContext = new ApplicationContext();
		return null;
	}
	
	@Override
	@Transactional
	public Order create(Integer aid, Integer uid, String username) throws InsertException {
		Date now = new Date();
		List<CartVO> carts = cartService.getByUid(uid);
		Long totalPrice = 0L;
		for (CartVO cartVO : carts) {
			totalPrice += cartVO.getPrice()*cartVO.getNum();
		}
		Address address = addressService.getByAid(aid);
		
		Order order = new Order();
		order.setUid(uid);
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setRecvAddress(address.getCityName()+address.getAreaName()+address.getAddress());
		order.setTotalPrice(totalPrice);
		order.setState(0);
		order.setOrderTime(now);
		order.setCreateTime(now);
		order.setModifiedTime(now);
		order.setCreateUser(username);
		order.setModifiedUser(username);
		insertOrder(order);
		
		for (CartVO cartVO : carts) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOid(order.getOid());
			orderItem.setGid(cartVO.getGid());
			orderItem.setPrice(cartVO.getPrice());
			orderItem.setTitle(cartVO.getTitle());
			orderItem.setImage(cartVO.getImage());
			orderItem.setNum(cartVO.getNum());
			orderItem.setCreateTime(now);
			orderItem.setModifiedTime(now);
			orderItem.setCreateUser(username);
			orderItem.setModifiedUser(username);
			insertOrderItem(orderItem);
		}
		
		return order;
	}
	
	@Override
	public Order getByUid(Integer uid) {
		
		return findByUid(uid);
	}
	@Override
	public List<Order> getOrders(Integer uid) {
		return findOrders(uid);
	}
	
	/**
	 * 插入訂單數據
	 * @param order
	 * @return
	 */
	private void insertOrder(Order order){
		Integer rows =OrderMapper.insertOrder(order);
		if(rows!=1){
			throw new InsertException("插入訂單數據錯誤");
		}
	}
	/**
	 * 插入訂單商品數據
	 * @param orderItem
	 * @return
	 */
	private void insertOrderItem(OrderItem orderItem){
		Integer rows =OrderMapper.insertOrderItem(orderItem);
		if(rows!=1){
			throw new InsertException("插入訂單商品數據錯誤");
		}
	}

	
	
	/**
	 * 根據用戶id查詢訂單
	 * @param uid
	 * @return
	 */
	private Order findByUid(Integer uid){
		return OrderMapper.findByUid(uid);
	}

	
	
	private List<Order> findOrders(Integer uid){
		return OrderMapper.findOrders(uid);
	}

}
