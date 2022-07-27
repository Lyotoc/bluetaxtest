package com.Lyoto.aop;

import com.Lyoto.aop.Config.AspectsConfig;
import com.Lyoto.aop.Service.MathCalculator;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 @author Lyoto
 @Description
 @Date 2022-07-26 9:51
 @Version
 **/
public class AopApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectsConfig.class);
		MathCalculator mathCalculator = context.getBean("mathCalculator", MathCalculator.class);
		mathCalculator.div(5, 0);
		context.close();
	}
}
