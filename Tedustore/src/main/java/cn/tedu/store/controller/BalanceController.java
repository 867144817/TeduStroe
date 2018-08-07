package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.put("list", list);
		return "orderConfirm";
	}
	@RequestMapping("/payment.do")
	public String payment(){
		return "payment";
	}
}
