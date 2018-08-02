package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.aop.StudentProxyHandler;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IStudentService;

public class TestAop {
	ApplicationContext ctx;
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("application-dao.xml","application-aop.xml","application-service.xml");
			
	}
	@Test
	public void test() {
		IStudentService stu = ctx.getBean("studentProxy",IStudentService.class);
		stu.addStudent();
		System.out.println("执行完成");
	}
	@Test
	public void test2() {
		//获取辅助类的对象
		StudentProxyHandler stu = ctx.getBean("studentProxyHandler",StudentProxyHandler.class);
		//获取代理类
		IStudentService ss = (IStudentService)stu.getProxy();
	}
	@Test
	public void test3() {
		IAddressService as = ctx.getBean("addressService",IAddressService.class);
		as.updateDefault(10, 7);
	}
	
}
