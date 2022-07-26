package com.Lyoto.ioc.FrameWork.config;

import java.util.concurrent.TimeUnit;

import com.Lyoto.ioc.Business.Beans.User;

import org.springframework.beans.factory.FactoryBean;

/**
 @author Lyoto
 @Description
 @Date 2022-07-14 16:47
 @Version
 **/
public class UserFactoryBean implements FactoryBean<User> {
	private User singletonInstance;
	@Override
	public User getObject() {
		if (isSingleton()){
			//synchronized (this){
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			singletonInstance = singletonInstance == null ? new User() : singletonInstance;
			//}
		}
		return singletonInstance;
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
