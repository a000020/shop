package cn.tedu.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.shop.entity.Order;
import cn.tedu.shop.service.IOrderService;
import cn.tedu.shop.util.JsonResult;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("create")
	public JsonResult<Order> create(Integer aid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username =getUsernameFromSession(session);
		
		Order data = orderService.create(aid, uid, username);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@GetMapping("/")
	public JsonResult<Order> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		Order data =orderService.getByUid(uid);
		return  new JsonResult<>(SUCCESS,data);
	}
	

	@GetMapping("orders")
	public JsonResult<List<Order>> getOrders(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Order> data =orderService.getOrders(uid);
		return new JsonResult<>(SUCCESS,data);
	}
}
