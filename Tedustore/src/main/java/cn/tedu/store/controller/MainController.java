package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Resource
	private IGoodsCategoryService goodsCategoryService;
	@Resource
	private IGoodsService goodsService;
	@RequestMapping("/showIndex.do")
	public String showIndex(ModelMap model) {
		List<GoodsCategory> list = goodsCategoryService.selectCategoryByParentId(161, 0, 3);
		model.addAttribute("list2", list);
		List<List<GoodsCategory>> list3 = new ArrayList<List<GoodsCategory>>();
		for (GoodsCategory goodsCategory : list) {
			List<GoodsCategory> listx = goodsCategoryService.selectCategoryByParentId(goodsCategory.getId(), 0, 6);
			list3.add(listx);
		}
		model.addAttribute("list3", list3);
		List<Goods> listx = goodsService.selectGoodsByCategoryId(163, 0, 3);
		model.addAttribute("listx",listx);
		
		return "index";
	}
	
	

//	@RequestMapping("/")
	
}
