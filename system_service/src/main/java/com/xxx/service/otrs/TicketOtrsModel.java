package com.xxx.service.otrs;

import org.springframework.web.multipart.MultipartFile;

public class TicketOtrsModel {
	
private String ticketState; //工单状态
	
	private String sleepTime; //挂起时间
	
	private String ticketService;//服务
	
	private String sla; //服务水平协议
	
	private String note;//问题描述
	
	private String createUser; //创建人
	
	private MultipartFile[] imgFile; //附件
	
	private String imgFilePath;//文件存放路径

	public String getImgFilePath() {
		return imgFilePath;
	}

	public void setImgFilePath(String imgFilePath) {
		this.imgFilePath = imgFilePath;
	}

	public MultipartFile[] getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile[] imgFile) {
		this.imgFile = imgFile;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getTicketState() {
		return ticketState;
	}

	public void setTicketState(String ticketState) {
		this.ticketState = ticketState;
	}

	public String getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}

	public String getTicketService() {
		return ticketService;
	}

	public void setTicketService(String ticketService) {
		this.ticketService = ticketService;
	}

	public String getSla() {
		return sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

}
