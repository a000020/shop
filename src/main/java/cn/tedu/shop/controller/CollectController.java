package cn.tedu.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.shop.entity.Collect;
import cn.tedu.shop.service.ICollectService;
import cn.tedu.shop.util.JsonResult;
import cn.tedu.shop.vo.CollectVO;

@RestController
@RequestMapping("collects")
public class CollectController extends BaseController {
	
	@Autowired
	private ICollectService service;
	
	
	@RequestMapping("add_to_collect")
	public JsonResult<Collect> addToCollect(HttpSession session,Collect collect){
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		service.addToCollect(collect, uid, username);
		return new JsonResult<>(SUCCESS);
	}
	
	@GetMapping("/")
	public JsonResult<List<CollectVO>> getByUid(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<CollectVO> data =service.getByUid(uid);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@RequestMapping("{coid}/delete")
	public JsonResult<Integer> delete(@PathVariable("coid")Integer coid,HttpSession session){
		Integer uid = getUidFromSession(session);
		service.delete(coid, uid);
		return new JsonResult<>(SUCCESS);
	}
	
}
