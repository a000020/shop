package cn.tedu.shop.service;

import java.util.List;

import cn.tedu.shop.entity.Address;
import cn.tedu.shop.service.ex.AccessDeniedException;
import cn.tedu.shop.service.ex.AddressCountLimitException;
import cn.tedu.shop.service.ex.AddressNotFoundException;
import cn.tedu.shop.service.ex.DeleteException;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.UpdateException;

/**
 * 收貨地址業務層接口
 * @author User
 *
 */
public interface IAddressService {
	
	public static final int ADDRESS_MAX_COUNT=20;
	
	/**
	 * 新增收貨地址
	 * @param address 收貨地址
	 * @param uid 用戶id
	 * @param username 用戶名
	 * @throws InsertException 新增資料異常
	 * @throws AddressCountLimitException 收貨地址上限異常
	 */
	void addnew(Address address,Integer uid,String username)throws InsertException,AddressCountLimitException;
	
	void delete(Integer aid,Integer uid,String username)throws DeleteException,AccessDeniedException,AddressNotFoundException,UpdateException;
	
	/**
	 * 設置指定收貨地址為默認
	 * @param aid
	 * @param uid
	 * @param username
	 * @throws AddressNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void setDefault(Integer aid,Integer uid,String username) throws AddressNotFoundException,AccessDeniedException,UpdateException;
	
	/**
	 * 用戶的收貨地址列表
	 * @param uid
	 * @return
	 */
	List<Address> getByUid(Integer uid);
	
	/**
	 * 根據收貨地址id獲得地址信息
	 * @param aid
	 * @return
	 */
	Address getByAid(Integer aid);
}
