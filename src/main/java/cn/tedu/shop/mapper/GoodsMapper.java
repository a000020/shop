package cn.tedu.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.shop.entity.Goods;

/**
 * 商品資料的持久層
 * @author User
 *
 */
public interface GoodsMapper {
	
	/**
	 * 獲取熱銷商品列表
	 * @return 
	 */
	List<Goods> findHotList();
	
	/**
	 * 獲取最新商品列表
	 * @return
	 */
	List<Goods> findNewList();
	
	/**
	 * 根據商品id獲取商品信息
	 * @param id
	 * @return
	 */
	Goods findById(Long id);
	
	/**
	 * 根據商品類別獲取商品種類 (首頁)
	 * @param itemType
	 * @return
	 */
	List<Goods> findByItemType(String itemType);
	
	/**
	 * 根據商品類別獲取商品種類
	 * @param itemType
	 * @return
	 */
	List<Goods> findByType(@Param("itemType")String itemType,@Param("page")Integer page);
	
	/**
	 * 搜尋功能
	 * @param itemType
	 * @return
	 */
	List<Goods> findByTitle(@Param("title")String title,@Param("page")Integer page);
	
	
	
	
}
