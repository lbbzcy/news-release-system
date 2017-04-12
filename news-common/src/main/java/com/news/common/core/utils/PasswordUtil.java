package com.news.common.core.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordUtil {
	
	/**
	 * 加密随机盐值
	 */
	private static final String ENCYPT_RANDOM_SALT = "K9tpRg5j";

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) {
		if (password == null)
			return null;
		return DigestUtils.shaHex(password);
	}

	/**
	 * 密码匹配
	 * 
	 * @param password
	 * @param passwordMd5
	 * @return
	 */
	public static boolean passwordsMatch(String password,
			String passwordEncrypted) {
		if (password == null || passwordEncrypted == null)
			return false;
		return passwordEncrypted.equals(encryptPassword(password));
	}

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPassword(char[] password) {
		if (password == null)
			return null;
		String pwString = String.valueOf(password);
		return encryptPassword(pwString);
	}

	public static String encryptPlainByMd5WithSalt(String plainPass){
		return encryptMd5ByMd5WithSalt(CryptographyUtil.hashMd5Hex(plainPass));
	}
	/**
	 * 重置管理员密码时，使用系统参数值
	 * @param plainPass
	 * @param sysSalt
	 * @return
	 */
	public static String encryptPlainBySysParam(String plainPass,String sysSalt){
		String firstPass = CryptographyUtil.hashMd5Hex(plainPass);
		return CryptographyUtil.hashMd5Hex(firstPass + sysSalt);
	}
	
	public static String encryptMd5ByMd5WithSalt(String md5Pass){
		return CryptographyUtil.hashMd5Hex(md5Pass + ENCYPT_RANDOM_SALT);
	}
	
	public static String hashMd5Hex(String plainText){
		return CryptographyUtil.hashMd5Hex(plainText);
	}
	
	public static void main(String[] args) {
		//dbb1c112a931eeb16299d9de1f30161d
		System.out.println(CryptographyUtil.hashMd5Hex("root123456"));
		System.out.println(encryptMd5ByMd5WithSalt("dbb1c112a931eeb16299d9de1f30161d"));
		System.out.println(encryptMd5ByMd5WithSalt("root123456"));
		System.out.println(encryptPlainByMd5WithSalt("root123456"));
	}

}
