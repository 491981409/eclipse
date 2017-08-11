package com.xxx.service.ticket;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.domain.ticket.Ticket;
import com.xxx.mapper.ticket.TicketMapper;
import com.xxx.util.sql.Criteria;

@Service
public class TicketService  {
	
	@Autowired
	private TicketMapper ticketMapper;
	
	public List<Ticket> query(Map<String,Object> param){
		Criteria criteria = new Criteria(param);
		return ticketMapper.query(criteria);
	}
	
	
	public void insert(Ticket ticket){
		ticketMapper.insert(ticket);
		
	}

}
