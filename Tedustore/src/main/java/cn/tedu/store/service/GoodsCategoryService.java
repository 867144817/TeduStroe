package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;

@Service
public class GoodsCategoryService implements IGoodsCategoryService{

	@Resource
	private GoodsCategoryMapper goodsCategoryMapper;
	public List<GoodsCategory> selectCategoryByParentId(Integer parentId, Integer offset, Integer count) {
		List<GoodsCategory> list = goodsCategoryMapper.selectCategoryByParentId(parentId, offset, count);
		return list;
	}
	public GoodsCategory selectCategoryById(Integer id) {
		return goodsCategoryMapper.selectCategoryById(id);
	}


}
