package com.xxx.service.ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.xxx.otrs.domain.ticket.Ticket;
import com.xxx.otrs.domain.ticket.TicketFlag;
import com.xxx.otrs.mapper.ticket.TicketMapper;
import com.xxx.util.sql.Criteria;

@Service
public class TicketService {
	
	@Autowired
	private TicketMapper ticketMapper;
	
	public List<Ticket> query(Map<String,Object> param){
		Criteria criteria = new Criteria(param);
		return ticketMapper.query(criteria);
	}
	
	public void insert(Ticket ticket){
		ticketMapper.insert(ticket);
	}
	
	public List<Map<String,Object>> queryTicket(Map<String,Object> param){
		return ticketMapper.queryTicket(new Criteria(param));
	}
	
	public PageInfo<Map<String,Object>> getTicketList(Map<String,Object> param){
		PageHelper.startPage(param);
		List<Map<String,Object>> ticketList = ticketMapper.queryTicket(new Criteria(param));
		PageInfo<Map<String,Object>> pageInfo =new PageInfo<Map<String,Object>>(ticketList);
		return pageInfo;
	}
	/**
	 * 查询处理中状态的工单
	 * @param param
	 * @return
	 */
	public PageInfo<Map<String,Object>> queryTicketProcess(Map<String,Object> param){
		param.put("status","pending");
		PageHelper.startPage(param);
		List<Map<String,Object>> ticketList = ticketMapper.queryTicket(new Criteria(param));
		PageInfo<Map<String,Object>> pageInfo =new PageInfo<Map<String,Object>>(ticketList);
		return pageInfo;
	}
	
	/**
	 * 获得每个队列 未分配状态总数
	 * @return
	 */
	public List<Map<String,Object>> getTicketUndistributed(){
		Criteria criteria = new Criteria();
		criteria.put("status", "undistributed");
		return ticketMapper.getQueueTicketAmount(criteria);
	}
	
	
	/**
	 * 获得每个队列 处理中状态总数
	 * @return
	 */
	public List<Map<String,Object>> getTicketPendding(){
		Criteria criteria = new Criteria();
		criteria.put("status", "pendding");
		return ticketMapper.getQueueTicketAmount(criteria);
	}
	
	/**
	 * 统计柱形图数据
	 * @return
	 */
	public Object countBar(){
		List<Map<String,Object>>  barList = ticketMapper.countBar();
		Map<String,BarModel> resultMap = new HashMap<>();
		for (Map<String, Object> map : barList) {
			String userId =  map.get("user_id").toString();
			String userName = map.get("user_name").toString();
			String openCount = map.get("open_count").toString();
			String upgradeCount = map.get("upgrade_count").toString();
			BarModel barModel = resultMap.get(userId);
			if(barModel == null){
				BarModel bar = new BarModel();
				bar.setUserName(userName);
				bar.setOpen(Integer.parseInt(openCount));
				bar.setUpgrade(Integer.parseInt(upgradeCount));
				barModel = bar;
			}
		/*	if("open".equals(statusName)){//处理中
				barModel.setOpen(barModel.open + Integer.parseInt(statusCount));
			}else{//提醒挂起
				barModel.setPending(barModel.pending + Integer.parseInt(statusCount));
			}*/
			resultMap.put(userId, barModel);
		}
		return resultMap;
	}
	
	
	/**
	 *  每个队列每天工单总数
	 * @return
	 */
	public Object queueDayTicketCount(){
		List<Map<String,Object>> queueList = ticketMapper.currentMonthTicket();
		Map<String,Object> resultMap = new HashMap<>();
		for (Map<String, Object> map : queueList) {
			String queueId =  map.get("queue_id").toString();
			String ticketCount = map.get("ticket_count").toString();
			String day = map.get("create_day").toString();
			queueId = map.get("queue_name").toString();
			if(resultMap.get(queueId)!= null){
				Map<String,Object> queueMap = (Map<String, Object>) resultMap.get(queueId);
				queueMap.put(day, ticketCount);
			}else{
				Map<String,Object> queueMap = new HashMap<>();
				queueMap.put(day, ticketCount);
				resultMap.put(queueId, queueMap);
			}
		}
		return resultMap;
	}
	
	
	public class BarModel{
		
		private String userName;
		
		private int upgrade; //已升级
		
		private int open; //处理中

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}


		public int getOpen() {
			return open;
		}

		public void setOpen(int open) {
			this.open = open;
		}
		
		public int getUpgrade() {
			return upgrade;
		}

		public void setUpgrade(int upgrade) {
			this.upgrade = upgrade;
		}


	}

}
