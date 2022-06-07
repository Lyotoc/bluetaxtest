package com.lyoto.FrameWork.Http;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.lyoto.FrameWork.Http.Utils.InvoCation;
import com.lyoto.FrameWork.Http.Utils.LocalRegister;


/**
 @author Lyoto
 @Description
 @Date 2022-06-02 16:10
 @Version
 **/
public class HttpServletHandler {
	public void handler(ServletRequest request, ServletResponse response){
		//处理逻辑
		try {
			InvoCation invoCation = JSONObject.parseObject(request.getInputStream(), InvoCation.class);
			Class implClass = LocalRegister.get(invoCation.getInterfaceName());
			Method method = implClass.getMethod(invoCation.getMethodName(), invoCation.getParamTypes());
			String result = (String) method.invoke(implClass.newInstance(), invoCation.getParams());
			IoUtil.write(response.getOutputStream(),false,result.getBytes(StandardCharsets.UTF_8));
		}
		catch (IOException | NoSuchMethodException | InvocationTargetException | IllegalAccessException |
			   InstantiationException e) {
			throw new RuntimeException(e);
		}catch (RuntimeException e){
			System.out.println(e.getMessage());
		}

	}
}
