package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.shop.entity.Address;

/**
 * 收貨地址持久層
 * @author User
 *
 */
public interface AddressMapper {
	
	
	/**
	 * 新增收貨地址
	 * @param address
	 * @return
	 */
	Integer insert(Address address);
	
	/**
	 * 根據aid刪除指定收貨地址
	 * @param aid
	 * @return
	 */
	Integer deleteByAid(Integer aid);
	
	/**
	 * 將所有收貨地址設非默認
	 * @param aid
	 * @return
	 */
	Integer updateNonDefault(Integer uid);
	/**
	 * 將指定收貨地址設默認
	 * @param aid
	 * @return
	 */
	Integer updateDefault(@Param("aid")Integer aid,@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	
	
	/**
	 * 根據用戶id查看該用戶收貨地址列表
	 * @param uid
	 * @return 返回查詢到的數據
	 */
	List<Address> findByUid(Integer uid);
	
	
	/**
	 * 統計用戶收貨地址數量
	 * @param uid
	 * @return 返回用戶收貨地址數量
	 */
	Integer countByUid(Integer uid);
	
	/**
	 * 根據aid查詢收貨地址信息
	 * @param aid
	 * @return
	 */
	Address findByAid(Integer aid);

	/**
	 * 查詢最後修改收貨地址
	 * @param uid
	 * @return
	 */
	Address findLastModified(Integer uid);
	
}
