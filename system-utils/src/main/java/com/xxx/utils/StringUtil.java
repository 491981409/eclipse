package com.xxx.utils;

import java.util.UUID;

public class StringUtil {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
