package com.xxx.controller.ticket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.xxx.service.ticket.TicketJobService;
import com.xxx.service.ticket.TicketService;
import com.xxx.utils.Result;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	
	@RequestMapping("/bar")
	@ResponseBody
	public String bar(){
		return new Gson().toJson(ticketService.countBar());
	}
	
	@RequestMapping("/ticketProcess")
	@ResponseBody
	public Result ticketListProcess(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",TicketJobService.pageNum);
		map.put("pageSize",TicketJobService.total);
		PageInfo<Map<String,Object>> page = ticketService.queryTicketProcess(map);
		Result result =  new Result();
		result.setResult(page);
		return result;
	}
	
	@RequestMapping("/ticketGride")
	@ResponseBody
	public Result ticketGride(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",TicketJobService.pageNumGride);
		map.put("pageSize",TicketJobService.total);
		PageInfo<Map<String,Object>> page = ticketService.queryTicketGride(map);
		Result result =  new Result();
		result.setResult(page);
		return result;
	}
	
	
	@RequestMapping("/ticketList")
	@ResponseBody
	public Result ticketList(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",TicketJobService.pageNumMax);
		map.put("pageSize",TicketJobService.totalMax);
		PageInfo<Map<String,Object>> page = ticketService.getTicketList(map);
		Result result =  new Result();
		result.setResult(page);
		return result;
	}
	
	
	@RequestMapping("/ticketCharts")
	@ResponseBody
	public Result ticketCharts(){
		Result result =  new Result();
		result.setResult(ticketService.countLine());
		return result;
	}
	

}
