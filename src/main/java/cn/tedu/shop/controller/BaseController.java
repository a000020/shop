package cn.tedu.shop.controller;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.shop.controller.ex.FileEmptyException;
import cn.tedu.shop.controller.ex.FileSizeException;
import cn.tedu.shop.controller.ex.FileTypeException;
import cn.tedu.shop.controller.ex.FileUploadIOException;
import cn.tedu.shop.controller.ex.FileUploadStateException;
import cn.tedu.shop.service.ex.AccessDeniedException;
import cn.tedu.shop.service.ex.AddressCountLimitException;
import cn.tedu.shop.service.ex.AddressNotFoundException;
import cn.tedu.shop.service.ex.CartNotFoundException;
import cn.tedu.shop.service.ex.DeleteException;
import cn.tedu.shop.service.ex.InsertException;
import cn.tedu.shop.service.ex.PasswordNotMatchException;
import cn.tedu.shop.service.ex.UserNotFoundException;
import cn.tedu.shop.service.ex.UsernameDuplicateException;
import cn.tedu.shop.util.JsonResult;
import cn.tedu.shop.service.ex.ServiceException;
import cn.tedu.shop.service.ex.UpdateException;

/**
 * 控制器類的基類
 * @author User
 *
 */
public abstract class BaseController {
	
	/**
	 * 從session中獲取uid
	 * @param session
	 * @return 返回獲取到的uid
	 */
	protected final Integer getUidFromSession(HttpSession session){
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	/**
	 * 從session中獲取username
	 * @param session
	 * @return 返回獲取到的username
	 */
	protected final String getUsernameFromSession(HttpSession session){
		return String.valueOf(session.getAttribute("username").toString());
	}
	
	/**
	 * 操作結果的"成功"狀態
	 */
	public static final Integer SUCCESS = 2000;
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> handleException(Throwable e){
		JsonResult<Void> jr =  new JsonResult<>();
		jr.setMessage(e.getMessage());
		
		if(e instanceof UsernameDuplicateException){
			//4000---用戶名衝突
			jr.setState(4000);
		}else if(e instanceof UserNotFoundException){
			//4001---帳號不存在錯誤
			jr.setState(4001);
		}else if(e instanceof PasswordNotMatchException){
			//4002---密碼錯誤
			jr.setState(4002);
		}else if(e instanceof AddressCountLimitException){
			//4003---收貨地址上限
			jr.setState(4003);
		}else if(e instanceof AddressNotFoundException){
			//4004---收貨地址不存在
			jr.setState(4004);
		}else if(e instanceof AccessDeniedException){
			//4005---數據歸屬不正確
			jr.setState(4005);
		}else if(e instanceof InsertException){
			//5000---插入數據錯誤
			jr.setState(5000);
		}else if(e instanceof UpdateException){
			//5001---修改數據錯誤
			jr.setState(5001);
		}else if(e instanceof FileEmptyException){
			//5002---上傳空文件錯誤
			jr.setState(5002);
		}else if(e instanceof FileSizeException){
			//5003---上傳文件容量過大錯誤
			jr.setState(5003);
		}else if(e instanceof FileTypeException){
			//5004---上傳文件類型不正確
			jr.setState(5004);
		}else if(e instanceof FileUploadIOException){
			//5005---
			jr.setState(5005);
		}else if(e instanceof FileUploadStateException){
			//5006---
			jr.setState(5006);
		}else if(e instanceof DeleteException){
			//5007---刪除數據異常
			jr.setState(5007);
		}else if(e instanceof CartNotFoundException){
			//5008---購物車數據不存在異常
			jr.setState(5008);
		}
		
		return jr;
	}
}
