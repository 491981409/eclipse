package com.xxx.service.otrs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.xxx.otrs.domain.ticket.Ticket;
import com.xxx.service.constant.OtrsMethodEnum;
import com.xxx.utils.CallOtrs;
import com.xxx.utils.Result;
import com.xxx.utils.ServiceException;
import com.xxx.utils.StringUtil;
import com.xxx.utils.date.DateUtil;

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
	
	
	/**
	 * 新增工單
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public void add(TicketOtrsModel ticket) throws IOException{
		String imgHtml ="";
		for (MultipartFile file : ticket.getImgFile()) {
			String fileName =StringUtil.getUUID()+"."+file.getContentType().substring(file.getContentType().indexOf("/")+1);
			String filePath = ticket.getImgFilePath() + File.separator + fileName;
			imgHtml+="<img class=\"zoomImage_img\" src=\"http://www.zyyweixin.com:8081/upload/"+fileName+"\"/>";
			File newFile = new File(filePath);
			FileUtils.writeByteArrayToFile(newFile,file.getBytes());
		}
		Map<String,String> param = new HashMap<>();
		param.put("userLogin",ticket.getCreateUser());
		 param.put("customerUserLogin","18033073739");
		 param.put("action", "AgentCreateTicket");
		 param.put("appName", "TICKET");
		 param.put("Priority", "3");//微信创建默认级别
		 param.put("Type", "4");
		 param.put("Queue", "2");//根据当前用户获得队列id
		 param.put("State", ticket.getTicketState());
		 param.put("Service",ticket.getTicketService());
		 param.put("SLA", ticket.getSla());
		 param.put("DynamicField_ITSMTicketType", "2");
		 param.put("Note",ticket.getNote()+ "<BR><BR><div id=\"UzwRi_PoIe\" class=\"zoomImage_div\">"+imgHtml+"</div>");
		 Result result = otrs.exeCallOtrs("NewTicket",new Gson().toJson(param));
		if(!result.isSuccess()){
			throw new ServiceException("create ticket failure");
		}
		JSONParser parser = new JSONParser();
		JSONObject json;
		try {
			json = (JSONObject) parser.parse(result.getResult().toString());
			if(!"ok".equals(json.get("result"))){
				result.setFailure();
				throw new ServiceException(json.get("result")+"");
			}
		} catch (ParseException e) {
			throw new ServiceException("parse json exception");
		}
		
	}
	
}
