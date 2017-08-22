package com.xxx.timertask;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xxx.service.ticket.TicketService;
import com.xxx.socket.EndPointServer;
import com.xxx.utils.listener.ContextUtil;

public class ScheduledService {
	
	private static TicketService ticketService;
	
	
	static{
		ticketService =(TicketService) ContextUtil.getBean(TicketService.class);
		 new ScheduledService().demo();
	}
	public void demo(){
		    Runnable runnable = new Runnable(){
				@Override
				public void run() {
					/*String json = new Gson().toJson(ticketService.getQueueTicketAmount());
					for (EndPointServer endPointServer : EndPointServer.sessionList) {
						try {
							endPointServer.session.getBasicRemote().sendText(json);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}*/
				}
		    };
	        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
	        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
	        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);  
	}
}
