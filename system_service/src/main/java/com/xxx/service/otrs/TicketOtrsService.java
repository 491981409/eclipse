package com.xxx.service.otrs;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.gson.Gson;
import com.xxx.service.constant.OtrsMethodEnum;
import com.xxx.utils.CallOtrs;
import com.xxx.utils.Result;

@Service
public class TicketOtrsService {
	
	@Autowired
	private CallOtrs otrs;
	
	/**
	 * 获得工单列表
	 * 方法作废 性能差
	 * @param userName
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getTicketList(String userName,String customerUserLogin) throws Exception{
		JSONObject json = new JSONObject();
		json.put("userLogin", userName);
		json.put("customerUserLogin", customerUserLogin);
		Result map = otrs.exeCallOtrs("SearchTicket", json.toString());
		return new Gson().toJson(map);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getTicketFactory(String userName,String ticketId,OtrsMethodEnum type) throws IOException{
		Assert.notNull(type,"ortsMethodEnum is null");
		JSONObject json = new JSONObject();
		json.put("userLogin", userName);
		json.put("ticketID", ticketId);
		Result map = otrs.exeCallOtrs(type.getValue(), json.toString());
		if(map.isSuccess()){
			return map.getResult().toString();
		}
		return null;
	}
	
}
