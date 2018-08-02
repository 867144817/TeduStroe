package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Goods;
import cn.tedu.store.mapper.GoodsMapper;
@Service
public class GoodsService implements IGoodsService{
	@Resource
	private GoodsMapper goodsMapper;
	public List<Goods> selectGoodsByCategoryId(Integer categoryId, Integer offset, Integer count) {
		return goodsMapper.selectGoodsByCategoryId(categoryId, offset, count);
	}
	public Integer selectCount(Integer categoryId) {
		return goodsMapper.selectCount(categoryId);
	}
	public Goods selectGoodsById(String id) {
		return goodsMapper.selectGoodsById(id);
	}
	
}
