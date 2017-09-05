package com.xxx.controller.otrs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xxx.controller.utils.BaseController;
import com.xxx.service.constant.OtrsMethodEnum;
import com.xxx.service.otrs.TicketOtrsService;
import com.xxx.service.ticket.TicketService;
import com.xxx.utils.RequestParamUtil;

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
		PageInfo result = ticketServivce.getTicketList(param);
		return result;
	}
	
	@RequestMapping("/ticketDetail")
	@ResponseBody
	public String ticketDetail(HttpServletRequest request) throws Exception{
		String ticketId = request.getParameter("ticketId");
		String userLogin = request.getParameter("userLogin");
		String ticketJson = ticketOtrsService.getTicketFactory(userLogin, ticketId,OtrsMethodEnum.ticket);
		String linkJson = ticketOtrsService.getTicketFactory(userLogin, ticketId,OtrsMethodEnum.link);
		String hisotryJson = ticketOtrsService.getTicketFactory(userLogin,ticketId,OtrsMethodEnum.history);
		return "";
	}
	

}
