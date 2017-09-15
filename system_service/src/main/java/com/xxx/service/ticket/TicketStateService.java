package com.xxx.service.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.otrs.domain.ticket.TicketState;
import com.xxx.otrs.mapper.ticket.TicketStateMapper;

@Service
public class TicketStateService {
	
	@Autowired
	private TicketStateMapper ticketStateMapper;
	
	public List<TicketState> query(){
		return null;
	}

}
