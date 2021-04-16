package cn.tedu.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.service.ICartService;
import cn.tedu.shop.util.JsonResult;
import cn.tedu.shop.vo.CartVO;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
	
	
	@Autowired
	private ICartService service;
	
	@RequestMapping("add_to_cart")
	public JsonResult<Cart> addToCart(HttpSession session,Cart cart){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.addToCart(cart, uid, username);
		
		return new JsonResult<>(SUCCESS);
	}
	
	
	@GetMapping("/")
	public JsonResult<List<CartVO>> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<CartVO> data =service.getByUid(uid);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@RequestMapping("{cid}/add")
	public JsonResult<Integer> add(@PathVariable("cid")Integer cid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		Integer data =service.add(cid, uid, username);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@RequestMapping("{cid}/less")
	public JsonResult<Integer> less(@PathVariable("cid")Integer cid,HttpSession session){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		Integer data =service.less(cid, uid, username);
		return new JsonResult<>(SUCCESS,data);
	}

	@RequestMapping("{cid}/delete")
	public JsonResult<Void> delete(@PathVariable("cid")Integer cid,HttpSession session){
		Integer uid = getUidFromSession(session);
		service.delete(cid, uid);
		return new JsonResult<>(SUCCESS);
	}
	

	
}
