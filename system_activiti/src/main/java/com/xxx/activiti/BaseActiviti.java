package com.xxx.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class BaseActiviti {
	
	@Autowired
	@Qualifier("processEngine")
	protected ProcessEngine processEngine;
	
	@Autowired
	@Qualifier("runtimeService")
	protected RuntimeService runtimeService;
	
	
	@Autowired
	@Qualifier("repositoryService")
	protected RepositoryService repositoryService;
	
	
	@Autowired
	@Qualifier("taskService")
	protected TaskService taskService;
	
	@Autowired
	@Qualifier("historyService")
	protected HistoryService historyService;
	
	@Autowired
	@Qualifier("managementService")
	protected ManagementService managementService;
	
	
	
	
}
