package com.xxx.service.constant;

public enum OtrsMethodEnum {
	
	ticket("GetTicket"),link("GetTicketLinkList"),history("GetTicketHistory");
	
	private String value;
	
	public String getValue() {
		return value;
	}

	private OtrsMethodEnum(String value){
		this.value = value;
	}
	

}
