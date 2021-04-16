package cn.tedu.shop.service;

import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.PasswordNotMatchException;
import cn.tedu.shop.service.ex.UpdateException;
import cn.tedu.shop.service.ex.UserNotFoundException;
import cn.tedu.shop.service.ex.UsernameDuplicateException;

/**
 * 處理用戶資料的業務層接口
 * @author User
 *
 */
public interface IUserService {

	/**
	 * 
	 * 用戶註冊
	 * @param user 用戶資料
	 * @throws UsernameDuplicateException 用戶名已存在
	 * @throws InsertException 插入資料錯誤
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	
	/**
	 * 用戶登入
	 * @param username 用戶名
	 * @param password 密碼
	 * @return 返回用戶訊息
	 * @throws UserNotFoundException 用戶不存在
	 * @throws PasswordNotMatchException 密碼不匹配
	 */
	User login(String username,String password)throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 修改密碼
	 * @param uid 用戶id
	 * @param username 用戶名
	 * @param oldPassword 舊密碼
	 * @param newPassword 新密碼
	 * @throws UserNotFoundException 帳號不存在異常
	 * @throws PasswordNotMatchException 密碼不匹配異常
	 * @throws UpdateException 修改異常
	 */
	void changePassword(Integer uid,String username,String oldPassword,String newPassword) throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 修改個人資料
	 * @param user 用戶資料
	 * @throws UserNotFoundException帳號不存在異常
	 * @throws UpdateException修改異常
	 */
	void changeInfo(User user) throws UserNotFoundException , UpdateException;
	
	/**
	 * 修改頭像
	 * @param uid
	 * @param username
	 * @param avatar頭像路徑
	 * @throws UpdateException
	 * @throws UserNotFoundException
	 */
	void changeAvatar(Integer uid,String username,String avatar) throws UpdateException,UserNotFoundException;
	
	/**
	 * 根據用戶ID查詢用戶數據
	 * @param uid
	 * @return
	 */
	User getByUid(Integer uid);
}
