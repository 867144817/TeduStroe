package cn.tedu.store.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	
	@Resource
	private IAddressService addressService;
	@Resource
	private IUserService userService;
	//显示修改地址页
	@RequestMapping("/showAddress.do")
	public String address(HttpSession session) {
		List<Address> addresslist = addressService.selectAddressByUid(getId(session),null);
		session.setAttribute("address", addresslist);
//		for (Address address : addresslist) {
//			System.out.println(address);
//		}
		return "addressAdmin";
	}
	
	
	@RequestMapping("/saveAddress.do")
	@ResponseBody
	public ResponseResult<Void> saveAddress(HttpSession session,Address address){
		ResponseResult<Void> rr = new ResponseResult<Void>();
//		System.out.println(address);
		try {
			address.setUid(getId(session));
			addressService.saveAddress(address);
			rr.setState(1);
			rr.setMessage("保存成功");
			session.setAttribute("user", userService.getUserById(this.getId(session)));
			
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rr;
	}
	@RequestMapping("/addressmsg.do")
	@ResponseBody
	public ResponseResult<List<Address>> addressmsg(HttpSession session,String str,HttpServletResponse response){
		ResponseResult<List<Address>> rr = new ResponseResult<List<Address>>();
//		System.out.println(address);
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			rr.setState(1);
			rr.setMessage("保存成功");
			List<Address> addresslist = addressService.selectAddressByUid(1,str);
			rr.setData(addresslist);
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return rr;
	}
//	跨域
	@RequestMapping(value="/addressmsg2.do",produces={"text/html;charset=UTF-8;","application/json;"})
	@ResponseBody
	public String addressmsg2(
			HttpSession session,
			String str,
			HttpServletRequest request,
			HttpServletResponse response
			){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String callback = request.getParameter("callback");
		
		String result = addressService.getJsonPData(callback);
		return result;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public ResponseResult<Void> update(Integer id,HttpSession session){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
			addressService.updateDefault(id, getId(session));
			rr.setState(1);
			rr.setMessage("设置成功");
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	//删除收货地址
	@RequestMapping("/del.do")
	@ResponseBody
	public String del(Integer id) {
		addressService.deleteAddress(id);
		System.out.println(id);
		return "redirect:../address/showAddress.do";
	}
	
	@RequestMapping("/modify.do")
	@ResponseBody
	public ResponseResult<Address> modify(Integer id){
		ResponseResult<Address> rr = new ResponseResult<Address>();
		try {
			Address address = addressService.selectAddressById(id);
			rr.setState(1);
			rr.setMessage("设置成功");
			rr.setData(address);
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
	
	@RequestMapping("/submitModify.do")
	@ResponseBody
	public ResponseResult<Void> submitModify(Address address){
		ResponseResult<Void> rr = new ResponseResult<Void>();
		try {
//			Address address = addressService.selectAddressById(id);
			System.out.println(address);
			rr.setState(1);
			rr.setMessage("修改成功");
			addressService.updateModifyAddress(address);
		} catch (Exception e) {
			rr.setState(0);
			rr.setMessage(e.getMessage());
		}
		return rr;
	}
}
