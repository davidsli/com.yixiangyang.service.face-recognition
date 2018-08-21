package com.yixiangyang.bootstrap;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 人脸识别
 * @author 15138
 *
 */
public class FaceRecognition {

	public static void main(String[] args) {
		String path = "F:\\dd/me.jpg";
		String image = Base64ImageUtils.GetImageStrFromPath(path);
		
		System.out.println("ggg");
		System.out.println(image);
		System.out.println("ggg"+faceRecognition("group_1", image, "faceliveness", "3"));
	}

	/**
	 * 
	 * @param groupId 组ID
	 * @param image base64编码后的图片数据，需urlencode，每次只支持单张图片，编码后的图片大小不超过10M
	 * @param extFilds 特殊返回信息，多个用逗号分隔，取值固定: 目前支持faceliveness(活体检测)。注：需要用于判断活体的图片，图片中的人脸像素面积需要不小于100px*100px，人脸长宽与图片长宽比
	 * @param userTopNum 识别后返回的用户top数，默认为1，最多返回5个
	 * @return
	 */
	public static String faceRecognition(String groupId,String image,String extFilds,String userTopNum) {
		 //String url = "https://aip.baidubce.com/rest/2.0/face/v3/identify";
		String url = "https://aip.baidubce.com/rest/2.0/face/v2/faceverify";
	        try {
	            Map<String, Object> map = new HashMap<>();
	            map.put("image", image);
	            map.put("max_face_num", "2");
	            map.put("face_fields", "qualities");
	            //map.put("group_id", groupId);
	            //map.put("ext_fields", extFilds);
	            //map.put("user_top_num", userTopNum);
	            String param = JSON.toJSONString(map);

	            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
	            String accessToken = "24.fdfdabc426c4265cfc5362186382630e.2592000.1537082890.282335-11690564";

	            String result = HttpUtil.post(url, accessToken, "application/x-www-form-urlencoded", param) ;
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return null;
	}
}
