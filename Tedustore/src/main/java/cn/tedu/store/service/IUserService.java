package cn.tedu.store.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.User;

public interface IUserService {
	void register(User user);
	boolean checkEmail(String email);
	boolean checkPhone(String phone);
	boolean checkUsername(String username);
	User login(String username,String password);
	void changePassword(User user,String pwd,Integer id);
	void updateUser(User user,Integer id);
	/**
	 * 通过id查找用户信息
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 	上传保存头像
	 * @param file2
	 * @param path
	 * @param id
	 * @throws IOException
	 */
	public void savePortrait(MultipartFile file2,String path,Integer id)throws IOException;
	
}
