package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.vo.CartVo;
@Service
public class BalanceService implements IBalanceService{
	@Resource
	private CartMapper cartMapper;
	
	public List<CartVo> selectBalance(Integer[] ids) {
		 List<CartVo> list = cartMapper.selectBalance(ids);
		return list;
	}
	
}
