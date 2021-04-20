package cn.tedu.shop.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.shop.entity.Address;
import cn.tedu.shop.entity.District;
import cn.tedu.shop.mapper.AddressMapper;

import cn.tedu.shop.service.IAddressService;
import cn.tedu.shop.service.ex.AccessDeniedException;
import cn.tedu.shop.service.ex.AddressCountLimitException;
import cn.tedu.shop.service.ex.AddressNotFoundException;
import cn.tedu.shop.service.ex.DeleteException;
import cn.tedu.shop.service.ex.EmptyException;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.UpdateException;


@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private DistrictServiceImpl districtService;
	
	@Override
	public void addnew(Address address, Integer uid, String username)
			throws InsertException, AddressCountLimitException {
		//判斷收貨地址數量
		Integer count = countByUid(uid);
		String name =address.getName();
		if(name.equals("")){
			throw new EmptyException("收貨人不能為空!");
		}
		String phone =address.getPhone();
		if(phone.equals("")){
			throw new EmptyException("電話不能為空!");
		}
		if(address.getAddress().equals("")){
			throw new EmptyException("地址欄不能為空!");
		}
		if(address.getZip().equals("")){
			throw new EmptyException("郵遞區號不能為空!");
		}
		if(address.getTag().equals("")){
			throw new EmptyException("地址類型不能為空!");
		}
		//補全uid
		address.setUid(uid);
		//補全數據:city_name,area_name
		District city = districtService.getByCode(address.getCityCode());
		District area = districtService.getByCode(address.getAreaCode());
		if(city==null){
			address.setCityName(null);
			throw new EmptyException("請選擇縣市名稱!");
		}else{
			address.setCityName(city.getName());
		}
		if(area==null){
			address.setAreaName(null);
			throw new EmptyException("請選擇地區名稱!");
		}else{
			address.setAreaName(area.getName());
		}
		
		//判斷該用戶收貨地址數量是否為0,若為0設為默認 isDefault
		Integer isDefault = count == 0 ? 1:0;
		
		address.setIsDefault(isDefault);
		
		//創建當前時間對象
		Date now = new Date();
		//補全4個日誌
	 	address.setCreateUser(username);
		address.setCreateTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		//執行插入
		insert(address);
	}
	@Transactional
	@Override
	public void delete(Integer aid, Integer uid, String username)
			throws DeleteException, AccessDeniedException, AddressNotFoundException, UpdateException {
		Address result =findByAid(aid);
		if(result==null){
			throw new AddressNotFoundException("該收貨地址不存在");
		}
		if(result.getUid()!=uid){
			throw new AccessDeniedException("刪除失敗!嘗試訪問他人數據");
		}
		
		deleteByAid(aid);
		if(result.getIsDefault()==0){
			return;
		}
		
		Integer count =countByUid(uid);
		if(count ==0){
			return;
		}
		
		Address data = findLastModified(uid);
		updateDefault(data.getAid(), username, new Date());
		
	}
	
	
	@Override
	public List<Address> getByUid(Integer uid) {
		return findByUid(uid);
	}
	@Transactional
	@Override
	public void setDefault(Integer aid, Integer uid, String username)
			throws AddressNotFoundException, AccessDeniedException, UpdateException {
		Address result = findByAid(aid);
		if(result==null){
			throw new AddressNotFoundException("收貨地址不存在");
		}
		
		if(result.getUid()!=uid){
			throw new AccessDeniedException("設置默認錯誤!嘗試訪問他人數據");
		}
		
		updateNonDefault(uid);
		
		Date now = new Date();
		updateDefault(aid,username,now);
		
	}
	
	@Override
	public Address getByAid(Integer aid) {
		
		return findByAid(aid);
	}

	
	
	/**
	 * 新增收貨地址
	 * @param address
	 * @return
	 */
	private void  insert(Address address){
		Integer  rows = addressMapper.insert(address);
		if(rows!=1){
			throw new InsertException("新增數據失敗!發生未知錯誤!");
		}
	}
	
	/**
	 * 根據aid刪除指定收貨地址
	 * @param aid
	 * @return
	 */
	private void deleteByAid(Integer aid){
		Integer rows = addressMapper.deleteByAid(aid);
		if(rows!=1){
			throw new InsertException("刪除數據失敗!發生未知錯誤!");
		}
	}
	
	/**
	 * 將指定收貨地址設非默認
	 * @param aid
	 * @return
	 */
	private void updateNonDefault(Integer uid){
		Integer rows =addressMapper.updateNonDefault(uid);
		if(rows==0){
			throw new UpdateException("修改數據失敗!發生未知錯誤!");
		}
	}
	/**
	 * 將指定收貨地址設默認
	 * @param aid
	 * @return
	 */
	private void updateDefault(Integer aid,String modifiedUser,Date modifiedTime){
		Integer rows =addressMapper.updateDefault(aid, modifiedUser, modifiedTime);
		if(rows!=1){
			throw new UpdateException("修改數據失敗!發生未知錯誤!");
		}
	}
	

	/**
	 * 根據用戶id查看該用戶收貨地址列表
	 * @param uid
	 * @return 返回查詢到的數據
	 */
	private List<Address> findByUid(Integer uid){
		return addressMapper.findByUid(uid);
	}
	
	
	/**
	 * 統計用戶收貨地址數量
	 * @param uid
	 * @return 返回用戶收貨地址數量
	 */
	private Integer countByUid(Integer uid){
		Integer  count = addressMapper.countByUid(uid);
		if(count>ADDRESS_MAX_COUNT){
			throw new AddressCountLimitException("收貨地址數量已達上限");
		}
		return count;
	}

	/**
	 * 根據aid查詢收貨地址信息
	 * @param aid
	 * @return
	 */
	private Address findByAid(Integer aid){
		return addressMapper.findByAid(aid);
	}

	/**
	 * 查詢最後修改收貨地址
	 * @param uid
	 * @return
	 */
	private Address findLastModified(Integer uid){
		return addressMapper.findLastModified(uid);
	}
	

}
