package cn.tedu.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.shop.controller.ex.FileEmptyException;
import cn.tedu.shop.controller.ex.FileSizeException;
import cn.tedu.shop.controller.ex.FileTypeException;
import cn.tedu.shop.entity.User;
import cn.tedu.shop.service.IUserService;
import cn.tedu.shop.util.JsonResult;
import cn.tedu.shop.controller.ex.FileUploadIOException;
import cn.tedu.shop.controller.ex.FileUploadStateException;

/**
 * 處理用戶數據相關請求的控制器類
 * @author User
 *
 */

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	
	/**
	 * 文件允許的容量
	 */
	public static final long AVATAR_MAX_SIZE = 2*1024*1024;
	/**
	 * 上傳時允許的頭像文件類型
	 */
	public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<String>();
	
	public static final String AVATAR_DIR = "upload";
	/**
	 * 初始化上傳時允許的頭像文件類型
	 */
	static{
		AVATAR_CONTENT_TYPES.add("image/jpeg");
		AVATAR_CONTENT_TYPES.add("image/png");
		AVATAR_CONTENT_TYPES.add("image/gif");
	}
	
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		userService.reg(user);
		
		
		return new JsonResult<>(2000);
	}
	
	@RequestMapping("login")
	public JsonResult<User> login(String username,String password,HttpSession session){
		User data = userService.login(username, password);
		session.setAttribute("uid", data.getUid());
		session.setAttribute("username", data.getUsername());
		
		return new JsonResult<>(SUCCESS,data);
	}
	
	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(@RequestParam("old_password")String oldPassword,
			@RequestParam("new_password")String newPassword,HttpSession session){
		//從session獲取UID及username
		Integer uid =getUidFromSession(session);
		String username = getUsernameFromSession(session);
		userService.changePassword(uid, username, oldPassword, newPassword);
		
		return new JsonResult<>(2000);
	}
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(HttpSession session,User user){
		//從session獲取UID及username
		Integer uid =getUidFromSession(session);
		String username = getUsernameFromSession(session);
		user.setUid(uid);
		user.setUsername(username);
		userService.changeInfo(user);
		return new JsonResult<>(SUCCESS);
	}
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(HttpServletRequest request,@RequestParam("file") MultipartFile file){
		//檢查文件是否為空
		if(file.isEmpty()){
			throw new FileEmptyException("文件不能為空");
		}
		//檢查文件大小
		if(file.getSize()>AVATAR_MAX_SIZE){
			throw new FileSizeException("上傳文件容量過大");
		}
		//檢查文件類型
		if(!AVATAR_CONTENT_TYPES.contains(file.getContentType())){
			throw new FileTypeException("上傳文件類型不正確");
		}
		//確定文件夾:File dir
		String dirPath = request.getServletContext().getRealPath(AVATAR_DIR);
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		//確定文件名
		String originalFilename = file.getOriginalFilename();
		String suffix = "";
		int beginIndex = originalFilename.lastIndexOf(".");
		if(beginIndex != -1){
			suffix=originalFilename.substring(beginIndex);
		}
		String filename = UUID.randomUUID().toString() + suffix;
		
		//執行保存
		File dest = new File(dir,filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileUploadStateException("上傳文件發生錯誤!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileUploadIOException("讀取文件發生錯誤!");
		}
		
		//更新數據表:avatar="/upload/"+filename
		String avatar = "/"+AVATAR_DIR+"/" + filename;
		HttpSession session = request.getSession();
		//從session中獲取uid和username
		Integer uid = getUidFromSession(session);
		String username = getUsernameFromSession(session);
		userService.changeAvatar(uid, username, avatar);
		
		//返回
		JsonResult<String> jr = new JsonResult<>();
		jr.setState(SUCCESS);
		jr.setData(avatar);
		return jr;
	}
	
	@GetMapping("get_info")
	public JsonResult<User> getInfo(HttpSession session){
		//根據session獲取uid
		Integer uid = (Integer) session.getAttribute("uid");
		if(uid==null){
			return new JsonResult<>(null);
		}else{
			User user =userService.getByUid(uid);
			return new JsonResult<>(SUCCESS,user);
		}
		
	}
	
	/**
	 * 會員登出
	 * @param session
	 * @return
	 */
	@RequestMapping("log_out")
	public JsonResult<Void> log_out(HttpSession session){
		session.invalidate();
		
		return new JsonResult<>(SUCCESS);
	}

}
