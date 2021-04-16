package cn.tedu.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.shop.entity.Collect;
import cn.tedu.shop.vo.CollectVO;

/**
 * 收藏商品持久層
 * @author User
 *
 */
public interface CollectMapper {

	/**
	 * 新增收藏
	 * @param collect
	 * @return
	 */
	Integer insert(Collect collect);
	
	/**
	 * 刪除收藏資料
	 * @param coid
	 * @return
	 */
	Integer deleteByCoid(Integer coid);
	
	/**
	 * 根據用戶id及商品id查詢收藏數據
	 * @param uid
	 * @param gid
	 * @return
	 */
	Collect findByUidAndGid(@Param("uid") Integer uid,@Param("gid") Long gid);
	
	/**
	 * 查詢指定用戶的購物車訊息
	 * @param uid
	 * @return
	 */
	List<CollectVO> findByUid(Integer uid);
	
	/**
	 * 根據收藏id查詢數據
	 * @param Coid
	 * @return
	 */
	Collect findByCoid(Integer Coid);
}
