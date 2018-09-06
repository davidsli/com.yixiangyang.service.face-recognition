package com.yixiangyang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;
import com.yixiangyang.service.BaiDuFaceService;

@Service
public class BaiDuFaceServiceImpl implements BaiDuFaceService{

	@Value("${baidu.appid}")
	private String APP_ID;
	@Value("${baidu.appkey}")
	private String APP_KEY;
	@Value("${baidu.secretkey}")
	private String SECRET_KEY;
	
	private AipFace client ;
	
	@PostConstruct
	public void init() {
		client = new AipFace(APP_ID, APP_KEY, SECRET_KEY);
	}
	
	@Override
	public JSONObject faceDetect(String image) {
		HashMap<String, String> options = new HashMap<String, String>();
	    options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype");
	    options.put("max_face_num", "2");
	    options.put("face_type", "LIVE");
		JSONObject obj= client.detect(image, "BASE64", options);
		return obj;
	}

	@Override
	public JSONObject faceSearch(String image, String groupIdList) {
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
//	    options.put("user_id", "233451");
	    options.put("max_user_num", "3");
		JSONObject obj = client.search(image, "BASE64", groupIdList, options);
		return obj;
	}

	@Override
	public JSONObject addUser(String image, String imageType,String userId, String groupId, HashMap<String, String> options) {
		JSONObject obj = client.addUser(image, imageType, groupId, userId, options);
		return obj;
	}

	@Override
	public JSONObject updateUser(String image, String imageType, String groupId, String userId,
			HashMap<String, String> options) {
		JSONObject obj = client.updateUser(image, imageType, groupId, userId, options);
		return obj;
	}

	@Override
	public JSONObject faceDelete(String userId, String groupId, String faceToken, HashMap<String, String> options) {
		JSONObject obj = client.faceDelete(userId, groupId, faceToken, options);
		return obj;
	}

	@Override
	public JSONObject getUser(String userId, String groupId, HashMap<String, String> options) {
		JSONObject obj = client.getUser(userId, groupId, options);
		return obj;
	}

	@Override
	public JSONObject getUserFaceList(String userId, String groupId, HashMap<String, String> options) {
		JSONObject obj = client.faceGetlist(userId, groupId, options);
		return obj;
	}

	@Override
	public JSONObject getGroupUsers(String groupId, HashMap<String, String> options) {
		JSONObject obj = client.getGroupUsers(groupId, options);
		return obj;
	}

	@Override
	public JSONObject userCopy(String userId, HashMap<String, String> options) {
		JSONObject obj = client.userCopy(userId, options);
		return obj;
	}

	@Override
	public JSONObject deleteUser(String groupId, String userId, HashMap<String, String> options) {
		JSONObject obj = client.deleteUser(groupId, userId, options);
		return obj;
	}

	@Override
	public JSONObject groupAdd(String groupId, HashMap<String, String> options) {
		JSONObject obj = client.groupAdd(groupId, options);
		return obj;
	}

	@Override
	public JSONObject groupDelete(String groupId, HashMap<String, String> options) {
		JSONObject obj = client.groupDelete(groupId, options);
		return obj;
	}

	@Override
	public JSONObject getGroupList(HashMap<String, String> options) {
		JSONObject obj = client.getGroupList(options);
		return obj;
	}

	@Override
	public JSONObject personVerify(String image, String imageType, String idCardNumber, String name,
			HashMap<String, String> options) {
		JSONObject obj = client.personVerify(image, imageType, idCardNumber, name, options);
		return obj;
	}

	@Override
	public JSONObject videoSessionCode(HashMap<String, String> options) {
		JSONObject obj = client.videoSessioncode(options);
		return obj;
	}

	@Override
	public JSONObject videoFaceLiveness(byte[] video, String sessionId, HashMap<String, String> options) {
		JSONObject obj = client.videoFaceliveness(sessionId, video, options);
		return obj;
	}

	@Override
	public JSONObject faceVerify(String image, String imageType, String faceFields) {
		FaceVerifyRequest req = new FaceVerifyRequest(image, imageType, faceFields);
		ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
		list.add(req);
		JSONObject obj = client.faceverify(list);
		return obj;
	}
	
}
