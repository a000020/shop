package cn.tedu.shop.service;

import java.util.List;

import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.service.ex.AccessDeniedException;
import cn.tedu.shop.service.ex.CartNotFoundException;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.UpdateException;
import cn.tedu.shop.vo.CartVO;

/**
 * 購物車業務層
 * @author User
 *
 */
public interface ICartService {
	
	
	void  addToCart(Cart cart,Integer uid ,String username) throws InsertException,UpdateException;
	
	void delete(Integer cid,Integer uid);
	
	void deleteByUid(Integer uid);
	
	List<CartVO> getByUid(Integer uid);
	
	Integer add(Integer cid,Integer uid ,String username)throws CartNotFoundException,AccessDeniedException,UpdateException;
	
	Integer less(Integer cid,Integer uid ,String username)throws CartNotFoundException,AccessDeniedException,UpdateException;
	
}
