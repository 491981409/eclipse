package com.xxx.controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxx.domain.ticket.Ticket;
import com.xxx.service.ticket.TicketService;
import com.xxx.utils.RequestParamUtil;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private TicketService ticketService;
	
	@SuppressWarnings("unused")
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest request){
		List<Ticket> ticketList = ticketService.query(RequestParamUtil.paramMapPage(request));
		return "core/main";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/toChart")
	public String toChart(HttpServletRequest request){
		return "core/charts";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/toWidgets")
	public String toWidgets(HttpServletRequest request){
		return "core/widgets";
	}

}
