package cn.tedu.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balance")
public class BalanceController {
	@RequestMapping("/showOrderConfirm.do")
	public String showOrderConfirm(){
		return "orderConfirm";
	}
	@RequestMapping("/payment.do")
	public String payment(){
		return "payment";
	}
}
