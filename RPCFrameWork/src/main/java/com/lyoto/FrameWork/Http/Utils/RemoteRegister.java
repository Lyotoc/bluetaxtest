package com.lyoto.FrameWork.Http.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import jdk.nashorn.internal.runtime.options.Options;

/**
 @author Lyoto
 @Description
 @Date 2022-06-06 14:27
 @Version
 **/
public class RemoteRegister implements Serializable {
	static File file = new File("E:\\interface.txt");
	private static final Map<String ,List<URL>> REGISTER = getFile();
	public static List<URL> get(String interfaceName){
		List urls = REGISTER.get(interfaceName);

		return (List<URL>) urls.stream().map(a -> {
			JSONObject a1 = (JSONObject) a;
			return URL.builder().port(a1.getString("port")).hostName(a1.getString("hostName")).build();
		}).collect(Collectors.toList());

	}
	public static void register(String interfaceName,List<URL> urls){
		REGISTER.put(interfaceName,urls);
		saveFile();
	}

	private static void saveFile() {
			if (!file.exists()){
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		Map<String, List<URL>> map = getFile();
		REGISTER.forEach((k,v)->{
			map.computeIfPresent(k,(k1,v1)->v);
			map.putIfAbsent(k,v);
		});
		BufferedWriter bufferedWriter =null;
		try {
			bufferedWriter = Files.newBufferedWriter(file.toPath());
			String jsonString = JSON.toJSONString(map);
			bufferedWriter.write(jsonString);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}finally {
			if (bufferedWriter != null){
				try {
					bufferedWriter.close();
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static Map<String,List<URL>> getFile() {
		InputStreamReader reader= null;
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
			char[] bytes = new char[128];
			StringBuilder builder = StrUtil.builder();
			while (bufferedReader.read(bytes) != -1){
				for (char aByte : bytes) {
					builder.append(aByte);
				}
			}
			String s = builder.toString();
			if (StrUtil.isBlank(s)){
				return new HashMap<>();
			}
			return JSON.parseObject(s, Map.class);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}finally {
			if (Objects.nonNull(reader)){
				try {
					reader.close();
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
