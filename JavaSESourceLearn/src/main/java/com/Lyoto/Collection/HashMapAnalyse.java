package com.Lyoto.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.Lyoto.Utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 @author Lyoto
 @Description
 @Date 2022-07-21 16:04
 @Version
 **/
@Slf4j
public class HashMapAnalyse {
	public HashMap<String,String> stringHashMap = new HashMap<>(16);

	public static void main(String[] args) {
		CommonUtils.doSomethingByCondition(true,()->{log.info("this condition is {}",true);});
	}
}
