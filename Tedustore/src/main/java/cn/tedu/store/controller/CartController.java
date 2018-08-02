package cn.tedu.store.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IGoodsService;
import cn.tedu.store.vo.CartVo;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController{
	@Resource
	private ICartService cartService;
	@Resource
	private IGoodsService goodsService;
	@RequestMapping("/insertCart.do")
	@ResponseBody
	public ResponseResult<Void> insert(Cart cart,HttpSession session) {
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
//			Cart cart = new Cart();
			cart.setUid(this.getId(session));
//			cart.setGoodsId(goodsId);
//			cart.setNum(1);
			System.out.println(cart);
			cart.setCreatedTime(new Date());
			rr.setState(1);
			rr.setMessage("成功");
			cartService.addCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	@RequestMapping("/cart.do")
//	@ResponseBody
	public String selectCart(HttpSession session,ModelMap model){
//		ResponseResult<List<Cart>> rr = new ResponseResult<List<Cart>>();
		List<CartVo> cartList = cartService.selectCartByUid(this.getId(session));
//		rr.setState(1);
//		rr.setData(list);
//		rr.setMessage("成功");
	
		model.addAttribute("cartList", cartList);
		System.out.println(cartList);
		return "cart";
	}
	
	@RequestMapping("/del.do")
	public String delete(Integer[] ids){
		cartService.deleteByBatch(ids);
		System.out.println(ids);
		return "redirect:../cart/cart.do";
	}
	@RequestMapping("/deleteById.do")
	public String deleteByid(Integer id){
		cartService.deleteById(id);
		return "redirect:../cart/cart.do";
	}
	@RequestMapping("/updateCart.do")
	@ResponseBody
	public ResponseResult<Void> updateCart(Integer pnum,Integer id){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		cartService.updateCart(pnum, id);
		return rr;
	}
}
