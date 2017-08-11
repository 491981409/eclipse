package com.xxx.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;


public class RequestParamUtil {
	
	
	public static Map<String, Object> formMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				map.put(key, values.length == 1 ? values[0].trim() : values);
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> formMapDelEmpty(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				if(values.length==1&&!StringUtils.isEmpty(values[0])){
					map.put(key, values.length == 1 ? values[0].trim() : values);
				}
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject formJSONObject(HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				obj.put(key, values.length == 1 ? values[0].trim() : values);
			}
		}
		return obj;
	}

	public static Model formModel(HttpServletRequest request, Model model){
		@SuppressWarnings("unchecked")
		Map<String, String[]> tmp = request.getParameterMap();
		if (tmp != null) {
			for (String key : tmp.keySet()) {
				String[] values = tmp.get(key);
				model.addAttribute(key, values.length == 1 ? values[0].trim() : values);
			}
		}
		return model;
	}
	
	
	public static Map<String,Object> paramMapPage(HttpServletRequest request){
		Map<String,Object> param =  formMapDelEmpty(request);
		param.put("pageNum", request.getParameter("page"));
		param.put("pageSize", request.getParameter("rows"));
		return param;
	}
}
