package face.com.yixiangyang.service.facerecognition;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class getUserInfo {

	public static void main(String[] args) {
		System.out.println(getUser("005", "group_1"));
		

	}

	 public static String getUser(String uId, String groupId) {
	        // 请求url
	        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/get";
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("user_id", uId);
	            map.put("group_id", groupId);
	            String param = JSON.toJSONString(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = "24.fdfdabc426c4265cfc5362186382630e.2592000.1537082890.282335-11690564";

	            String result = HttpUtil.post(url, accessToken, "application/x-www-form-urlencoded", param);
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;

 }
}
