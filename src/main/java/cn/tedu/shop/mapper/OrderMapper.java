package cn.tedu.shop.mapper;

import java.util.List;

import cn.tedu.shop.entity.Order;
import cn.tedu.shop.entity.OrderItem;
/**
 * 處理訂單持久層
 * @author User
 *
 */
public interface OrderMapper {
	
	/**
	 * 插入訂單數據
	 * @param order
	 * @return
	 */
	Integer insertOrder(Order order);
	/**
	 * 插入訂單商品數據
	 * @param orderItem
	 * @return
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	Order findByUid(Integer uid);
	
	List<Order> findOrders(Integer uid);
	
	
}
