package cn.tedu.shop.service;

import java.util.List;

import cn.tedu.shop.entity.Order;
import cn.tedu.shop.service.ex.InsertException;

/**
 * 處理訂單業務層
 * @author User
 *
 */
public interface IOrderService {
	
	Order create(Integer aid,Integer uid,String username)throws InsertException;
	
	Order getByUid(Integer uid);
	
	List<Order> getOrders(Integer uid);
}
