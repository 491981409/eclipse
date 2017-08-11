package com.xxx.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class FrameMessageResource {
	
	  private MessageSource messageSource;

	  public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String messagesKey)
	  {
	    return this.messageSource.getMessage(messagesKey, null, "", LocaleContextHolder.getLocale());
	  }
	  
	  public String getMessage(String messagesKey,String... param)
	  {
	    return this.messageSource.getMessage(messagesKey, param, "", LocaleContextHolder.getLocale());
	  }

}
