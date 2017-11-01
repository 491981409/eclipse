package com.xxx.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxx.utils.redis.RedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:spring-mvc.xml","classpath:spring-*.xml"}) //加载配置文件  
public class BaseJunit4Test {
	

}
