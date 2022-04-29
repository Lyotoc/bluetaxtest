package com.lyoto.zhonghai.Utils;

import cn.hutool.crypto.digest.MD5;

import org.springframework.stereotype.Component;

/**
 @author Lyoto
 @Description
 @Date 2022-01-12 14:41
 @Version
 **/
public class EncryptUtil {
	/**
	 * 根据需要参数加密生成sign
	 * @param params
	 * @return
	 */
	static public String generateSign(String[] params){
		StringBuilder chain = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			chain.append(params[i]);
			if(params.length - 1 == i){
				break;
			}
				chain.append("_");
		}
		return MD5.create().digestHex(chain.toString());
	}
}
