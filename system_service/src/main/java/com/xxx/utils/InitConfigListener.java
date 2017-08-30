package com.xxx.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.xxx.service.wxi.AgentCfgService;
import com.xxx.utils.cach.CachFactory;
import com.xxx.wxi.domain.base.AgentCfg;

public class InitConfigListener  implements InitializingBean ,ServletContextAware{
	
	private static final Logger log = LoggerFactory.getLogger(InitConfigListener.class);
	
	@Autowired
	private AgentCfgService agentCfgService;

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		log.info("====== init config data ======");
		List<AgentCfg> list = agentCfgService.getAll();
		CachFactory cach = CachFactory.getInstance();
		for (AgentCfg agentCfg : list) {
			Map cachObj = cach.createCache(agentCfg.getAppType());
			cachObj.put(agentCfg.getCfgKey(), agentCfg.getCfgValue());
		}
	}
}
