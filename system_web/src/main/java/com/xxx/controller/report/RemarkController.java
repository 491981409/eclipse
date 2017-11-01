package com.xxx.controller.report;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.service.ticket.TicketJobService;
import com.xxx.service.wxi.RemarkService;

@Controller
@RequestMapping("/remark")
public class RemarkController {
	
	@Autowired
	private RemarkService reamrkService;
	
	/**
	 * 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageNum",request.getParameter("pageNumber"));
		map.put("pageSize",request.getParameter("pageSize"));
		return reamrkService.queryPage(map);
	}

}
