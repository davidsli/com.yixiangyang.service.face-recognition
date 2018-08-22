package com.yixiangyang.bootstrap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.FaceVerifyRequest;

public class Sample {
	public static final String APP_ID = "11690564"; //appId
    public static final String API_KEY = "dgpSxKd0VFTrdeVTYGbjTGnM"; //apiKey
    public static final String SECRET_KEY = "ltCMGxFiNRSIYQ1QHQghZUh8BcIqDkkl"; //SecretKey
    
	public static void main(String[] args) throws IOException {
		AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//		client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
//		client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		//System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
		HashMap<String, String> options = new HashMap<String, String>();
	    options.put("face_field", "age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype");
	    options.put("max_face_num", "2");
	    options.put("face_type", "LIVE");
		// 调用接口
		String path = "F:\\dd/me.jpg";
		FaceVerifyRequest req = new FaceVerifyRequest(Base64ImageUtils.GetImageStrFromPath(path), "BASE64","age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype");
		ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
		list.add(req);
		//在线活体检测
		//JSONObject res = client.faceverify(list);
		JSONObject res = client.detect(Base64ImageUtils.GetImageStrFromPath(path), "BASE64", options);
		//组列表查询
//		HashMap<String, String> options1 = new HashMap<>();
//		options1.put("start", "0");
//	    options1.put("length", "50");
//		JSONObject res = client.getGroupList(options1);
		//获取用户列表
//		HashMap<String, String> options2 = new HashMap<>();
//		options2.put("start", "0");
//	    options2.put("length", "5");
//	    JSONObject res = client.getGroupUsers("group_1", options2);
		//获取用户信息
//		HashMap<String, String> options3 = new HashMap<String, String>();
//		JSONObject res = client.getUser("005", "group_1", options3);
		//人脸搜索
//		 HashMap<String, String> options4 = new HashMap<String, String>();
//		 options4.put("quality_control", "NORMAL");
//		 options4.put("liveness_control", "LOW");
//		// options4.put("user_id", "002");
//		 options4.put("max_user_num", "5");
////
//		 String image = Base64ImageUtils.GetImageStrFromPath(path);
//		 String imageType = "BASE64";
//		 String groupIdList = "group_1";
//		 JSONObject res = client.search(image, imageType, groupIdList, options4);
		
		//视频活体检测接口接口
//		 String vodeopath = "F:\\dd/gg.mp4";
//		 JSONObject res2= client.videoSessioncode(null);
//		 System.out.println(res2.toString());
//		 System.out.println("411");
//		 JSONObject res = client.videoFaceliveness("S5b7be26086cdd747198934", FileUtil.readFileByBytes(vodeopath), null);
		 //JSONObject res = client.videoFaceliveness("S5b7a77a514e44145896709", vodeopath, null);
		 //client.vo
		System.out.println(res.toString(2));

	}

}
