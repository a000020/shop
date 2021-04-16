package cn.tedu.shop.service;

import java.util.List;

import cn.tedu.shop.entity.Goods;

/**
 * 商品資料的業務層
 * @author User
 *
 */
public interface IGoodsService {
	
	/**
	 * 獲取熱銷商品列表
	 * @return
	 */
	List<Goods> getHotList();
	
	/**
	 * 獲取最新商品列表
	 * @return
	 */
	List<Goods> getNewList();
	
	/**
	 * 獲取商品信息
	 * @param id
	 * @return
	 */
	Goods getById(Long id);
	
	/**
	 * 根據商品類別獲取商品種類
	 * @param itemType
	 * @return
	 */
	List<Goods> getByItemType(String itemType);
	

	/**
	 * 根據商品類別獲取商品種類
	 * @param itemType
	 * @return
	 */
	List<Goods> getByType(String itemType,Integer page);
	
	/**
	 * 搜尋功能
	 * @param itemType
	 * @return
	 */
	List<Goods> getByTitle(String title,Integer page);
	
}
