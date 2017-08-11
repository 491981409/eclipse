package com.xxx.util.sql;

import java.util.HashMap;
import java.util.Map;

public class Criteria {
	
	private Map<String,Object> condition;
	
    public Criteria() {
    	condition = new HashMap<String,Object>();
    }
    
    public Criteria(Map<String,Object> param){
    	condition = param;
    }
    
    public void put(String key ,String value){
    	condition.put(key, value);
    }
    
}
