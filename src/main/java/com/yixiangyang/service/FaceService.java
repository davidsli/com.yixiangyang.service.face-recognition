package com.yixiangyang.service;


import java.util.HashMap;
import org.json.JSONObject;


public interface FaceService {
	/**
	 * 人脸检测 
	 * @param image 采用base64编码后传入
	 * @return
	 */
	public JSONObject faceDetect(String image);
	
	/**
	 * 人脸搜索
	 * @param image
	 * @param groupIdList
	 * @return
	 */
	public JSONObject faceSearch(String image,String groupIdList);
	
	/**
	 * 人脸注册
	 * @param image
	 * @param imageType
	 * @param groupId
	 * @param options
	 * @return
	 */
	public JSONObject addUser(String image ,String imageType,String userId,String groupId,HashMap<String, String> options);
	
	/**
	 * 人脸更新
	 * @param image
	 * @param imageType
	 * @param groupId
	 * @param userId
	 * @param options
	 * @return
	 */
	public JSONObject updateUser(String image,String imageType,String groupId,String userId,HashMap<String, String> options);
	
	/**
	 * 人脸删除
	 * @param userId
	 * @param groupId
	 * @param faceToken
	 * @param options
	 * @return
	 */
	public JSONObject faceDelete(String userId,String groupId,String faceToken,HashMap<String, String> options);
	
	/**
	 * 获取用户信息
	 * @param userId 必选
	 * @param groupId 必选
	 * @param options
	 * @return
	 */
	public JSONObject getUser(String userId,String groupId,HashMap<String, String> options);
	
	/**
	 * 获取用户的人脸列表
	 * @param userId 必选
	 * @param groupId  必选
	 * @param options
	 * @return
	 */
	public JSONObject getUserFaceList(String userId,String groupId,HashMap<String, String> options);
	
	/**
	 * 获取用户列表
	 * @param groupId 必选
	 * @param options  start length 非必选
	 * @return
	 */
	public JSONObject getGroupUsers(String groupId,HashMap<String, String> options);
	
	/**
	 * 复制用户
	 * @param userId 用户ID必选
	 * @param options src_group_id 非必选，从指定组里复制信息 dst_group_id 非必选，需要添加用户的组ID
	 * @return
	 */
	public JSONObject userCopy(String userId,HashMap<String, String> options);
	
	/**
	 * 删除用户
	 * @param groupId 用户组ID 必选
	 * @param userId  用户ID 必选
	 * @param options
	 * @return
	 */
	public JSONObject deleteUser(String groupId,String userId,HashMap<String, String> options);
	
	/**
	 * 创建一个空的用户组
	 * @param groupId 组ID 必选 (由数字字母下划线组成)
	 * @param options
	 * @return
	 */
	public JSONObject groupAdd(String groupId,HashMap<String, String> options);
	
	/**
	 * 删除用户组
	 * @param groupId 组ID
	 * @param options
	 * @return
	 */
	public JSONObject groupDelete(String groupId,HashMap<String, String> options);
	
	/**
	 * 获取用户组列表
	 * @param options  start 起始序号 非必选,length 返回数量 非必选 默认100 最大值1000
	 * @return
	 */
	public JSONObject getGroupList(HashMap<String, String> options);
	
	/**
	 * 身份验证
	 * @param image 总数据大小应小于10M 采用BASE64编码上传
	 * @param imageType  BASE64
	 * @param idCardNumber  身份证号 (真实身份证号)
	 * @param name 名称 真实姓名和身份证号匹配
	 * @param options quality_control 图片质量  liveness_control 活体检测控制
	 * @return
	 */
	public JSONObject personVerify(String image,String imageType,String idCardNumber,String name,HashMap<String, String> options);
	
	/**
	 * 语音校验码接口
	 * @param options appid 非必选 百度云创建应用时的唯一标识ID
	 * @return
	 */
	public JSONObject videoSessionCode(HashMap<String, String> options);
	
	/**
	 * 视频活体检测接口
	 * @param video  二进制数组
	 * @param sessionId  语音校验码回话ID
	 * @param options 
	 * @return
	 */
	public JSONObject videoFaceLiveness(byte[] video,String sessionId,HashMap<String, String> options);
	
	/**
	 * 在线活体检测
	 * @param image
	 * @param imageType
	 * @param faceFields
	 * @return
	 */
	public JSONObject faceVerify(String image,String imageType,String faceFields);
}
