package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.bean.GoodsCategory;

public interface IGoodsService {
	/**
	 * 根据商品类型查询商品表信息 并分页
	 * @param categoryId
	 * @param offset
	 * @param count
	 * @return
	 */
	List<Goods> selectGoodsByCategoryId(Integer categoryId,
			 Integer offset, Integer count);
	/**
	 * 查询该类商品有多少条记录
	 * @param categoryId
	 * @return
	 */
	Integer selectCount(Integer categoryId);
	/**
	 * 通过id来查询商品
	 * @param id
	 * @return
	 */
	Goods selectGoodsById(String id);
}
