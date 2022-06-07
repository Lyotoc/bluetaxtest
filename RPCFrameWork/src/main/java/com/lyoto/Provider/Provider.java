package com.lyoto.Provider;

import java.util.Collections;
import java.util.LinkedList;

import com.lyoto.FrameWork.Http.HttpServer;
import com.lyoto.FrameWork.Http.Utils.LocalRegister;
import com.lyoto.FrameWork.Http.Utils.RemoteRegister;
import com.lyoto.FrameWork.Http.Utils.URL;
import com.lyoto.Provider.api.HelloService;
import com.lyoto.Provider.impl.HelloServiceImpl;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 17:14
 @Version
 **/
public class Provider {
	public static void main(String[] args) {
		LocalRegister.addRegister(HelloService.class.getName(), HelloServiceImpl.class);
		URL url= URL.builder().hostName("localhost").port("8080").build();

		RemoteRegister.register(HelloService.class.getName(), Collections.singletonList(url));
		HttpServer httpServer = new HttpServer();
		httpServer.start("localhost",8080);
		LinkedList<String> list = new LinkedList<>();
	}
}
