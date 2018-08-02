package cn.tedu.store.aop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.tedu.store.service.IStudentService;
@Component
public class StudentProxy implements IStudentService{
	//再代理类中即有目标类的对象
	//还要有切面类的对象
	@Resource
	private IStudentService studentService;
	@Resource
	private StudentAop studentAop;
	public void addStudent() {
		// TODO Auto-generated method stub
		studentAop.log();
		studentService.addStudent();
	}

	public void updateStudent() {
		// TODO Auto-generated method stub
		
	}

	public void getById() {
		// TODO Auto-generated method stub
		
	}

	public void getAll() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}
		
}
