package cn.tedu.shop.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.vo.CartVO;

/**
 * 購物車持久層
 * @author User
 *
 */
public interface CartMapper {
	
	/**
	 * 新增購物車
	 * @param cart
	 * @return
	 */
	Integer insert(Cart cart);
	
	
	/**
	 * 刪除購物車數據
	 * @param cart
	 * @return
	 */
	Integer deleteByCid(Integer cid);
	
	/**
	 * 修改購物車數量
	 * @param cid
	 * @param num
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateNum(@Param("cid")Integer cid ,@Param("num")Integer num,
			@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 刪除指定用戶的所有購物車數據
	 * @param uid
	 * @return
	 */
	Integer clearByUid(Integer uid);
	
	/**
	 * 根據用戶id及商品id查詢購物車數據
	 * @param uid
	 * @param gid
	 * @return
	 */
	Cart findByUidAndGid(@Param("uid") Integer uid,@Param("gid") Long gid);
	
	/**
	 * 查詢指定用戶的購物車訊息
	 * @param uid
	 * @return
	 */
	List<CartVO> findByUid(Integer uid);
	
	/**
	 * 根據購物車id查詢數據
	 * @param cid
	 * @return
	 */
	Cart findByCid(Integer cid);
	
	/**
	 * 根據購物車id獲得購物車數據
	 * @param cids
	 * @return
	 */
	List<CartVO> findByCids(Integer[] cids);
	
	
}
