package com.xxx.demo;

import java.io.File;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestfullClient {
	
	@Test
	public void client(){
		RestTemplate rest = new RestTemplate();
		MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
		FileSystemResource resource = new FileSystemResource(new File("E://123.jpg"));
		param.add("multipart", resource);
		param.add("type", "01");
		String result=  rest.postForObject("http://localhost:6080/api/invitation/guid",param,String.class);
		System.out.println(result);
	}

}
