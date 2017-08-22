package com.xxx.controller.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.xxx.service.ticket.TicketService;

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
	

}
