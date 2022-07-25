package com.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
