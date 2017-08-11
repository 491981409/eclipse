package com.xxx.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("data")
public class DataController {
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("json")
	@ResponseBody
	public Json getJson(){
		Json json = new Json();
		
		JSONObject obj = new JSONObject();
		obj.put("CategoryName", "Beverages");
		obj.put("ProductName", "Steeleye Stout");
		obj.put("Country", "UK");
		obj.put("Price", "1008.0000");
		obj.put("Quantity", "65");
		
		JSONObject obj1 = new JSONObject();
		obj.put("CategoryName", "Beverages");
		obj.put("ProductName", "Laughing Lumberjack Lager");
		obj.put("Country", "USA");
		obj.put("Price", "140.0000");
		obj.put("Quantity", "10");
		JSONArray array = new JSONArray();
		array.add(obj);
		array.add(obj1);
		JSONObject map = new JSONObject();
		
		map.put("row", array);
		
		
		json.setCode("100");
		json.setMsg("没问题");
		json.setResult("什么鬼");
		
		return json;
	}
	

}
