package cn.tedu.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.shop.entity.Address;
import cn.tedu.shop.service.IAddressService;
import cn.tedu.shop.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
	
	
	@Autowired
	private IAddressService service;
	
	@RequestMapping("addnew")
	public JsonResult<Void> addnew(Address address,HttpSession session){
		//從session獲取uid,username
		Integer uid =getUidFromSession(session);
		String username= getUsernameFromSession(session);
		service.addnew(address, uid, username);
		
		return new JsonResult<>(SUCCESS);
	}
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> delete(HttpSession session,@PathVariable("aid")Integer aid){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.delete(aid, uid, username);
		return new JsonResult<>(SUCCESS);
	}
	
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(HttpSession session,@PathVariable("aid")Integer aid){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.setDefault(aid, uid, username);
		return new JsonResult<>(SUCCESS);
	}
	
	@GetMapping("/")
	public JsonResult<List<Address>> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<Address> data = service.getByUid(uid);
		return new JsonResult<>(SUCCESS,data);
	}
	
	
}
