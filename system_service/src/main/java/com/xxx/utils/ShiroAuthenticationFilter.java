package com.xxx.utils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class ShiroAuthenticationFilter extends FormAuthenticationFilter{
	
	
	  public AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
	        String username = getUsername(request);
	        String password = getPassword(request);
	        return createToken(username, password, request, response);
	    }
}
