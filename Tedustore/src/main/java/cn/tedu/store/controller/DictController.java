package cn.tedu.store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IDictService;

@RequestMapping("/dict")
@Controller
public class DictController {
	@Resource
	private IDictService dictService;
	//获取省
	@RequestMapping("/province.do")
	@ResponseBody
	public ResponseResult<List<Province>> getProvince(){
		ResponseResult<List<Province>> rr = new ResponseResult<List<Province>>();
		rr.setState(1);
		rr.setMessage("Province");
		rr.setData(dictService.getProvince());
		return rr;
	}
	//获取城市
	@RequestMapping("/getCity.do")
	@ResponseBody
	public ResponseResult<List<City>> getCity(String provinceCode){
		ResponseResult<List<City>> rr = new ResponseResult<List<City>>();
		rr.setState(1);
		rr.setMessage("City");
//		System.out.println(provinceCode);
		rr.setData(dictService.getCity(provinceCode));
		return rr;
	}
	//获取地区
	@RequestMapping("/getArea.do")
	@ResponseBody
	public ResponseResult<List<Area>> getArea(String cityCode){
		ResponseResult<List<Area>> rr = new ResponseResult<List<Area>>();
		rr.setState(1);
		rr.setMessage("Area");
		rr.setData(dictService.getArea(cityCode));
		return rr;
	}
	
}
