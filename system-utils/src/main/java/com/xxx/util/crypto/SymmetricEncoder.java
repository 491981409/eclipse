package com.xxx.util.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.druid.util.Base64;


public class SymmetricEncoder {
	
	/**
	 * 加密
	 * @param encodeRules
	 * @param content
	 * @return
	 */
	public static String AESEncode(String encodeRules,String content){
		try{
			//1.构造密匙生成器，指定为AES算法，不区分大小
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			//2.根据ecnodeRules规则初始化密匙生成器
			//c生成一个128为的随机源，根据传入的字节数组
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			//3.产生原始对称密匙
			SecretKey original_key = keygen.generateKey();
			byte[] raw= original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw,"AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
		    byte [] byte_encode = content.getBytes("utf-8");
		    byte [] byte_AES = cipher.doFinal(byte_encode);
		    String AES_encode = null; //= new String(Base64.encodeBase64(byte_AES),"utf-8");
		    return AES_encode;
		}catch(Exception ex){
			
		}
		return null;
	}
	
	/**
	 * 解密
	 * @param encodeRules
	 * @param content
	 * @return
	 */
	public static String AESDncode(String encodeRules,String content){
		try{
			//1.构造密匙生成器，指定为AES算法，不区分大小
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			//2.根据ecnodeRules规则初始化密匙生成器
			//c生成一个128为的随机源，根据传入的字节数组
			keygen.init(128, new SecureRandom(encodeRules.getBytes()));
			//3.产生原始对称密匙
			SecretKey original_key = keygen.generateKey();
			//4.获得原始对称密匙的字节数组
			byte[] raw= original_key.getEncoded();
			//5.根据字节数组生成AES密匙
			SecretKey key = new SecretKeySpec(raw,"AES");
			//6.根据指定算法AES子成密码器
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			//7.初始化密码器，第一个参数为加密 或者解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			//8.将加密并编码后的内容解码成字节数组
			byte [] byte_content = null;//Base64.decodeBase64(content);
			byte []byte_decode = cipher.doFinal(byte_content);
			String AES_decode = new String (byte_decode,"utf-8");
			return AES_decode;
		}catch(Exception ex){
			
		}
		return null;
		
	}
	
	
	public static void main(String [] args){
	String encode =SymmetricEncoder.AESEncode("aa", "bb");
	String content=	SymmetricEncoder.AESDncode("aa", encode);
	System.out.println(encode+":"+content);
	}

}
