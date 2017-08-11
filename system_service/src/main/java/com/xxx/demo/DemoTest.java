package com.xxx.demo;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DemoTest {
	
	//@Test
	public void main(){
		try {
			URL targetUrl = new URL("http://localhost:8080/sys/loginController/login");
			HttpURLConnection httpConnection =(HttpURLConnection)targetUrl.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type","application/json");
			httpConnection.setDoInput(true);
		    OutputStream out = httpConnection.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			String s ;
			while((s=br.readLine()) != null){
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
