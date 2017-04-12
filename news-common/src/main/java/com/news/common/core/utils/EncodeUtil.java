package com.news.common.core.utils;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public class EncodeUtil {
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static String md5(String msg) throws Exception {
		//利用md5对msg处理,不可逆加密（把不等长变等长）
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[]  input = msg.getBytes();
		byte[]  output = md.digest(input);//将字节信息处理
		//將md5处理的output结果转成字符串
		String result = Base64.encodeBase64String(output);
		return  result;
	}
	public static String encode(String pwd, String slat) {

		try {
			return EncodeUtil.md5(pwd + slat);
		} catch (Exception e) {
		}
		return pwd + slat;
	}
	public static void main(String[] args) {
		String pwdString = "zcy1234";
		String slat = "zcy";
		System.out.println(encode(pwdString, slat));
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}
