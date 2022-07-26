package com.Lyoto.aop.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-07-25 17:57
 @Version
 **/
public class MathCalculator {
	protected final Log logger = LogFactory.getLog(getClass());
	public int div (int i,int j){
		logger.info("this result ");
		return i/j;
	}
}
