package com.xxx.utils;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class GenerateActivitiTable {
	
	@Test
	/**
	 * 生成表结构
	 */
	public void main(){
		ProcessEngine processEngine =  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();  
		System.out.println("processEngine="+processEngine);  
	}

}
