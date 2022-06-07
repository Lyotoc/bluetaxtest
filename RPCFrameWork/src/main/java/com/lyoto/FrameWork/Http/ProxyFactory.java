package com.lyoto.FrameWork.Http;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Random;

import com.lyoto.FrameWork.Http.Utils.InvoCation;
import com.lyoto.FrameWork.Http.Utils.RemoteRegister;
import com.lyoto.FrameWork.Http.Utils.URL;
import com.lyoto.Provider.api.HelloService;

/**
 @author Lyoto
 @Description
 @Date 2022-06-06 14:46
 @Version
 **/
public class ProxyFactory {

	public static <T> T getProxy(final Class implementClass){
		return (T) Proxy.newProxyInstance(implementClass.getClassLoader(), new Class[] {implementClass}, (proxy, method, args) -> {
			HttpClient httpClient = HttpClient.create();
			InvoCation invoCation = InvoCation.builder().interfaceName(implementClass.getName()).methodName(method.getName())
					.paramTypes(method.getParameterTypes()).params(args).build();
			List<URL> urls = RemoteRegister.get(implementClass.getName());
			URL url = urls.get(new Random().nextInt(urls.size()));
			return httpClient.send(url.getHostName(), Integer.valueOf(url.getPort()), invoCation);
		});
		}

	}
