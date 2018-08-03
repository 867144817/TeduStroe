package cn.tedu.store.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;

@Service
public class UserService implements IUserService {
	@Resource
	private UserMapper userMapper;

	// 注册业务
	public void register(User user) {
		boolean userFalg = checkUsername(user.getUsername());
		boolean emailFalg = checkEmail(user.getEmail());
		boolean phoneFalg = checkPhone(user.getPhone());
		if (!userFalg && !emailFalg && !phoneFalg) {
			userMapper.insertUser(user);
		}
		if (userFalg) {
			throw new UsernameAlreadyExistException("该用户名已被占用");
		}
		if (emailFalg) {
			throw new UsernameAlreadyExistException("邮箱已存在");
		}
		if (phoneFalg) {
			throw new UsernameAlreadyExistException("电话已存在");
		}
	}

	// 邮箱是否重复业务
	public boolean checkEmail(String email) {
		Integer email1 = userMapper.selectByEmail(email);
		return email1 > 0;
	}

	// 电话是否重复业务
	public boolean checkPhone(String phone) {
		Integer phone1 = userMapper.selectByPhone(phone);
		System.out.println(phone1);
		return phone1 > 0;
	}

	// 用户名是否重复业务
	public boolean checkUsername(String username) {
		System.out.println(username);
		return userMapper.selectUserByUsername(username) != null;
	}

	// 登陆业务
	public User login(String username, String password) {
		User user = userMapper.selectUserByUsername(username);
		
		System.out.println("user:"+user);
		if (user == null) {
			throw new UserNotFoundException("用户名没找到");
		}
		System.out.println(password);
		if (user.getPassword().equals(password)) {
			System.out.println(user);
			return user;
		} else {
			throw new PasswordNotMatchException("密码错误");
		}
		// return user;
	}

	// 修改密码业务
	public void changePassword(User user, String pwd, Integer id) {
		User user1 = (User) userMapper.selectUserById(id);

		if (user1 == null) {
			throw new UserNotFoundException("用户不存在");
		} else {
			if (user1.getPassword().equals(pwd)) {
				user.setId(id);
				userMapper.updateUser(user);
			} else {
				throw new PasswordNotMatchException("密码错误");
			}
		}
	}

	// 修改个人信息（用户名，邮箱，电话，）
	public void updateUser(User user, Integer id) {
		User user1 = userMapper.selectUserById(id);

		if (user1 == null) {
			throw new UserNotFoundException("用户不存在");
		} else {
			if(user1.getUsername().equals(user.getUsername())||!checkUsername(user.getUsername())) {
				if(user1.getEmail().equals(user.getEmail())||!checkEmail(user.getEmail())) {
					user.setId(id);
					userMapper.updateUser(user);
//					return userMapper.selectUserById(id);
				}else {
					throw new UsernameAlreadyExistException("邮箱已存在");
				}
			}else{
				throw new UsernameAlreadyExistException("用户已存在");
			}
			
		}
	}

//	根据id获取用户信息
	public User getUserById(Integer id) {
		return userMapper.selectUserById(id);
	}
	
	//上传保存头像
	public void savePortrait(MultipartFile file2, String path,Integer id) throws IOException {
		User user1 = userMapper.selectUserById(id);

		if (user1 == null) {
			throw new UserNotFoundException("用户不存在");
		} else {
			
			File dir = new File(path);
			if (!dir.exists()) 
				dir.mkdir();
			String suffix = file2.getOriginalFilename().substring(file2.getOriginalFilename().lastIndexOf("."));
//			String name = new Date().getTime()+suffix;
			System.out.println(file2.getOriginalFilename());
			String name = "portrait"+suffix;
			File file = new File(dir, name);
			System.out.println(path+"/"+name);
//			// 保存到文件
			file2.transferTo(file);
			
			String url = "../upload/files"+id+"/"+name;
			System.out.println(url);
			userMapper.updateImage(url, id);
			
		}
		
	}

}
