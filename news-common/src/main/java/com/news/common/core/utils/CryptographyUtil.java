package com.news.common.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.news.common.core.exception.BaseRuntimeException;

public class CryptographyUtil {
	private static String hashHex(String algorithm, String val) {
		if (val == null) {
			return null;
		} else {
			MessageDigest digest = null;

			try {
				digest = MessageDigest.getInstance(algorithm);
			} catch (NoSuchAlgorithmException arg3) {
				throw new BaseRuntimeException(arg3);
			}

			return Hex.encodeHexString(digest.digest(val.getBytes()));
		}
	}

	private static String hashBase64(String algorithm, String val) {
		if (val == null) {
			return null;
		} else {
			MessageDigest digest = null;

			try {
				digest = MessageDigest.getInstance(algorithm);
			} catch (NoSuchAlgorithmException arg3) {
				throw new BaseRuntimeException(arg3);
			}

			return Base64.encodeBase64String(digest.digest(val.getBytes()));
		}
	}

	public static String hashMd5Hex(String val) {
		return hashHex("MD5", val);
	}

	public static String hashMd5Base64(String val) {
		return hashBase64("MD5", val);
	}

	public static String hashSha256Hex(String val) {
		return hashHex("SHA-256", val);
	}

	public static String hashSha256Base64(String val) {
		return hashBase64("SHA-256", val);
	}

	public static String hashSha1Base64(String val) {
		return hashBase64("SHA-1", val);
	}

	public static String hashSha1Hex(String val) {
		return hashHex("SHA-1", val);
	}

	private static byte[] aesDecrypt(byte[] content, String password) {
		try {
			KeyGenerator e = KeyGenerator.getInstance("AES");
			e.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = e.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(2, key);
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException arg7) {
			arg7.printStackTrace();
		} catch (NoSuchPaddingException arg8) {
			arg8.printStackTrace();
		} catch (InvalidKeyException arg9) {
			arg9.printStackTrace();
		} catch (IllegalBlockSizeException arg10) {
			arg10.printStackTrace();
		} catch (BadPaddingException arg11) {
			arg11.printStackTrace();
		}

		return null;
	}

	private static String byte2Hex(byte[] buf) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < buf.length; ++i) {
			String hex = Integer.toHexString(buf[i] & 255);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}

			sb.append(hex.toUpperCase());
		}

		return sb.toString();
	}

	private static byte[] hex2Byte(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		} else {
			byte[] result = new byte[hexStr.length() / 2];

			for (int i = 0; i < hexStr.length() / 2; ++i) {
				int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
				int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
				result[i] = (byte) (high * 16 + low);
			}

			return result;
		}
	}

	public static String aesDecrypt(String content, String password) {
		if (content == null) {
			return null;
		} else {
			byte[] byte_result = aesDecrypt(hex2Byte(content), password);
			return new String(byte_result);
		}
	}

	public static String aesEncrypt(String content, String password) {
		try {
			KeyGenerator e = KeyGenerator.getInstance("AES");
			e.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = e.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(1, key);
			byte[] result = cipher.doFinal(byteContent);
			return byte2Hex(result);
		} catch (NoSuchAlgorithmException arg8) {
			arg8.printStackTrace();
		} catch (NoSuchPaddingException arg9) {
			arg9.printStackTrace();
		} catch (InvalidKeyException arg10) {
			arg10.printStackTrace();
		} catch (UnsupportedEncodingException arg11) {
			arg11.printStackTrace();
		} catch (IllegalBlockSizeException arg12) {
			arg12.printStackTrace();
		} catch (BadPaddingException arg13) {
			arg13.printStackTrace();
		}

		return null;
	}
}