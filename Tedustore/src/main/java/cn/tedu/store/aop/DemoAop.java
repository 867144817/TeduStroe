package cn.tedu.store.aop;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect表示当前类是切面类
@Component
@Aspect
public class DemoAop {
	//@Before表示在业务方法之前执行
	//bean(userService)表示再userService业务层的方法之前执行
	@Before("bean(userService)")
	public void test1() {
		System.out.println("之前...");
//		System.out.println(new Date().getTime());
	}
	//@After最终通知，不管有没有异常都会执行的代码
	@After("bean(userService)")
	public void test2() {
		System.out.println("之后1...");
//		System.out.println(new Date().getTime());
	}
	//@AfterReturning再不发生异常的时候下执行
	@AfterReturning("bean(userService)")
	public void test3() {
		System.out.println("之后2...");
//		System.out.println(new Date().getTime());
	}
	
	//@AfterReturning再发生异常的时候下执行
		@AfterThrowing("bean(userService)")
		public void test4() {
			System.out.println("发生异常...");
//			System.out.println(new Date().getTime());
		}
		//环绕通知
		//必须要有返回值
		//必须有参数ProceedingJoinPoint jp
		//参数对象调用方法jp.proceed
//		@Around("bean(userService)")
//		public Object test5(ProceedingJoinPoint jp) throws Throwable {
//			System.out.println("环绕通知前...");
//			Object obj = jp.proceed();//表示调用业务方法
//			System.out.println("环绕通知后...");
//			return obj;//返回业务逻辑方法的返回值
//		}
		
		//测试业务层的性能
		@Around("bean(userService)")
		public Object test6(ProceedingJoinPoint jp) throws Throwable {
			System.out.println("环绕通知前...");
			Long t1 = System.currentTimeMillis();
			Object obj = jp.proceed();//表示调用业务方法
			Long t2 = System.currentTimeMillis();
			System.out.println(t2-t1);
			System.out.println("环绕通知后...");
			return obj;//返回业务逻辑方法的返回值
		}
}
