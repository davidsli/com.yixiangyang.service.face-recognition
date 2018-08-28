package com.yixiangyang.bootstrap;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class SearchFace {
	public static final String APP_ID = "11690564"; //appId
    public static final String API_KEY = "dgpSxKd0VFTrdeVTYGbjTGnM"; //apiKey
    public static final String SECRET_KEY = "ltCMGxFiNRSIYQ1QHQghZUh8BcIqDkkl"; //SecretKey
    
	public static void main(String[] args) {
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
//		HashMap<String, String> options = new HashMap<String, String>();
//	    options.put("face_field", "age");
//	    options.put("max_face_num", "5");
//	    options.put("face_type", "LIVE");
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
	    options.put("max_user_num", "3");
		// 调用接口
		String path = "F:\\dd/me.jpg";
		String image = Base64ImageUtils.GetImageStrFromPath(path);
		String groupIdList = "group_1";
		JSONObject res = client.search(image, "BASE64", groupIdList, options);
//		client.
		System.out.println(res.toString(2));

	}
}
