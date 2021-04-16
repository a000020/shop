package cn.tedu.shop.service.impl;

import java.util.Date;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.shop.entity.User;
import cn.tedu.shop.mapper.UserMapper;
import cn.tedu.shop.service.IUserService;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.PasswordNotMatchException;
import cn.tedu.shop.service.ex.UpdateException;
import cn.tedu.shop.service.ex.UserNotFoundException;
import cn.tedu.shop.service.ex.UsernameDuplicateException;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		// 根据参数user对象中的username属性查询数据：userMapper.findByUsername()
		String username = user.getUsername();
		User result = userMapper.findByUsername(username);
		// 判断查询结果是否不为null（查询结果是存在的）
		if(result!=null){
			throw new UsernameDuplicateException("註冊失敗!嘗試註冊的用戶名("+username+")被占用");
		}
		System.err.println("reg()>password="+user.getPassword());
		//得到鹽值
		String salt = UUID.randomUUID().toString();
		//基於參數user對象中的password進行加密,得到加密後的密碼
		String md5Password = getMd5Password(user.getPassword(), salt);
		//將加密後的密碼和鹽值封裝到user中
		user.setSalt(salt);
		user.setPassword(md5Password);
		
		System.err.println("reg()>salt="+salt);
		System.err.println("reg()>md5Password="+md5Password);
		// 将user中的isDelete设置为0
		user.setIsDelete(0);
		// 封装user中的4个日志属性
		Date now = new Date();
		user.setCreateUser(username);
		user.setCreateTime(now);
		user.setModifiedUser(username);
		user.setModifiedTime(now);
		insert(user);
		
	}
	
	@Override
	public User login(String username, String password)
			throws UserNotFoundException, PasswordNotMatchException {
		//根據參數username查詢用戶數據
		User result = userMapper.findByUsername(username);
		//判斷查詢結果是否為null
		if(result==null){
			//拋出:UserNotFoundException
			throw new UserNotFoundException("登入失敗!帳號不存在!");
		}
		
		//判斷查詢結果的isDelete是否為1
		if(result.getIsDelete()==1){
			//拋出:UserNotFoundException
			throw new UserNotFoundException("登入失敗!帳號不存在!");
		}
		
		
		//從查詢結果中獲取鹽值
		String salt =result.getSalt();
		//根據參數password和鹽值一起進行加密,得到加密後的密碼
		String md5Password = getMd5Password(password, salt);
		
		
		//判斷查詢結果中的password和以上加密後的密碼是否不一致
		if(!result.getPassword().equals(md5Password)){
			//拋出:PasswordNotMatchException
			throw new PasswordNotMatchException("登入失敗!密碼錯誤!");
		}
		
		//將查詢結果中的password、salt、isDelete設置為null
		result.setPassword(null);
		result.setSalt(null);
		result.setIsDelete(null);
		//返回查詢結果
		return result;
	}
	
	
	
	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		// 根据参数uid查询用户数据
		User result = findByUid(uid);
		System.err.println("uid is:"+uid);
		System.err.println("result is:"+result);

		// 从查询结果中获取盐值
		String salt = result.getSalt();
		// 根据参数oldPassword和盐值一起进行加密，得到加密后的密码
		String oldMd5Password =getMd5Password(oldPassword, salt);
		// 判断查询结果中的password和以上加密后的密码是否不一致
		if(!result.getPassword().equals(oldMd5Password)){
			// 抛出：PasswordNotMatchException
			throw new PasswordNotMatchException("修改密碼失敗!密碼錯誤");
		}

		// 根据参数newPassword和盐值一起进行加密，得到加密后的密码
		String newMd5Password = getMd5Password(newPassword, salt);
		// 创建当前时间对象
		Date now = new Date();
		// 执行更新密码
		updatePassword(uid, newMd5Password, username, now);
		
	}
	
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		//根據參數uid查詢用戶資料
		findByUid(user.getUid());
		
		
		//創建當前時間對象
		Date now = new Date();
		//修改日誌
		user.setModifiedTime(now);
		
		//執行修改
		updateInfo(user);
		
	}

	@Override
	public void changeAvatar(Integer uid, String username, String avatar)
			throws UpdateException, UserNotFoundException {
		// 根据参数uid查询用户数据
		User result = findByUid(uid);
		System.err.println("uid is:"+uid);
		System.err.println("result is:"+result);
		
		Date now = new Date();
		updateAvatar(uid, avatar, username, now);
	}

	
	
	
	@Override
	public User getByUid(Integer uid) {
		User result = findByUid(uid);
		
		return result;
	}
	
	
	
	/**
	 * 用戶註冊資料
	 * @param user
	 */
	private void insert(User user){
		Integer rows =userMapper.insert(user);
		if(rows!=1){
			throw new InsertException("插入數據發生未知錯誤!");
		}
	}
	
	/**
	 * 更改密碼
	 * @param uid 用戶id
	 * @param password 密碼 
	 * @param modifiedUser 最後修改人
	 * @param modifiedTime 最後修改時間
	 * @return 返回受影響行數
	 */
	private void updatePassword(
			Integer uid,String password,String modifiedUser,Date modifiedTime){
		Integer rows =userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if(rows!=1){
			throw new UpdateException("修改密碼發生未知錯誤");
		}
	}
	
	/**
	 * 修改用戶資料
	 * @param user
	 * @return
	 */
	private void updateInfo(User user){
		Integer rows =userMapper.updateInfo(user);
		if(rows!=1){
			throw new UpdateException("修改資料發生未知錯誤");
		}
	}
	private void updateAvatar(Integer uid,String avatar,
		String modifiedUser,Date modifiedTime){
		Integer rows =userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		if(rows!=1){
			throw new UpdateException("修改資料發生未知錯誤");
		}
	}
	
	
	/**
	 * 根據uid查詢用戶訊息
	 * @param uid
	 * @return 查詢匹配數據,若無返回null
	 */
	private User findByUid(Integer uid){
		User user =userMapper.findByUid(uid);
		if(user==null){
			throw new UserNotFoundException("帳號不存在!");
		}else if(user.getIsDelete()==1){
			throw new UserNotFoundException("帳號不存在!");
		}
		else{
			user.setIsDelete(0);
		}
		return user;
	}
	
		
	private String getMd5Password(String password,String salt){
		//規則:對password+salt進行三重加密
		String str = password+salt;
		for(int i=0;i<3;i++){
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}

	
	
	
	
	

}
