package com.xxx.controller.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class ForwardController {
	
	@RequestMapping("/{url}/*")
	public String toPage(HttpServletRequest request){
		String uri = request.getRequestURI();
		String url = uri.substring(uri.indexOf("views")+5,uri.length());
		return url;
	}
	
}
