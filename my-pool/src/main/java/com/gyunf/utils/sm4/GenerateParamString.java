package com.gyunf.utils.sm4;

import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

public class GenerateParamString {

	/**
	 * url编码
	 * @param text
	 * @return
	 */
	private static String encode(String text) {
		String result = "";
		try {
			result = URLEncoder.encode(text, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	/**
	 * 生成 params 值
	 * @param zjhm 原始证件号码
	 * @param zjlx 证件类型
	 * @param xm 姓名
	 * @param accessCode 分配的accessCode
	 * @return
	 */
	private static String generateParam(String zjhm,String zjlx,String xm,String accessCode) {
		// 下面代码仅为示例，可根据各自项目使用JSONObject类
		JSONObject json = new JSONObject();
		json.put("zjhm", SM4Utils.encryptData_ECB(zjhm, accessCode));
		json.put("zjlx", zjlx);
		json.put("xm", xm);
		return encode(json.toString());
	}
	
	
	/**
	 * 生成 params 值
	 * @param zjhm 原始证件号码
	 * @param accessCode 分配的accessCode
	 * @return
	 */
	private static String generateParam(String zjhm,String accessCode) {
		// 下面代码仅为示例，可根据各自项目使用JSONObject类
		JSONObject json = new JSONObject();
		json.put("zjhm", SM4Utils.encryptData_ECB(zjhm, accessCode));
		return encode(json.toString());
	}
}
