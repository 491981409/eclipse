package com.xxx.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class GenerateRandom {
	
	public static String generate(int count)  
    {  
		
		String str = "2,3,5,6,7,8,9,A,B,C,D,E,F,G,H,J,K,M,N,P,Q,R,S,T,U,V,W,X,Y";
		String str2[] = str.split(",");// 将字符串以,分割
		Random rand = new Random();// 创建Random类的对象rand
		int index = 0;
		String randStr = "";// 创建内容为空字符串对象randStr
		for (int i = 0; i < count; ++i) {
			index = rand.nextInt(str2.length - 1);// 在0到str2.length-1生成一个伪随机数赋值给index
			randStr += str2[index];// 将对应索引的数组与randStr的变量值相连接
		}
		return randStr;
		
    }  
	
	public static void main(String[] args) {
		Set<String> list =new HashSet<>();
		for (int i = 0; i < 500; i++) {
			list.add(generate(8));
		}
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}

}
