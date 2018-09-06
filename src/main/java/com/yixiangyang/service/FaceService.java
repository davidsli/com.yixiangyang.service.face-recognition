package com.yixiangyang.service;

public interface FaceService {
	/**
	 * 人脸检测
	 * @param imageFile 二进制的文件
	 * @return
	 */
	public String faceDetect(byte[] imageFile );
	
	/**
	 * 人脸检测
	 * @param imageBase64 传入一个base64的图片
	 * @return
	 */
	public String faceDetect(String imageBase64);
}
