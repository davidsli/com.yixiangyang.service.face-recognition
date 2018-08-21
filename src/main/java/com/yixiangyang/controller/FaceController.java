package com.yixiangyang.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yixiangyang.service.FaceService;

@RestController
public class FaceController {
	@Resource
	private FaceService faceService;
	
	@RequestMapping(value="/v1/face/facedecet",method=RequestMethod.PUT,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<String> facedecet(@RequestParam("image")String image){
		System.out.println(image);
//		JSONObject obj = faceService.faceDetect(image);
		JSONObject obj = faceService.faceSearch(image, "group_1");
		String str = obj.toString();
		System.out.println(str);
		
		System.out.println("这个是:"+obj);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
}
