package face.com.yixiangyang.service.facerecognition;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

public class faceRegistered {

	public static void main(String[] args) {
		String path = "F:\\dd/me.jpg";
		String image = Base64ImageUtils.GetImageStrFromPath(path);
		System.out.println(createUser("007", "group_1", image, "这个是用户信息", "append"));
	}

	/**
	 * 
	 * @param uId 用户ID由字母数字下划线组成
	 * @param groupId
	 * @param image
	 * @param userInfo
	 * @param actionType
	 * @return
	 */
	 public static String createUser(String uId, String groupId,String image,String userInfo,String actionType) {
		        // 请求url
		        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
		        try {
		            Map<String, Object> map = new HashMap<>();
		            map.put("user_id", uId);
		            map.put("group_id", groupId);
		            map.put("image", image);
		            map.put("image_type", "BASE64");
		            map.put("user_info", userInfo);
		            map.put("action_type", actionType);
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
