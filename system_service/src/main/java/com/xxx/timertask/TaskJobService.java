package com.xxx.timertask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xxx.service.ticket.TicketJobService;
import com.xxx.service.ticket.TicketService;
import com.xxx.socket.EndPointServer;

@Service
public class TaskJobService {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private TicketJobService ticketJobService;
	
	
	
	public void amountJob(){
		String result = assemble();
		for (EndPointServer endPointServer : EndPointServer.sessionList) {
			try {
				endPointServer.session.getBasicRemote().sendText(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 组装报表队列 统计数
	 */
	private String assemble(){
		Map<String,Object> result = new HashMap<>();
		result.put("pendding", ticketService.getTicketPendding());
		result.put("undistributed", ticketService.getTicketUndistributed());
		result.put("ticketList", ticketJobService.queryTicketList());
		result.put("ticketGrideList", ticketJobService.queryTicketGrideList());
		result.put("bar", ticketService.countBar());
		result.put("line", ticketService.countLine());
		String json = new Gson().toJson(result);
		return json;
	}

}
