package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.GoodsCategory;

public interface IGoodsCategoryService {
	List<GoodsCategory> selectCategoryByParentId( Integer parentId,
			 Integer offset, Integer count);
	GoodsCategory selectCategoryById(Integer id);
}
