package cn.tedu.shop.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.shop.entity.Cart;
import cn.tedu.shop.mapper.CartMapper;
import cn.tedu.shop.service.ICartService;
import cn.tedu.shop.service.ex.AccessDeniedException;
import cn.tedu.shop.service.ex.CartNotFoundException;
import cn.tedu.shop.service.ex.DeleteException;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.UpdateException;
import cn.tedu.shop.vo.CartVO;
@Service
public class CartServiceImpl implements ICartService {

	
	@Autowired
	private CartMapper cartMapper;
	
	
	@Override
	public void addToCart(Cart cart, Integer uid, String username) throws InsertException, UpdateException {
		Cart result = findByUidAndGid(uid, cart.getGid());
		if(result==null){
			cart.setUid(uid);
			cart.setCreateUser(username);
			cart.setModifiedUser(username);
			cart.setCreateTime(new Date());
			cart.setModifiedTime(new Date());
			insert(cart);
		}else{
			Integer cid = result.getCid();
			Integer oldNum = result.getNum();
			Integer newNum = oldNum+cart.getNum();
			updateNum(cid, newNum, username, new Date());
		}

	}
	@Override
	public void delete(Integer cid, Integer uid) {
		Cart result = findByCid(cid);
		if(result==null){
			throw new CartNotFoundException("購物車數據不存在");
		}
		if(result.getUid()!=uid){
			throw new AccessDeniedException("嘗試訪問他人數據!");
		}
		deleteByCid(cid);
	}
	@Override
	public void deleteByUid(Integer uid) {
		clearByUid(uid);
		
	}

	
	@Override
	public Integer add(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		Cart result =findByCid(cid);
		if(result==null){
			throw new CartNotFoundException("購物車數據不存在");
		}
		if(result.getUid()!=uid){
			throw new AccessDeniedException("嘗試訪問他人數據!");
		}
		Integer newNum =result.getNum()+1;
		updateNum(cid, newNum, username, new Date());
		return newNum;
	}

	@Override
	public Integer less(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		Cart result =findByCid(cid);
		if(result==null){
			throw new CartNotFoundException("購物車數據不存在");
		}
		if(result.getUid()!=uid){
			throw new AccessDeniedException("嘗試訪問他人數據!");
		}
		Integer newNum =result.getNum()-1;
		if(newNum<1){
			return result.getNum();
		}
		updateNum(cid, newNum, username, new Date());
		return newNum;
	}
	
	
	
	@Override
	public List<CartVO> getByUid(Integer uid) {
		List<CartVO> results = findByUid(uid);
		Iterator<CartVO> it = results.iterator();
		while (it.hasNext()) {
			if(uid != it.next().getUid()){
				it.remove();
			}
			
		}
		return results;
		
		
	}
	
	
	
	
	/**
	 * 新增購物車
	 * @param cart
	 * @return
	 */
	private void insert(Cart cart){
		Integer rows =cartMapper.insert(cart);
		if(rows!=1){
			throw new InsertException("新增數據失敗!發生未知原因");
		}
	}
	
	/**
	 * 修改購物車數量
	 * @param cid
	 * @param num
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	private void updateNum(Integer cid ,Integer num,String modifiedUser,Date modifiedTime){
		Integer rows =cartMapper.updateNum(cid, num, modifiedUser, modifiedTime);
		if(rows!=1){
			throw new UpdateException("修改數據失敗!發生未知原因");
		}
	}
	
	/**
	 * 根據用戶id及商品id查詢購物車數據
	 * @param uid
	 * @param gid
	 * @return
	 */
	private Cart findByUidAndGid(Integer uid,Long gid){
		return cartMapper.findByUidAndGid(uid, gid);
	}
	
	/**
	 * 查詢指定用戶的購物車訊息
	 * @param uid
	 * @return
	 */
	private List<CartVO> findByUid(Integer uid){
		return cartMapper.findByUid(uid);
	}

	/**
	 * 根據購物車id查詢數據
	 * @param cid
	 * @return
	 */
	private Cart findByCid(Integer cid){
		return cartMapper.findByCid(cid);
	}
	
	
	
	
	/**
	 * 刪除購物車數據
	 * @param cart
	 * @return
	 */
	
	private void deleteByCid(Integer cid){
		Integer rows =cartMapper.deleteByCid(cid);
		if (rows!=1){
			throw new DeleteException("刪除數據失敗!發生未知錯誤");
		}
	}
	
	
	/**
	 * 刪除指定用戶的所有購物車數據
	 * @param uid
	 * @return
	 */
	private void clearByUid(Integer uid){
		Integer rows =cartMapper.clearByUid(uid);
		if(rows ==0){
			throw new DeleteException("刪除數據失敗!");
		}
	}
	




	

}
