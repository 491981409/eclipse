package com.xxx.timertask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xxx.service.ticket.TicketJobService;
import com.xxx.service.ticket.TicketService;
import com.xxx.socket.EndPointServer;

@Service
public class TaskJobService {
	private static final Logger log = LoggerFactory.getLogger(TaskJobService.class);
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private TicketJobService ticketJobService;
	
	
	
	public void amountJob(){
		String result = assemble();
		log.debug("=============web socket session timeout:"+EndPointServer.sessionList.size() +" ===================");
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
		result.put("ticketProcessList", ticketJobService.queryTicketProcessList());
		result.put("ticketGrideList", ticketJobService.queryTicketGrideList());
		result.put("ticketList", ticketJobService.queryTicketList());
		result.put("bar", ticketService.countBar());
		result.put("line", ticketService.countLine());
		String json = new Gson().toJson(result);
		return json;
	}

}
