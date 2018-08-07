package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.net.httpserver.HttpContext;

import cn.tedu.store.service.IBalanceService;
import cn.tedu.store.vo.CartVo;

@Controller
@RequestMapping("/balance")
public class BalanceController {
	@Resource
	private IBalanceService balanceService;
	@RequestMapping("/showOrderConfirm.do")
	public String showOrderConfirm(Integer[] ids,ModelMap model){
		System.out.println(ids[0]);
		List<CartVo> list = balanceService.selectBalance(ids);
		System.out.println(list);
		model.put("list", list);
		return "orderConfirm";
	}
	@RequestMapping("/payment.do")
	public String payment(Integer[] ids,Integer addressId,HttpServlet servlet){
		
		return "payment";
	}
	
	
}
