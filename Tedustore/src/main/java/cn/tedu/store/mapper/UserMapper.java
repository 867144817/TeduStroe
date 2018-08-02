package cn.tedu.store.mapper;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.User;

public interface UserMapper {
	void insertUser(User user);
	User selectUserByUsername(String name);
	Integer selectByEmail(String user);
	Integer selectByPhone(String phone);
	void updateUser(User user);
	User selectUserById(Integer id);
	void updateImage(@Param("image") String image,@Param("id") Integer id);
}
