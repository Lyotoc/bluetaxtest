package com.lyoto.Provider.impl;

import com.lyoto.Provider.api.HelloService;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 17:19
 @Version
 **/
public class HelloServiceImpl implements HelloService {
	@Override
	public String sayHello(String name) {
		return "hello" + name;
	}
}
