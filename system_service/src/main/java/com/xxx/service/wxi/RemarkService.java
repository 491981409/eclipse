package com.xxx.service.wxi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.util.sql.Criteria;
import com.xxx.wxi.domain.base.Remark;
import com.xxx.wxi.mapper.base.RemarkMapper;

@Service
public class RemarkService {
	
	@Autowired
	private RemarkMapper remarkMapper;
	
	public List<Remark> query(){
		return remarkMapper.queryRemark();
	}
	
	
	public PageInfo<Remark> queryPage(Map<String,Object> param){
		PageHelper.startPage(param);
		List<Remark> remarkList = remarkMapper.query(new Criteria(param));
		PageInfo<Remark> pageInfo =new PageInfo<Remark>(remarkList);
		return pageInfo;
	}

}
