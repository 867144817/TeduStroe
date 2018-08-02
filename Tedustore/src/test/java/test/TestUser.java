package test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.DictMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.UserService;

public class TestUser {
	ClassPathXmlApplicationContext ctx;
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("application-dao.xml","application-service.xml");
			
	}
	@Test
	public void insert() {
		UserMapper um = ctx.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("赵六");
		user.setPassword("123456");
		user.setPhone("110");
		user.setEmail("66@qq.com");
		user.setGender(1);
		user.setImage("");
		user.setCreatedTime(new Date());
		user.setCreatedUser("张三");
		user.setModifiedTime(new Date());
		user.setModifiedUser("李四");
		um.insertUser(user);
	}
	@Test
	public void select() {
		UserMapper um = ctx.getBean("userMapper",UserMapper.class);
		System.out.println(um.selectUserByUsername("斗罗大陆"));
	}
	@Test
	public void reg() {
		IUserService um = ctx.getBean("userService",UserService.class);
		User user = new User();
		user.setUsername("赵七");
		user.setPassword("123456");
		user.setPhone("110");
		user.setEmail("66@qq.com");
		user.setGender(1);
		user.setImage("");
		um.register(user);
//		System.out.println();
	}
	@Test
	public void login() {
		IUserService um = ctx.getBean("userService",UserService.class);
		try {
			User user = um.login("张三", "123456");
			System.out.println(user);
		} catch (RuntimeException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	@Test
	public void update() {
//		IUserService um = ctx.getBean("userService",UserService.class);
		UserMapper um = ctx.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("赵七1");
		user.setPassword("1234562");
//		user.setPhone("254023");
		user.setEmail("66857@qq.com");
//		user.setGender(2);
		user.setId(1);
		um.updateUser(user);
	}
	@Test
	public void ById() {
		UserMapper um = ctx.getBean("userMapper",UserMapper.class);
		User user = new User();
	}
	
	@Test
	public void selectProvince() {
		DictMapper dm = ctx.getBean("dictMapper",DictMapper.class);
		
		Province p = new Province();
		List<Province> pList = dm.selectProvince();
		for (Province province : pList) {
			System.out.println(province);
		}
	}
	@Resource
	private AddressMapper addressMapper;
	@Resource
	private IAddressService addressService;
	@Test
	public void sele() {
//		IAddressService dm = ctx.getBean("addressService",IAddressService.class);
//		System.out.println(dm.selectAddressById(7));
		AddressMapper dm = ctx.getBean("addressMapper",AddressMapper.class);
//		System.out.println(dm.selectAddressById(7));
	}

}
