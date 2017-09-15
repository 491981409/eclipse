package com.xxx.controller.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.controller.utils.BaseController;
import com.xxx.service.ticket.TicketDictionaryService;
import com.xxx.utils.Result;

@Controller
@RequestMapping("/ticketDictionary")
public class TicketDictionaryController extends BaseController{
	
	@Autowired
	private TicketDictionaryService ticketDictionaryService;
	
	
	@RequestMapping("/getServiceDictionary")
	@ResponseBody
	public Result getServiceDictionary(){
		Result result = new Result();
		result.setResult(ticketDictionaryService.getServiceDictionary());
		return result;
	}
	
	@RequestMapping("/getSlaDictionary")
	@ResponseBody
	public Result getSlaDictionary(@RequestParam("serviceId")String serviceId){
		Result result = new Result();
		result.setResult(ticketDictionaryService.getSlaDictionaryByServiceId(serviceId));
		return result;
	}
	
}
