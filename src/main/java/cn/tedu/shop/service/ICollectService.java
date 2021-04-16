package cn.tedu.shop.service;

import java.util.List;

import cn.tedu.shop.entity.Collect;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.vo.CollectVO;

/**
 * 收藏的持久層
 * @author User
 *
 */
public interface ICollectService {
	
	void addToCollect(Collect collect,Integer uid,String username)throws InsertException;
	
	void delete(Integer coid,Integer uid);
	
	List<CollectVO> getByUid(Integer uid);
}
