package com.xxx.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/index")
public class IndexController {
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String toIndex(HttpServletRequest request){
		return "/index";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String toIndexGet(HttpServletRequest request,Model model){
		model.addAttribute("user", null);
		return "/index";
	}

}
