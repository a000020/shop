package cn.tedu.shop.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.shop.entity.User;

/**
 * 處理用戶資料的持久層
 * @author User
 *
 */

public interface UserMapper {
	
	/**
	 * 用戶註冊資料
	 * @param user
	 * @return 受影響行數
	 */
	Integer insert(User user);
	
	/**
	 * 更改密碼
	 * @param uid 用戶id
	 * @param password 密碼 
	 * @param modifiedUser 最後修改人
	 * @param modifiedTime 最後修改時間
	 * @return 返回受影響行數
	 */
	Integer updatePassword(
			@Param("uid")Integer uid,@Param("password")String password,
			@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 修改用戶資料
	 * @param user
	 * @return
	 */
	Integer updateInfo(User user);
	
	/**
	 * 修改頭像
	 * @param uid
	 * @param avatar
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateAvatar(@Param("uid")Integer uid,@Param("avatar")String avatar,
			@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	
	/**
	 * 根據用戶名查詢用戶資料
	 * @param username 用戶名
	 * @return 查詢匹配數據,若無返回null
	 */
	User findByUsername(String username);
	
	/**
	 * 根據uid查詢用戶訊息
	 * @param uid
	 * @return 查詢匹配數據,若無返回null
	 */
	User findByUid(Integer uid);
	
}
