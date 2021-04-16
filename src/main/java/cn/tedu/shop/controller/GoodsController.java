package cn.tedu.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.shop.entity.Goods;
import cn.tedu.shop.service.IGoodsService;
import cn.tedu.shop.util.JsonResult;

/**
 * 商品資料的控制器層
 * @author User
 *
 */

@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {
	
	@Autowired
	private IGoodsService service;
	
	
	@GetMapping("hot")
	public JsonResult<List<Goods>> getHotList(){
		List<Goods> data =service.getHotList();
		return new JsonResult<List<Goods>>(SUCCESS,data);
		
	}
	
	@GetMapping("new")
	public JsonResult<List<Goods>> getNewList(){
		List<Goods> data =service.getNewList();
		return new JsonResult<List<Goods>>(SUCCESS,data);
	}
	
	@GetMapping("{id}/details")
	public JsonResult<Goods> getById(@PathVariable("id")Long id){
		Goods data = service.getById(id);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@GetMapping("/")
	public JsonResult<List<Goods>> getByItemType(String itemType){
		List<Goods> data = service.getByItemType(itemType);
		return new JsonResult<>(SUCCESS,data);
	}
	
	@GetMapping("{itemType}/type")
	public JsonResult<List<Goods>> getByType(@PathVariable("itemType")String itemType,Integer page){
		List<Goods> data = service.getByType(itemType,page);
		
		return new JsonResult<>(SUCCESS,data);
	}
	
	@GetMapping("{title}/search")
	public JsonResult<List<Goods>> getByTitle(@PathVariable("title")String title,Integer page){
		List<Goods> data = service.getByTitle("%"+title+"%",page);
		return new JsonResult<>(SUCCESS,data);
	}
	
}
