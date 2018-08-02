package cn.tedu.store.controller;

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
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IGoodsCategoryService goodsCategoryService;

	@RequestMapping("/showSearch.do")
	public String showSearch(Integer categoryId,ModelMap model) {
		Integer len = goodsService.selectCount(categoryId);
		if(len%12==0) {
			len=(int)Math.ceil(len/12.0);
		}else {
			len=len/12+1;
		}
		GoodsCategory gc = goodsCategoryService.selectCategoryById(categoryId);
		model.addAttribute("name", gc);
		model.addAttribute("len", len);
		return "search";
	}
	@RequestMapping("/limit.do")
	@ResponseBody
	public ResponseResult<List<Goods>> limit(Integer page,Integer categoryId){
		ResponseResult<List<Goods>> rr = new ResponseResult<List<Goods>>();
		System.out.println(categoryId);
		List<Goods> listx = goodsService.selectGoodsByCategoryId(categoryId, (page-1)*12, 12);
//		model.addAttribute("listx",listx);
		Integer len = goodsService.selectCount(categoryId);
		if(len%12==0) {
			len=(int)Math.ceil(len/12.0);
		}else {
			len=len/12+1;
		}
		System.out.println(len);
		rr.setState(1);
		rr.setMessage(""+len);
		rr.setData(listx);
		return rr;
	}
	
	@RequestMapping("/productDetails.do")
	public String productDetails(String id,ModelMap model) {
		Goods g = goodsService.selectGoodsById(id);
		List<Goods> goodList = goodsService.selectGoodsByCategoryId(g.getCategoryId(), null, 0);
		model.addAttribute("goodList", goodList);
		model.addAttribute("goods", g);
		return "product_details";
	}
}
