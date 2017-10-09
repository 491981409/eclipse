package com.xxx.controller.otrs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xxx.controller.utils.BaseController;
import com.xxx.service.constant.OtrsMethodEnum;
import com.xxx.service.otrs.TicketOtrsModel;
import com.xxx.service.otrs.TicketOtrsService;
import com.xxx.service.ticket.TicketService;
import com.xxx.utils.CallOtrs;
import com.xxx.utils.RequestParamUtil;
import com.xxx.utils.Result;

@Controller
@RequestMapping("/ticketOtrs")
public class TicketOtrsController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(TicketOtrsController.class);
	
	@Autowired
	private TicketOtrsService ticketOtrsService;
	
	@Autowired
	private TicketService ticketServivce;
	
	
	@RequestMapping("/ticketList")
	@ResponseBody
	public Object ticketList(HttpServletRequest request) throws Exception{
		Map<String,Object> param =  RequestParamUtil.formMap(request);
		param.put("userLogin", "zfx001");
		param.put("status", "open");
		PageInfo result = ticketServivce.getTicketList(param);
		return result;
	}
	
	@RequestMapping("/ticketDetail")
	@ResponseBody
	public Object ticketDetail(HttpServletRequest request) throws Exception{
		String ticketId = request.getParameter("ticketId");
		String userLogin = "13316532095";//request.getParameter("userLogin");
		String ticketJson = ticketOtrsService.getTicketFactory(userLogin, "1458",OtrsMethodEnum.ticket);
		JSONParser parser=new JSONParser();
		JSONObject jsonobj= (JSONObject) parser.parse(ticketJson);
		//String linkJson = ticketOtrsService.getTicketFactory(userLogin, ticketId,OtrsMethodEnum.link);
//		String hisotryJson = ticketOtrsService.getTicketFactory(userLogin,ticketId,OtrsMethodEnum.history);
		Map<String,Object> json = new HashMap<>();
		//((JSONObject)parser.parse(jsonobj.get("ticketInfo").toString())).get("description").toString().replaceAll("\"/otrs(.*?)/weixin.pl", "\"" + "AAAA");
		json.put("ticket", ticketJson.replaceAll("\"/otrs(.*?)/weixin.pl", "\"" +CallOtrs.OTRS_URL));
		//json.put("ticket", ticketServivce.getTicketDetail(ticketId));
//		json.put("link", linkJson);
//		json.put("history", hisotryJson);
		Result result = new Result();
		result.setResult(json);
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(TicketOtrsModel ticket,HttpServletRequest reqeust) throws IOException{
		ticket.setCreateUser("13316532095");
		String path = reqeust.getSession().getServletContext().getRealPath("/");
		path = path.substring(0, path.lastIndexOf("\\"));
		path = path.substring(0, path.lastIndexOf("\\"));
		path = path + File.separator + "upload";
		ticket.setImgFilePath(path);
		ticketOtrsService.add(ticket);
		return new Result();
	}

}
