package com.ioc.FrameWork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 @author Lyoto
 @Description
 @Date 2022-07-14 16:49
 @Version
 **/
@Configuration
public class FactoryBeanConfig {
	@Bean
	public UserFactoryBean userFactoryBean(){
		return new UserFactoryBean();
	}
}
