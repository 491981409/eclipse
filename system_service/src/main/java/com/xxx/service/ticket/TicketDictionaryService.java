package com.xxx.service.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.otrs.domain.ticket.TicketDictionary;
import com.xxx.otrs.mapper.ticket.TicketDictionaryMapper;

@Service
public class TicketDictionaryService {
	
	@Autowired
	private TicketDictionaryMapper ticketDictionaryMapper;
	
	
	public List<TicketDictionary> getServiceDictionary(){
		return ticketDictionaryMapper.getServiceDictionary();
	}
	
	
	/**
	 * 根据服务id 获得 服务水平协议
	 * @param serviceId
	 * @return
	 */
	public List<TicketDictionary> getSlaDictionaryByServiceId(String serviceId){
		return ticketDictionaryMapper.getSlaDictionaryByServiceId(serviceId);
	}

}
