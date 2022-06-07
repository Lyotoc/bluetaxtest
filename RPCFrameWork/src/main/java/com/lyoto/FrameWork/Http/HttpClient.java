package com.lyoto.FrameWork.Http;

import cn.hutool.http.HttpConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyoto.FrameWork.Http.Utils.InvoCation;

/**
 @author Lyoto
 @Description
 @Date 2022-06-02 17:49
 @Version
 **/
public class HttpClient {
	private HttpClient(){}
	public static HttpClient create(){
		return new HttpClient();
	}
	public String send(String hostName,Integer port, InvoCation invoCation) {
		try (HttpResponse execute = HttpUtil.createPost(hostName + ":" + port).body(JSONObject.toJSONString(invoCation))
				.execute()) {
			return execute.body();
		}
	}
}
