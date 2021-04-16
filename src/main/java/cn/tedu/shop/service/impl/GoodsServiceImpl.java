package cn.tedu.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.tedu.shop.entity.Goods;
import cn.tedu.shop.mapper.GoodsMapper;
import cn.tedu.shop.service.IGoodsService;

@Service
public class GoodsServiceImpl implements IGoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Goods> getHotList() {
		
		return findHotList();
	}
	
	@Override
	public List<Goods> getNewList() {
		
		return findNewList();
	}
	
	@Override
	public Goods getById(Long id) {
		return findById(id);
	}
	
	@Override
	public List<Goods> getByItemType(String itemType) {
		
		return findByItemType(itemType);
	}


	@Override
	public List<Goods> getByType(String itemType,Integer page) {
		
		List<Goods> data = findByType(itemType,page);
		if(data.size()==0){
			return null;
		}
		return findByType(itemType, page);
	}


	@Override
	public List<Goods> getByTitle(String title,Integer page) {
		List<Goods> data = findByTitle(title,page);
		if(data.size()==0){
			return null;
		}
		return findByTitle(title,page);
	}

	
	/**
	 * 獲取熱銷商品列表
	 * @return 
	 */
	private List<Goods> findHotList(){
		return goodsMapper.findHotList();
	}
	

	/**
	 * 獲取熱銷商品列表
	 * @return 
	 */
	private List<Goods> findNewList(){
		return goodsMapper.findNewList();
	}

	/**
	 * 根據商品id獲取商品信息
	 * @param id
	 * @return
	 */
	private Goods findById(Long id){
		return goodsMapper.findById(id);
	}
	

	/**
	 * 根據商品類別獲取商品種類
	 * @param itemType
	 * @return
	 */
	private List<Goods> findByItemType(String itemType){
		return goodsMapper.findByItemType(itemType);
	}

	/**
	 * 根據商品類別獲取商品種類
	 * @param itemType
	 * @return
	 */
	private List<Goods> findByType(String itemType,Integer page){
		
		
		return goodsMapper.findByType(itemType,page);
	}

	/**
	 * 搜尋功能
	 * @param itemType
	 * @return
	 */
	private List<Goods> findByTitle(String title,Integer page){
		return goodsMapper.findByTitle(title,page);
	}
	
	

}
