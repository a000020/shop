package cn.tedu.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.shop.entity.District;
import cn.tedu.shop.service.IDistrictService;
import cn.tedu.shop.util.JsonResult;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
	@Autowired
	private IDistrictService service;
	
	@GetMapping("/")
	public JsonResult<List<District>> getByparent(String parent){
		List<District> data = service.getByParent(parent);
		return new JsonResult<>(SUCCESS,data);
	}
}
