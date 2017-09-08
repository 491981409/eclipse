package com.xxx.utils.cach;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CachFactory {
	
	private static CachFactory cachFactory = null;  
    
    //缓存已经创建的Map对象  
    private Map<String,Map> factoryMap = new HashMap<String,Map>();  
      
    //单例模式  
    private CachFactory(){  
    }  
      
    //使用“懒加载”的单例模式  
    public static CachFactory getInstance(){  
        if(cachFactory == null){  
            cachFactory = new CachFactory();  
        }  
        return cachFactory;  
    }  
      
    /** 
     * 创建key为cachName的Map对象 
     * @param cachName 
     * @return 
     */  
    public Map<String,Object> createCache(String cachName){
    	Map	map;
    	if(!isExist(cachName)){
    	    map = new HashMap<String,Object>();  
            factoryMap.put(cachName, map);  
    	}else{
    		map = factoryMap.get(cachName);
    	}
        return map;  
    }  
      
    /** 
     * 判断是否存在key为cachName的map 
     * @param cachName 
     * @return 
     */  
    public boolean isExist(String cachName){  
        if(factoryMap.containsKey(cachName)){  
            return true;  
        }  
        return false;  
    }  
      
    /** 
     * 获取key为cachName的map对象 
     * @param cachName 
     * @return 
     */  
    public Map getMapByKey(String cachName){  
        if(factoryMap.containsKey(cachName)){  
            return factoryMap.get(cachName);  
        }  
        return null;  
    } 
    
    
    public String getConfig(String baseKey ,String valueKey){
    	Map map =  getMapByKey(baseKey);
    	if(map !=null && map.containsKey(valueKey)){
    		return map.get(valueKey).toString();
    	}
    	return null;
    }

}
