package cn.tedu.store.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.tedu.store.service.StudentService;
//生成代理辅助类
@Component
public class StudentProxyHandler implements InvocationHandler{
	@Resource
	private StudentService studentService;
	@Resource
	private StudentAop studentAop;
	//获取动态代理的对象
	public Object getProxy() {
		//第一个参数表示目标类的类加载器对象
		//第二个参数表示目标类的接口对象
		//第三个接口实现了InvocationHandler接口的类的对象
		return Proxy.newProxyInstance(studentService.getClass().getClassLoader(), 
												studentService.getClass().getInterfaces(), this);
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//设置前置通知
		studentAop.log();
		//表示通过反射调用studentService的方法
		Object obj = method.invoke(studentService, args);
		
		return obj;
	}
	
}
