package com.xxx.otrs.mapper.ticket;

import java.util.List;

import com.xxx.otrs.domain.ticket.TicketDictionary;

public interface TicketDictionaryMapper {
	
	public List<TicketDictionary> getServiceDictionary();
	
	public List<TicketDictionary> getSlaDictionaryByServiceId(String serviceId);

}
