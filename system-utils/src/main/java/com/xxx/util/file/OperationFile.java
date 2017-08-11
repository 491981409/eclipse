package com.xxx.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

public class OperationFile {
	
	//@Test
	public void readFile(){
		try {
			//获得classes 
			String path = this.getClass().getClassLoader().getResource("template/activationAuthorizeModel.doc").getPath();
			//FileInputStream file = new FileInputStream("");
			FileReader fr = new FileReader("D:\\cgn.xml");
			//InputStreamReader input = new InputStreamReader(file);
			BufferedReader bf = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String s ;
			while((s =bf.readLine()) != null){
				sb.append(s);
			}
			System.out.println(sb.toString());
			bf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		}
		
	}
	
	@Test
	public void writerFile(){
		
		 File imgPath = new File("C:/ecp/code/newECP/code/ecp/ecp.supplier.webapp/target/classes/template/contract");
         if(!imgPath.exists()){//图片目录不存在则创建
             imgPath.mkdirs();
         }
		
	}

}
