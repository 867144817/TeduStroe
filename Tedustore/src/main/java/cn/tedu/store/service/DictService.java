package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Area;
import cn.tedu.store.bean.City;
import cn.tedu.store.bean.Province;
import cn.tedu.store.mapper.DictMapper;

@Service
public class DictService implements IDictService{
	@Resource
	private DictMapper dictMapper;
	//获取所有省
	public List<Province> getProvince(){
		
		return dictMapper.selectProvince();
	}
	//获取该市所有地区
	public List<Area> getArea(String cityCode) {
		return dictMapper.selectArea(cityCode);
	}
	//获取该省所有城市
	public List<City> getCity(String provinceCode) {
		return dictMapper.selectCity(provinceCode);
	}
	public String selectProvinceNameByCode(String provinceCode) {
		return dictMapper.selectProvinceNameByCode(provinceCode);
	}
	public String selectCityNameByCode(String cityCode) {
		return dictMapper.selectCityNameByCode(cityCode);
	}
	public String selectAreaNameByCode(String areaCode) {
		return dictMapper.selectAreaNameByCode(areaCode);
	}
	
}
