package cn.tedu.shop.mapper;

import java.util.List;

import cn.tedu.shop.entity.District;

/*
 * 縣/市資料的持久層街口
 */
public interface DistrictMapper {
	
	/**
	 * 根據父級代號獲取全國某縣所有鄉鎮市
	 * @param parent
	 * @return
	 */
	List<District> findByParent(String parent);
	
	/**
	 * 根據代號獲取縣市名稱
	 * @param Code
	 * @return
	 */
	District findByCode(String Code);
}
