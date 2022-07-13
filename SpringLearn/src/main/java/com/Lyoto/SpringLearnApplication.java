package com.Lyoto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

/**
 @author Lyoto
 @Description
 @Date 2022-07-13 14:42
 @Version
 **/
@SpringBootApplication

@ComponentScan("com.Lyoto.*.**")
public class SpringLearnApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringLearnApplication.class, args);
		RedisTemplate bean = run.getBean("redisTemplate",RedisTemplate.class);
		//bean.
		//System.out.println(bean);
	}
}
