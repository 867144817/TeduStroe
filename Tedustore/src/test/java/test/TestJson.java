package test;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tedu.store.bean.Address;
import cn.tedu.store.bean.ResponseResult;

public class TestJson {
	public static void main(String[] args) throws JsonProcessingException {
		ResponseResult<List<Address>> rr = new ResponseResult<List<Address>>();
		rr.setState(1);
		rr.setMessage("保存成功");
		
//		List<Address> addresslist = addressService.selectAddressByUid(1,str);
		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(user);
		String jsonlist = mapper.writeValueAsString(rr);
		System.out.println(jsonlist);
	}
}
