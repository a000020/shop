package cn.tedu.shop.service;

import java.util.List;

import cn.tedu.shop.entity.District;

/*
 * 縣/市資料的業務層接口
 */
public interface IDistrictService {
	
	/**
	 *  根據父級代號獲取全國某縣所有鄉鎮市
	 * @param parent 父級代號,如果嘗試獲取全國縣,使用"886"
	 * @return 全國某縣市/鄉鎮市的列表
	 */
	List<District> getByParent(String parent);
	
	/**
	 * 根據代號獲取縣市資料
	 * @param Code
	 * @return 
	 */
	District getByCode(String Code);
}
