package com.news.backend.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.common.core.utils.FileToFTP;
import com.news.common.core.utils.FtpUtil;
@Service("uploadToFtpService")
public class UploadToFtpService {
	private static String FTP_ADDRESS;
	private static Integer FTP_PORT;
	private static String FTP_USERNAME;
	private static String FTP_PASSWORD;
	private static String FTP_BASE_PATH;
	private static String IMAGE_BASE_URL;
	static {
		try {
			Properties props = new Properties();
			InputStream inStream = FileToFTP.class.getClassLoader().getResourceAsStream("ftp.properties");  
			props.load(inStream);
			FTP_ADDRESS = props.getProperty("FTP_ADDRESS");
			FTP_PORT = Integer.parseInt(props.getProperty("FTP_PORT"));
			FTP_USERNAME = props.getProperty("FTP_USERNAME");
			FTP_PASSWORD = props.getProperty("FTP_PASSWORD");
			FTP_BASE_PATH = props.getProperty("FTP_BASE_PATH");
			IMAGE_BASE_URL = props.getProperty("IMAGE_BASE_URL");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			//生成一个新的文件名
			//取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			System.out.println("文件原名称:"+oldName);
			//生成新文件名
			//UUID.randomUUID();
			String newName = UUID.randomUUID().toString().replaceAll("-", "");
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			//图片上传
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			System.out.println("ftp地址:"+FTP_ADDRESS+"端口号:"+FTP_PORT+"用户:"+FTP_USERNAME+"密码:"+FTP_PASSWORD+"路径:"+FTP_BASE_PATH);
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
			System.out.println("result:"+result);
			//返回结果
			if(!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
			return resultMap;
			
		} catch (Exception e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

}
