package com.news.common.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FileToFTP {
	
	private static String FTP_ADDRESS;
	private static Integer FTP_PORT;
	private static String FTP_USERNAME;
	private static String FTP_PASSWORD;
	private static String FTP_BASE_PATH;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false   
	 */
	public static boolean uploadFile(String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			//切换到上传目录
			if (!ftp.changeWorkingDirectory(FTP_BASE_PATH+filePath)) {
				//如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = FTP_BASE_PATH;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//上传文件
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	
}
