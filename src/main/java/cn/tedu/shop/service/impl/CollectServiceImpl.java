package cn.tedu.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.shop.entity.Collect;
import cn.tedu.shop.mapper.CollectMapper;
import cn.tedu.shop.service.ICollectService;
import cn.tedu.shop.service.ex.AccessDeniedException;
import cn.tedu.shop.service.ex.CollectNotFoundException;
import cn.tedu.shop.service.ex.DeleteException;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.vo.CollectVO;

@Service
public class CollectServiceImpl implements ICollectService {

	@Autowired
	private CollectMapper collectMapper;
	
	
	@Override
	public void addToCollect(Collect collect, Integer uid, String username) throws InsertException {
		Collect result =findByUidAndGid(uid, collect.getGid());
		if(result!=null){
			return;
		}
		collect.setUid(uid);
		collect.setCreateUser(username);
		collect.setCreateTime(new Date());
		collect.setModifiedUser(username);
		collect.setModifiedTime(new Date());
		insert(collect);

	}
	
	

	@Override
	public void delete(Integer coid, Integer uid) {
		Collect result = findByCoid(coid);
		if(result==null){
			throw new CollectNotFoundException("收藏數據不存在");
		}
		if(result.getUid()!=uid){
			throw new AccessDeniedException("嘗試訪問他人數據!");
		}
		deleteByCoid(coid);
	}

	
	@Override
	public List<CollectVO> getByUid(Integer uid) {
		
		return findByUid(uid);
	}
	
	/**
	 * 新增收藏
	 * @param collect
	 * @return
	 */
	private void insert(Collect collect){
		Integer rows =collectMapper.insert(collect);
		if(rows!=1){
			throw new InsertException("加入收藏發生錯誤!");
		}
	}
	
	/**
	 * 刪除收藏資料
	 * @param coid
	 * @return
	 */
	private void deleteByCoid(Integer coid){
		Integer rows =collectMapper.deleteByCoid(coid);
		if(rows!=1){
			throw new DeleteException("刪除失敗");
		}
	}
	
	/**
	 * 根據用戶id及商品id查詢收藏數據
	 * @param uid
	 * @param gid
	 * @return
	 */
	private Collect findByUidAndGid(Integer uid, Long gid){
		return collectMapper.findByUidAndGid(uid, gid);
	}
	
	
	
	/**
	 * 查詢指定用戶的購物車訊息
	 * @param uid
	 * @return
	 */
	private List<CollectVO> findByUid(Integer uid){
		return collectMapper.findByUid(uid);
	}


	/**
	 * 根據收藏id查詢數據
	 * @param Coid
	 * @return
	 */
	private Collect findByCoid(Integer Coid){
		return collectMapper.findByCoid(Coid);
	}
	

}
