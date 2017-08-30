package com.xxx.service.wxi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xxx.util.sql.Criteria;
import com.xxx.wxi.domain.base.AgentCfg;
import com.xxx.wxi.mapper.base.AgentCfgMapper;

@Service
public class AgentCfgService {
	
	@Autowired
	private AgentCfgMapper agentCfgMapper;
	
	public AgentCfg get(String appType,String cfgKey){
		Criteria criteria = new Criteria();
		criteria.put("appType", appType);
		criteria.put("cfgKey", cfgKey);
		List<AgentCfg> list = agentCfgMapper.query(criteria);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}
	
	public List<AgentCfg> getAll(){
		List<AgentCfg> list = agentCfgMapper.query(new Criteria());
		return list;
	}
}
