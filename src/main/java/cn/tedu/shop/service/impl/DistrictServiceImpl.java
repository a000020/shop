package cn.tedu.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.shop.entity.District;
import cn.tedu.shop.mapper.DistrictMapper;
import cn.tedu.shop.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {

	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public List<District> getByParent(String parent) {
		
		return findByParent(parent);
	}

	@Override
	public District getByCode(String Code) {
		
		return findByCode(Code);
	}
	
	/**
	 * 根據父級代號獲取全國某縣所有鄉鎮市
	 * @param parent
	 * @return
	 */
	private List<District> findByParent(String parent){
		return districtMapper.findByParent(parent);
	}

	
	/**
	 * 根據代號獲取縣市名稱
	 * @param Code
	 * @return
	 */
	private District findByCode(String Code){
		return districtMapper.findByCode(Code);
	}

}
