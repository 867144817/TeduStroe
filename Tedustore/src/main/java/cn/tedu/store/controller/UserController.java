package cn.tedu.store.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private IUserService userService;
	//加密字符
	private String salt="会呼吸的江湖";
	//显示注册页
	@RequestMapping("/showRegister.do")
	public String showreg() {
		return "register";
	}
	//显示登陆页
	@RequestMapping("/showLogin.do")
	public String tologin() {
		return "login";
	}
	//显示主页
	@RequestMapping("/exit.do")
	public String exit(HttpSession session) {
		session.invalidate();
		return "redirect:/main/showIndex.do";
	}
	//显示修改密码页
	@RequestMapping("/showPassword.do")
	public String exit() {
		return "personal_password";
	}
	//显示修改个人信息页
	@RequestMapping("/showPerson.do")
	public String showPersonInfo() {
		return "personPage";
	}

	/**
	 * //注册业务
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/reg.do")
	@ResponseBody
	public ResponseResult<User> reg(User user,HttpSession session) {
	
		ResponseResult<User> rr = new ResponseResult<User>();
		String md5Pwd = user.getPassword()+salt;
		md5Pwd = DigestUtils.md5Hex(md5Pwd);
		//System.out.println(md5Pwd);
		user.setPassword(md5Pwd);
		try {
			userService.register(user);
			rr.setState(1);
			rr.setMessage("成功");
			session.setAttribute("username", user.getUsername());
			System.out.println(user);
			rr.setData(user);
		} catch (UsernameAlreadyExistException e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
			rr.setData(user);
//			e.printStackTrace();
			return rr;
		}
		
		return rr;
	}
	/**
	 * //邮箱是否重复
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkEmail.do")
	@ResponseBody
	public ResponseResult<Void> email(String email) {
//		System.out.println(email);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		if(userService.checkEmail(email)) {
			rr.setState(0);
			rr.setMessage("邮箱已存在");
		}else {
			rr.setState(1);
			rr.setMessage("邮箱可以使用");
		}
//		userService.checkEmail(email);
		return rr;
	}
	/**
	 * //电话是否重复
	 * @param phone
	 * @return
	 */
	@RequestMapping("/checkPhone.do")
	@ResponseBody
	public ResponseResult<Void> phone(String phone) {
//		System.out.println(phone);
		ResponseResult<Void> rr = new ResponseResult<Void>();
//		userService.checkEmail(email);
		if(userService.checkPhone(phone)) {
			rr.setState(0);
			rr.setMessage("电话已存在");
		}else {
			rr.setState(1);
			rr.setMessage("电话可用");
		}
		return rr;
	}
	/**
	 * //用户名是否重复
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkUsername.do")
	@ResponseBody
	public ResponseResult<Void> userName(String username) {
//		System.out.println(username);
		ResponseResult<Void> rr = new ResponseResult<Void>();
//		userService.checkEmail(email);
		if(userService.checkUsername(username)) {
			rr.setState(0);
			rr.setMessage("该用户名已被占用");
		}else {
			rr.setState(1);
			rr.setMessage("该用户名可以使用");
		}
		return rr;
	}
	/**
	 * 	//登陆业务
	 * @param username
	 * @param pwd
	 * @param session
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public ResponseResult<Void> login(String username,String pwd,HttpSession session) {
//		System.out.println(username);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		pwd = DigestUtils.md5Hex(pwd+salt);
		try {
			User user = userService.login(username, pwd);
			//System.out.println(user);
			session.setAttribute("user", user);
			rr.setState(1);
			rr.setMessage("成功");
		} catch (RuntimeException e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
			return rr;
		}
		return rr;
	}
	
	/**
	 * //修改密码业务
	 * @param user
	 * @param oldpwd
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public ResponseResult<Void> update(User user,String oldpwd,HttpSession session){
//		System.out.println(user);
		ResponseResult<Void> rr = new ResponseResult<Void>();
		String md5Pwd = user.getPassword()+salt;
		md5Pwd = DigestUtils.md5Hex(md5Pwd);
		user.setPassword(md5Pwd);
		oldpwd = DigestUtils.md5Hex(oldpwd+salt);
		System.out.println(oldpwd);
		try {
			rr.setState(1);
			rr.setMessage("修改成功");
			userService.changePassword(user,oldpwd,super.getId(session));
		} catch (RuntimeException e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}

	
	/**
	 * //修改用户信息
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateUser.do")
	@ResponseBody
	public ResponseResult<Void> updatePerson(User user,HttpSession session){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		System.out.println(user);
		
		try {
			System.out.println();
			userService.updateUser(user,getId(session));
			User u = userService.getUserById(getId(session));
			rr.setState(1);
			rr.setMessage("修改成功");
			session.setAttribute("user", u);
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
	
		return rr;
	}
	/**
	 *		 上传图片
	 * @param file2
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadImage.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> upload(@RequestParam(value="myfile" , required=false) MultipartFile file2,HttpSession session) 
			throws IOException {
		ResponseResult<Void> rr = new ResponseResult<Void>();
	try {
		String path = "/upload/files"+this.getId(session);
		path = session.getServletContext().getRealPath(path);
		userService.savePortrait(file2, path, this.getId(session));
		rr.setState(1);
		rr.setMessage("上传成功");
		session.setAttribute("user", userService.getUserById(this.getId(session)));
	} catch (Exception e) {
		rr.setState(0);
		rr.setMessage(e.getMessage());
	}
		
		return rr;
	}


}
