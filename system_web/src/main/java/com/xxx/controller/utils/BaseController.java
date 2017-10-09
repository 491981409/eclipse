package com.xxx.controller.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.utils.RequestParamUtil;
import com.xxx.utils.Result;
import com.xxx.utils.ServiceException;

@ControllerAdvice
public abstract class BaseController {
	
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 全局异常处理.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public Result runtimeExceptionHandler(final HttpServletRequest request , Exception ex) throws ServletException {
    	Result model = new Result();
    	model.setFailure();
        logger.error(ex.getMessage(), ex);
        logger.error(RequestParamUtil.formJSONObject(request).toString());
        if (ex instanceof org.mybatis.spring.MyBatisSystemException) {
            model.setMsg("数据库连接异常!");
            model.setCode("5001");
        }else if (ex instanceof MissingServletRequestParameterException){
        	model.setMsg("请求参数异常!");
            model.setCode("5002");
        }else if(ex instanceof ServiceException){
        	model.setMsg(ex.getMessage());
        	model.setCode("50003");
        }else {
        	model.setMsg("系统异常!");
        	model.setCode("5000");
        }
        return model;
    }

}
