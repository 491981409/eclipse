package com.xxx.controller.socket;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.xxx.service.ticket.TicketService;
import com.xxx.socket.EndPointServer;

/**
 * 推送控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/push")
public class PushController {

	@Autowired
	private TicketService ticketService;

	/**
	 * 向用户发送消息
	 * 
	 * @param modelMap
	 * @param session
	 * @param sid
	 * @param msg
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toSendMsg")
	public String toSendMsg(ModelMap modelMap, HttpSession session) {
		try {
			String json = new Gson().toJson("");
			for (EndPointServer endPointServer : EndPointServer.sessionList) {
				endPointServer.session.getBasicRemote().sendText(json);
				modelMap.put("msg", 1);// 发送成功
			}
		} catch (IOException e) {
			modelMap.put("msg",0);
			e.printStackTrace();
		}
		return new Gson().toJson(modelMap);
	}

}
