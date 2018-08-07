package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.vo.CartVo;

public interface IBalanceService {
	List<CartVo> selectBalance(Integer[] ids);
}
