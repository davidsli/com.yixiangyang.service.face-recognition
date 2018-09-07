package com.yixiangyang.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Document;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.aip.util.Base64Util;
import com.yixiangyang.util.AesException;
import com.yixiangyang.util.WXBizMsgCrypt;
import com.yixiangyang.util.XMLParse;
@RestController
public class WxController {
	private String sToken ="5qxeCPD5unzsbyr8";
	
	private String sEncodingAESKey = "cmn6mVQBLzYrxzhXezO7y73vhuz5F14BPF6eKEKPM8C";
	
	private String corpId ="wwf412e46506b121ae";
	public String verifyURL(String msgSignature, String timestamp, 
            String nonce, String echostr, String corpid) throws AesException{
        //注意创建解密对象时使用的是CORP_ID而不是SUITE_ID
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(sToken, 
        		sEncodingAESKey, corpid);
        String result = wxBizMsgCrypt.VerifyURL(msgSignature, timestamp, nonce, echostr);
        return result;
    }
	
	@RequestMapping(value="/suite/receive")
	@ResponseBody
	public void suiteReceive( HttpServletRequest req,ServletResponse response) throws AesException, IOException{
		/**获取msgSignature*/
//		req.get
		System.out.println("-----------------------------");
		System.out.println("ggg");
//		System.out.println(res);
        String sVerifyMsgSig = req.getParameter("msg_signature");
        /**包体*/
//        String msg_encrypt = req.getParameter("Encrypt");
//        String corpid = req.getParameter("corpid");
		String sVerifyTimeStamp =  req.getParameter("timestamp");
		String sVerifyNonce =  req.getParameter("nonce");
		String sVerifyEchoStr =  req.getParameter("echostr");
		
//		String msg_encrypt = req.getParameter("msg_encrypt");
//		String msg = req.getParameter("msg");
//		System.out.println(msg);
//		String encrypt = req.getParameter("encrypt");
//		System.out.println("encrypt:"+encrypt);
//		System.out.println("mag_encrypt:"+msg_encrypt);
//		System.out.println("Encrypt"+req.getParameter("Encrypt"));
		
		System.out.println("corpId:"+corpId +" \n  msg_signature:"+sVerifyMsgSig+"\n  sVerifyTimeStamp:"+sVerifyTimeStamp+
				"\n sVerifyNonce:"+sVerifyNonce +" \n    "+sVerifyNonce+"\n echostr:"+sVerifyEchoStr);
		String encryptType = req.getParameter("encrypt_type"); 
		System.out.println("encryptType:"+encryptType);
		//验证url的正确性
		if(sVerifyEchoStr !=null) {
			String verifyURLResult = this.verifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr, corpId);
			System.out.println("是否验证成功:"+verifyURLResult);
			PrintWriter out = response.getWriter();
			out.write(verifyURLResult);
			out.flush();
		}else {
			WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(sToken, 
	        		sEncodingAESKey, "ww3baf8d8d4c6ef17d");
			String postData = IOUtils.toString(req.getInputStream(), "utf-8");
			System.out.println("postData"+postData);
			String ss = wxBizMsgCrypt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, postData);
			System.out.println("ss"+ss);
			if(ss!=null) {
				Object[] o = XMLParse.extract1(ss);
				System.out.println("SuiteId"+o[1] +"  SuiteTicket: "+o[2]);
			}
			
		}
		
		
		
//		response.getWriter().write(verifyURLResult);
//		res.getWriter().write(verifyURLResult);
//		BufferedReader br= req.getReader();
//		String str, wholeStr = "";
//		while((str = br.readLine()) != null){
//		wholeStr += str;
//		}
//		System.out.println("请求包体"+wholeStr);
         
//		 InputStream inputStream = req.getInputStream();  
//	        
//	        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
//	        String line;
//	        StringBuffer buf=new StringBuffer();
//	        while((line=reader.readLine())!=null){
//	        	buf.append(line);
//	        }
//	        reader.close();
//	        inputStream.close();
//		System.out.println(buf.toString());
//		String postData = IOUtils.toString(req.getInputStream(), "utf-8");
//		WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(sToken, 
//        		sEncodingAESKey, corpId);
//		System.out.println("解密消息体:"+wxBizMsgCrypt.DecryptMsg(msgSignature, timeStamp, nonce, postData));
//		String ss = wxBizMsgCrypt.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, postData);
//		System.out.println("ss:"+ss);
//		System.out.println("postDate"+postData);
//		String xmltext = wx.DecryptMsg(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyMsgSig);
//		Object[] o = XMLParse.extract(xmltext);
		
		PrintWriter out = response.getWriter();
		out.write("success");
		out.flush();
//		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
//	 public static Map<String, String> parseXmlCrypt(HttpServletRequest request) throws Exception {  
//	        // 将解析结果存储在HashMap中  
//	        Map<String, String> map = new HashMap<String, String>();  
//	  
//	        // 从request中取得输入流  
//	        InputStream inputStream = request.getInputStream();  
//	        
//	        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
//	        String line;
//	        StringBuffer buf=new StringBuffer();
//	        while((line=reader.readLine())!=null){
//	        	buf.append(line);
//	        }
//	        reader.close();
//	        inputStream.close();
//	        
//	        WXBizMsgCrypt wxCeypt=MessageUtil.getWxCrypt();
//	        // 微信加密签名  
//	        String msgSignature = request.getParameter("msg_signature");  
//	        // 时间戳  
//	        String timestamp = request.getParameter("timestamp");  
//	        // 随机数  
//	        String nonce = request.getParameter("nonce");  
//	        
//	        String respXml=wxCeypt.DecryptMsg(msgSignature, timestamp, nonce, buf.toString());
//	        
//	        //SAXReader reader = new SAXReader();  
//	        Document document =DocumentHelper.parseText(respXml);
//	        // 得到xml根元素  
//	        Element root = document.getRootElement();  
//	        // 得到根元素的所有子节点  
//	        List<Element> elementList = root.elements();  
//	  
//	        // 遍历所有子节点  
//	        for (Element e : elementList)  
//	            map.put(e.getName(), e.getText());  
//	  
//	        // 释放资源  
//	        //inputStream.close();  
//	        //inputStream = null;  
//	  
//	        return map;  
//	    }  
	public static void main(String[] args) throws AesException {
//		Object[] o = XMLParse.extract("<xml><SuiteId><![CDATA[ww3baf8d8d4c6ef17d]]></SuiteId><InfoType><![CDATA[suite_ticket]]></InfoType><TimeStamp>1536314586</TimeStamp><SuiteTicket><![CDATA[zvtenJeECnfgezSeBT1KUrJ5v6lI02Fjnp5kXARdOqPBmSk8DEbHNujz0IgRrLcU]]></SuiteTicket></xml>");
//		System.out.println(o[1] +"  SuiteTicket: "+o[2]);
	}
}
