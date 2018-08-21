package com.yixiangyang.bootstrap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	public static final <T>T toObject(String json, Class<T> clazz) {
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static final <T>Object toObject(String json){
		return JSONObject.parse(json);
	}
}
