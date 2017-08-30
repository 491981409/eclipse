package com.xxx.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.xxx.utils.cach.CachFactory;
import com.xxx.utils.redis.RedisUtil;

@Component
public class CallOtrs {
	
	private static final Logger log = LoggerFactory.getLogger(CallOtrs.class);
	
	@Autowired
	private RedisUtil redisUtil;
	

	public static String OTRS_URL = "";

	public CallOtrs() {
		try {
			CachFactory cach = CachFactory.getInstance();
			String prefix = cach.getConfig("base", "otrsUrlInf");
		    OTRS_URL = prefix + cach.getConfig("base", "otrsUrlInf");;
		} catch (Exception e) {
			log.info("============= orts_url init error:" + OTRS_URL +"==============");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CallOtrs callOtrs = new CallOtrs();
		Map<String, String> jsStr = null;
		try {
			OTRS_URL = "http://172.18.3.202/otrs/weixin.pl";

			//String param = "{\"imageUrl\":\""
					//+ MultiLanguageUtil.getConfigSysString(ConfigUtil.getConfig("base", "language"), "TICKET_TEST")
					//+ "\"," + "\"formId\":\"\"," + "\"customerUserId\":\"chenJserver\"" + "}";
			 Map<String,Object> param = new HashMap<String,Object>();
			 param.put("userLogin","13316532095");
			 param.put("customerUserLogin","18033073739");
			jsStr = callOtrs.exeCallOtrs("SearchTicket", new Gson().toJson(param).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> exeCallOtrs(String subFun, String param) throws IOException {
		log.info("=================excCallOtrs:"+subFun+" param:" +param+"===================");
		Map<String, String> rsltMap = new HashMap<String, String>();

		HttpClient httpClient = new HttpClient();

		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(120000);
		PostMethod postMethod = new PostMethod(OTRS_URL);
		postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");

		postMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(120000));
		postMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
		postMethod.addParameter("subFun", subFun);
		postMethod.addParameter("param", param);

		String errorInfo = "";

		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != 200) {
			errorInfo = "[" + postMethod.getStatusLine().toString() + "]";
		}

		byte[] responseBody = postMethod.getResponseBody();

		String returnInfo = errorInfo + new String(responseBody, "utf-8");

		String httpStatus = String.valueOf(statusCode);

		rsltMap.put("content", returnInfo);
		rsltMap.put("status", httpStatus);

		postMethod.releaseConnection();
		return rsltMap;
	}

	public String getFAQList(String userId, String keyWords) throws HttpException, IOException {
		String errorInfo = "";
		// String httpStatus = "";
		String returnJSON = "";
		String returnInfo = "";

		HttpClient httpClient = new HttpClient();

		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		PostMethod postMethod = new PostMethod(OTRS_URL);
		postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
		postMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(3000));
		postMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());

		postMethod.addParameter("subFun", "WxSearchFaq");
		postMethod.addParameter("User", userId != null ? userId : "");
		postMethod.addParameter("keyWords", keyWords);
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != 200) {
			errorInfo = "[" + postMethod.getStatusLine().toString() + "]";
		}

		byte[] responseBody = postMethod.getResponseBody();

		returnInfo = errorInfo + new String(responseBody, "utf-8");

		postMethod.releaseConnection();
		return returnJSON = returnInfo;
	}

	public String getITSM(String param, String userID) throws IOException {
		String errorInfo = "";
		String returnInfo = "";

		HttpClient httpClient = new HttpClient();

		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
		PostMethod postMethod = new PostMethod(OTRS_URL);

		postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
		postMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(50000));
		postMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());

		postMethod.addParameter("param", param);
		postMethod.addParameter("User", userID);
		postMethod.addParameter("subFun", "SearchCMDB");
		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != 200) {
			errorInfo = "[" + postMethod.getStatusLine().toString() + "]";
		}

		byte[] responseBody = postMethod.getResponseBody();
		returnInfo = errorInfo + new String(responseBody, "utf-8");

		postMethod.releaseConnection();
		return returnInfo;
	}

	public String confirmWeixinLogin(String tid, String userLogin) throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();

		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		PostMethod postMethod = new PostMethod(OTRS_URL);
		postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
		postMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(50000));
		postMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());

		postMethod.addParameter("subFun", "QrcodeVertifyAccess");
		if ((null == tid) || ("".equals(tid)) || (null == userLogin) || ("".equals(userLogin))) {
			postMethod.addParameter("tid", "");
			postMethod.addParameter("user", "");
		} else {
			postMethod.addParameter("tid", tid);
			postMethod.addParameter("user", userLogin);
		}
		String errorInfo = "";

		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != 200) {
			errorInfo = "[" + postMethod.getStatusLine().toString() + "]";
		}

		byte[] responseBody = postMethod.getResponseBody();

		String returnInfo = errorInfo + new String(responseBody, "utf-8");
		// String httpStatus = String.valueOf(statusCode);

		postMethod.releaseConnection();
		return returnInfo;
	}

	public String transferQrcodeData(String tid, String userLogin) throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();

		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		PostMethod postMethod = new PostMethod(OTRS_URL);
		postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
		postMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(30000));
		postMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());

		postMethod.addParameter("subFun", "ScanQrcodeLogin");
		postMethod.addParameter("tid", tid);
		postMethod.addParameter("userLogin", userLogin != null ? userLogin : "");
		String errorInfo = "";

		int statusCode = httpClient.executeMethod(postMethod);
		if (statusCode != 200) {
			errorInfo = "[" + postMethod.getStatusLine().toString() + "]";
		}

		byte[] responseBody = postMethod.getResponseBody();

		String returnInfo = errorInfo + new String(responseBody, "utf-8");
		// String httpStatus = String.valueOf(statusCode);

		postMethod.releaseConnection();
		return returnInfo;
	}

}
