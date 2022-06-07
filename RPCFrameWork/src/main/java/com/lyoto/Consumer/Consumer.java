package com.lyoto.Consumer;

import com.lyoto.FrameWork.Http.HttpClient;
import com.lyoto.FrameWork.Http.ProxyFactory;
import com.lyoto.FrameWork.Http.Utils.InvoCation;
import com.lyoto.FrameWork.Http.Utils.RemoteRegister;
import com.lyoto.Provider.api.HelloService;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 17:47
 @Version
 **/
public class Consumer {
	public static void main(String[] args) {
		//jdk动态代理
		HelloService service = ProxyFactory.getProxy(HelloService.class);
		String lyoto = service.sayHello("lyoto");
		System.out.println(lyoto);
	}
}
