package com.lyoto.zhonghai;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lyoto.zhonghai.Bean.Test;
import com.lyoto.zhonghai.Service.TestService;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MR.L
 */
@SpringBootApplication
@MapperScan("com.lyoto.zhonghai.Service.mapper")
@ComponentScan(basePackages = {"com.lyoto.zhonghai.*"})
public class ZhonghaiApplication{
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ZhonghaiApplication.class, args);
		TestService service = run.getBean("testServiceImpl", TestService.class);
		Test test = new Test();
		test.setMoney(new BigDecimal("1.0"));
		service.save(test);
		Configuration config = new Configuration();
		//Field[] declaredFields = test.getClass().getDeclaredFields();
		//for (Field field:declaredFields) {
		//	Type genericType = field.getGenericType();
		//}
	}

}

