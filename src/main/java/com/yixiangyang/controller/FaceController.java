package com.yixiangyang.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yixiangyang.bootstrap.JSONUtil;
import com.yixiangyang.service.FaceService;

@RestController
public class FaceController {
	@Resource
	private FaceService faceService;
	
	@RequestMapping(value="/v1/face/facedecet",method=RequestMethod.PUT,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> facedecet(@RequestParam("image")String image){
		JSONObject obj = faceService.faceDetect(image);
//		JSONObject obj = faceService.faceSearch(image, "group_1");
		String str = obj.toString();
		Object o = com.alibaba.fastjson.JSONObject.parse(str);
//		System.out.println(str);
//		
//		System.out.println("这个是:"+obj);
//		String path = "F:\\dd/me.jpg";
//	    String str = Base64ImageUtils.GetImageStrFromPath(path);
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/face_search",method=RequestMethod.GET,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> faceSearch(@RequestParam("image")String image,@RequestParam("group_ids")String groupIds){
		JSONObject obj = faceService.faceSearch(image, groupIds);
		Object o = JSONUtil.toObject(obj.toString());
//		Object o = com.alibaba.fastjson.JSONObject.parse(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/add_user",method=RequestMethod.POST,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> addUser(@RequestParam("image")String image,@RequestParam("image_type")String imageType,
			@RequestParam("user_id")String userId,@RequestParam("group_id")String groupId){
		HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", "这个是用户的信息");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
		JSONObject obj = faceService.addUser(image, imageType, userId, groupId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/update_user_face",method=RequestMethod.PUT,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> updateUserface(@RequestParam("image")String image,@RequestParam("image_type")String imageType,
			@RequestParam("groupId")String groupId,@RequestParam("user_id")String userId){
		HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", "user's info");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
		JSONObject obj = faceService.updateUser(image, imageType, groupId, userId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/delete_face",method=RequestMethod.DELETE,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> deleteface(@RequestParam("user_id")String userId,@RequestParam("group_id")String groupId,
			@RequestParam("face_token")String faceToken){
		HashMap<String, String> options = new HashMap<String, String>();
		JSONObject obj = faceService.faceDelete(userId, groupId, faceToken, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/user",method=RequestMethod.GET,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> getUser(@RequestParam("user_id")String userId,@RequestParam("group_id")String groupId){
		HashMap<String, String> options = new HashMap<String, String>();
		JSONObject obj = faceService.getUser(userId, groupId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/user_faces",method=RequestMethod.GET,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> getUserFaceList(@RequestParam("user_id")String userId,@RequestParam("group_id")String groupId){
		HashMap<String, String> options = new HashMap<String,String>();
		JSONObject obj = faceService.getUserFaceList(userId, groupId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/group_users",method=RequestMethod.GET,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> getGroupUsers(@RequestParam("group_id")String groupId,@RequestParam("start")String start,@RequestParam("length")String length){
		HashMap<String, String> options = new HashMap<String,String>();
		options.put("start", start);
		options.put("length", length);
		JSONObject obj = faceService.getGroupUsers(groupId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/group_users",method=RequestMethod.PUT,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> userCopy(@RequestParam("user_id")String userId,@RequestParam("src_group_id")String srcGroupId,
			@RequestParam("dst_group_id")String dstGroupId){
		HashMap<String, String> options = new HashMap<String,String>();
		options.put("src_group_id", srcGroupId);
		options.put("dst_group_id",dstGroupId);
		JSONObject obj = faceService.userCopy(userId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/delete_user",method=RequestMethod.DELETE,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> deleteUser(@RequestParam("group_id")String groupId,@RequestParam("user_id")String userId){
		HashMap<String, String> options = new HashMap<String,String>();
		JSONObject obj = faceService.deleteUser(groupId, userId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/group",method=RequestMethod.POST,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> createGroup(@RequestParam("group_id")String groupId){
		HashMap<String, String> options = new HashMap<String,String>();
		JSONObject obj = faceService.groupAdd(groupId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/group",method=RequestMethod.DELETE,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> deleteGroup(@RequestParam("group_id")String groupId){
		HashMap<String, String> options = new HashMap<String,String >();
		JSONObject obj = faceService.groupDelete(groupId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/groups",method=RequestMethod.GET,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> getGroupList(@RequestParam("start")String start,@RequestParam("length")String length){
		if("#".equals(start)) {
			start = null;
		}
		if("#".equals(length)) {
			length = null;
		}
		HashMap<String, String> options = new HashMap<>();
		options.put("start", start);
		options.put("length", length);
		JSONObject obj = faceService.getGroupList(options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/person_verify",method=RequestMethod.PUT,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> personVerify(@RequestParam("image")String image,@RequestParam("image_type")String imageType,
			@RequestParam("id_card_number")String idCardNumber,@RequestParam("name")String name){
		HashMap<String, String> options = new HashMap<String, String>();
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	    JSONObject obj = faceService.personVerify(image, imageType, idCardNumber, name, options);
	    Object o = JSONUtil.toObject(obj.toString());
	    return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/person_verify",method=RequestMethod.GET,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> videoSessionCode(){
		HashMap<String, String> options = new HashMap<String, String>();
	    //options.put("appid", "223245"); //appid 创建应用的唯一ID  非必填
		JSONObject obj = faceService.videoSessionCode(options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	//视频活体检测
	@RequestMapping(value="/v1/face/video_face_liveness",method=RequestMethod.POST,produces={"application/json"})
	public ResponseEntity<Object> videoFaceLiveness(@RequestParam("video")MultipartFile file ,@RequestParam("session_id")String sessionId) throws IOException{
//		file.getBytes();
//		MultipartFile file = null;
		InputStream input = file.getInputStream();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		int ch;
		while((ch = input.read()) != -1) {
			byteStream.write(ch);
		}
		byte data[] = byteStream.toByteArray();
		byteStream.close();
		HashMap<String, String> options = new HashMap<String, String>();
		JSONObject obj = faceService.videoFaceLiveness(data, sessionId, options);
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
	
	@RequestMapping(value="/v1/face/face_verify",method=RequestMethod.PUT,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<Object> faceVerify(@RequestParam("image")String image,@RequestParam("image_type")String imageType){
		JSONObject obj = faceService.faceVerify(image, imageType, "age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype");
		Object o = JSONUtil.toObject(obj.toString());
		return new ResponseEntity<Object>(o, HttpStatus.OK);
	}
}
