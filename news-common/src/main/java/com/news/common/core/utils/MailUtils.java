package com.news.common.core.utils;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.news.common.project.dto.NewsUserDto;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {
	private static Logger logger = Logger.getLogger(MailUtils.class);
	// --------------参数---------------------
	public static final String FROM = "1109927601@qq.com";// 发件人的email
	public static final String PWD = "pjbywtespcbfhhgi";// 发件人密码--邮箱密码
	public static final String URL = "http://localhost:9080/frontend/retrievepwd/step3.html";// 项目主页
	public static final int TIMELIMIT = 1000 * 60 * 60 * 1; // 激活邮件过期时间24小时
	public static final String TITLE = "找回密码";

	// ---------------自定义函数-----------------
	public static void sendMail(NewsUserDto u) throws AddressException, MessagingException, NoSuchAlgorithmException {
		// 注册邮箱
		String to = u.getEmail();
		// 发送的邮箱内容
		String content = "<p>您好 O(∩_∩)O~~<br><br>请勿回复本邮件.点击下面的链接,重设密码,本邮件超过30分钟,链接将会失效，需要重新申请找回密码."
				+ "<br><a href='" + URL + "?id="+u.getId()+"'><br>点击找回密码</a></p>";
		// 调用发送邮箱服务
		sendMailEntity(to, TITLE, content);
	}

	public static void sendMailEntity(String to, String title, String content)
			throws AddressException, MessagingException {

		Properties props = new Properties(); // 可以加载一个配置文件
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");// 指定邮件服务器，默认端口 25
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");// 要采用指定用户名密码的方式去认证
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		// 根据属性新建一个邮件会话
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}
		});
		session.setDebug(true); // 有他会打印一些调试信息。
		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象
		message.setFrom(new InternetAddress(FROM));// 设置发件人的地址
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));// 设置收件人,并设置其接收类型为TO
		message.setSubject(title, "UTF-8");// 设置标题
		// 设置信件内容
		message.setContent(content, "text/html;charset=utf-8"); // 发送HTML邮件，内容样式比较丰富
		message.setSentDate(new Date());// 设置发信时间
		// 发送邮件
		Transport.send(message);
		logger.debug("Transport.send");
	}
}
