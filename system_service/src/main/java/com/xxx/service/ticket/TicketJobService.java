package com.xxx.service.ticket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class TicketJobService {
	
	@Autowired
	private TicketService ticketService;
	
	public static volatile int pageNum = 1;
	
	public static volatile int pageNumGride = 1;
	
	public static volatile int pageNumMax = 1;
	
	public static volatile int totalMax = 10;
	
	public static volatile int total = 12;
	
	public static volatile int records;
	
	
	public PageInfo<Map<String,Object>> queryTicketProcessList(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",pageNum++);
		map.put("pageSize",total);
		PageInfo<Map<String,Object>> page = ticketService.queryTicketProcess(map);
		if(pageNum > page.getPages()){
			pageNum = 1;
		}
		return page;
	}
	
	
	public PageInfo<Map<String,Object>> queryTicketGrideList(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",pageNumGride++);
		map.put("pageSize",total);
		PageInfo<Map<String,Object>> page = ticketService.queryTicketGride(map);
		if(pageNumGride > page.getPages()){
			pageNumGride = 1;
		}
		return page;
	}
	
	
	public PageInfo<Map<String,Object>> queryTicketList(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",pageNumMax++);
		map.put("pageSize",totalMax);
		PageInfo<Map<String,Object>> page = ticketService.getTicketList(map);
		if(pageNumMax > page.getPages()){
			pageNumMax = 1;
		}
		return page;
	}

}
