package com.Lyoto.FrameWork.config;

import java.util.Arrays;

import com.Lyoto.Business.Beans.Student;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-07-15 15:14
 @Version
 **/
@Slf4j
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.info("当前已加载{}个Bean",beanFactory.getBeanDefinitionCount());
		Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(log::info);
	}

}
