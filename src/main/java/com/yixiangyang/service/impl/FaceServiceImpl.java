package com.yixiangyang.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.yixiangyang.service.FaceService;

@Service
public class FaceServiceImpl implements FaceService{
	private String API_KEY = "BJGDLXA2dwdqjMgYeD7mo38fNUmstFnI";
	private String API_SECRET = "Z5d57gqX1RuqQ7g3ZMoTsQpyZVy4b_xg";
//	private String API_KEY = "BJGDLXA2dwdqjMgYeD7mo38fNUmstFnI";
//	private String API_SECRET = "";

	@Override
	public String faceDetect(byte[] imageFile) {
		String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
		 HashMap<String, String> map = new HashMap<>();
	        HashMap<String, byte[]> byteMap = new HashMap<>();
	        map.put("api_key", API_KEY);
	        map.put("api_secret", API_SECRET);
			map.put("return_landmark", "1");
	        map.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
	        byteMap.put("image_file", imageFile);
	        String str = null;
	        try{
	        	byte[] bacd = FaceUtil.post(url, map, byteMap);
	            str = new String(bacd);
	            System.out.println(str);
	        }catch (Exception e) {
	        	e.printStackTrace();
			}
		return str;
	}

	@Override
	public String faceDetect(String imageBase64) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
