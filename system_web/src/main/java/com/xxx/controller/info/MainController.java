package com.xxx.controller.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxx.otrs.domain.ticket.Ticket;
import com.xxx.service.ticket.TicketService;
import com.xxx.utils.RequestParamUtil;

@Controller
@RequestMapping("/main")
public class MainController {
	
	
	@SuppressWarnings("unused")
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest request){
		return "core/main";
	}
	
	@RequestMapping("/toChart")
	public String toChart(HttpServletRequest request){
		return "core/charts";
	}
	
	@RequestMapping("/toWidgets")
	public String toWidgets(HttpServletRequest request){
		return "core/widgets";
	}

}
