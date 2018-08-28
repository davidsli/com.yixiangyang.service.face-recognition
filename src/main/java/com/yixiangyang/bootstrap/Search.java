package com.yixiangyang.bootstrap;

import java.util.HashMap;
import java.util.Map;

public class Search {
	public static String search() {
		String path = "F:\\\\dd/me.jpg";
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
		String image = Base64ImageUtils.GetImageStrFromPath(path);
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("image", image);
			map.put("liveness_control", "NORMAL");
			map.put("group_id_list", "group_1");
			map.put("image_type", "BASE64");
			map.put("quality_control", "LOW");

			String param = GsonUtils.toJson(map);

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "24.fdfdabc426c4265cfc5362186382630e.2592000.1537082890.282335-11690564";

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(search());

	}

}
