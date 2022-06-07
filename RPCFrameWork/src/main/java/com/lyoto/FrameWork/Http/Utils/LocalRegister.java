package com.lyoto.FrameWork.Http.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 17:01
 @Version
 **/
public class LocalRegister {
	private LocalRegister(){}
	private static final Map<String,Class> REGISTER_SERVICES = new HashMap<>();
	public static void addRegister(String interfaceName,Class implClass){
		REGISTER_SERVICES.put(interfaceName,implClass);
	}
	public static Class get(String interfaceName){
		return REGISTER_SERVICES.get(interfaceName);
	}
}
