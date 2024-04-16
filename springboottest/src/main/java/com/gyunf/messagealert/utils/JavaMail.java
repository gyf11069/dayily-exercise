package com.gyunf.messagealert.utils;

import com.gyunf.messagealert.MessConfig;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送
 */
public class JavaMail {



	public static Properties setProperties(MessConfig messConfig){
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", messConfig.getSmtp_server());// 发件人的邮箱的SMTP服务器地址
		props.setProperty("mail.stmp.auth", "true");// 需要请求认证
		// 开启SSL安全认证
		props.setProperty("mail.smtp.port", messConfig.getSmtp_port());
		props.setProperty("mail.smtp.socletFactory.class","javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		if ("465".equals(messConfig.getSmtp_port())){
			props.setProperty("mail.smtp.ssl.enable", "true");
		}
		props.put("mail.smtp.timeout", 30000);
		props.put("mail.smtp.connectiontimeout", 30000);
		props.put("mail.smtp.writetimeout", 30000);
		return props;
	}


	/**
	 * 单发
	 * @param receiveMailAccount 接收邮箱账户
	 * @param subject 邮件标题
	 * @param content 邮件内容
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendMail(MessConfig messConfig ,String receiveMailAccount, String subject, String content) throws Exception {
		// 1.创建参数配置，用于连接邮件服务器的配置
		List receiveList = Arrays.asList(receiveMailAccount );
		return sendMail(  messConfig ,  receiveList,   subject,   content);
	}

	/**
	 * 群发
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendMail(MessConfig messConfig ,List<String> receiveMailAccount, String subject, String content)
									throws Exception {
		if (StringUtils.isBlank(messConfig.getSmtp_port()) || StringUtils.isBlank(messConfig.getSendmailpass())
				|| StringUtils.isBlank(messConfig.getSendmail()) ||  StringUtils.isBlank(messConfig.getSmtp_server()) ){
			throw new Exception("邮箱配置不正确");
		}
		if (receiveMailAccount == null || receiveMailAccount.isEmpty()) {
			System.out.println("邮箱地址为空,邮箱未发送!!!");
			return false;
		}
		// 1.创建参数配置，用于连接邮件服务器的配置
		Properties props = setProperties(messConfig);
		// props.setProperty("mail.smtp.scoletFactory.port",smtpPort);
		// 2.创建配置绘画对象，用于和邮件服务器交互
		Session session = Session.getInstance(props);
		session.setDebug(false);// 设置debug模式查看日志
		// 3.创建一封邮件
		MimeMessage message = creatMineMessage(messConfig.getSendmail(),session,messConfig.getSendmail(), receiveMailAccount, subject, content);
		// 4.根据session获取邮件传输对象
		Transport transport = session.getTransport();
		// 5.使用账号和密码连接邮箱
		transport.connect(messConfig.getSendmail(), messConfig.getSendmailpass());
		// 6.发送邮件，发送至所有地址
		transport.sendMessage(message, message.getAllRecipients());
		// 7.关闭连接
		transport.close();
		return true;
	}
	
	/**
	 * 创建一封只包含文本的简单邮件（群发）
	 * 
	 * @param session 和服务器交互的会话
	 * @param sendMail 发送方
	 * @return
	 * @throws UnsupportedEncodingException 编码异常
	 * @throws MessagingException 信息异常
	 */
	public static MimeMessage creatMineMessage(String myEmailAccountName ,Session session, String sendMail,
											   List<String> receiveMailList, String subject, String content)
											   throws UnsupportedEncodingException, MessagingException {
		// 1.创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2.FROM:发件人
		message.setFrom(new InternetAddress(sendMail, myEmailAccountName,
				"UTF-8"));
		// 3.邮件标题
		message.setSubject(subject, "UTF-8");
		// 4.TO:收件人（可添加多个收件人、抄送人、密送）
		InternetAddress[] internetAddress = new InternetAddress[receiveMailList
				.size()];
//		for (int i = 0; i < receiveMailList.size(); i++) {
//
//			//解密
//			String encrypt_mail = receiveMailList.get(i);
//			String decrypt_mail = "";
//			try {
//				decrypt_mail = SM4Util.decryptData_ECB(encrypt_mail);
//				if(decrypt_mail==null || "null".equals(decrypt_mail) || StringUtils.isBlank(decrypt_mail)){
//					decrypt_mail = encrypt_mail;
//				}
//			} catch (Exception e) {
//				System.out.println(encrypt_mail+"密码解密失败："+e.getMessage());
//				decrypt_mail = encrypt_mail;
//			}
//
//			internetAddress[i] = new InternetAddress(decrypt_mail);
//		}
		message.setRecipients(MimeMessage.RecipientType.TO, internetAddress);
		// 5.设置发件时间
		message.setSentDate(new Date());
		// 6.邮件正文
		message.setContent(content, "text/html;charset=UTF-8");
		// 7.保存设置
		message.saveChanges();
		return message;
	}

	public static boolean sendMail(MessConfig messConfig ,List<String> receiveMailAccount, String subject, String content,List<File> files,String fileName)
			throws Exception {
		if (StringUtils.isBlank(messConfig.getSmtp_port()) || StringUtils.isBlank(messConfig.getSendmailpass())
				|| StringUtils.isBlank(messConfig.getSendmail()) ||  StringUtils.isBlank(messConfig.getSmtp_server()) ){
			throw new Exception("邮箱配置不正确");
		}
		// 1.创建参数配置，用于连接邮件服务器的配置
		Properties props = setProperties(messConfig);
		// props.setProperty("mail.smtp.scoletFactory.port",smtpPort);
		// 2.创建配置绘画对象，用于和邮件服务器交互
		Session session = Session.getInstance(props);
		session.setDebug(false);// 设置debug模式查看日志
		// 3.创建一封邮件
		MimeMessage message = NewCreatMineMessage(messConfig.getSendmail(),session,messConfig.getSendmail(), receiveMailAccount, subject, content,files,fileName);
		// 4.根据session获取邮件传输对象
		Transport transport = session.getTransport();
		// 5.使用账号和密码连接邮箱
		transport.connect(messConfig.getSendmail(), messConfig.getSendmailpass());
		// 6.发送邮件，发送至所有地址
		transport.sendMessage(message, message.getAllRecipients());
		// 7.关闭连接
		transport.close();
		return true;
	}

	//邮件附件
	public static MimeMessage NewCreatMineMessage(String myEmailAccountName ,Session session, String sendMail,
											   List<String> receiveMailList, String subject, String content,List<File> files,String fileName)
			throws UnsupportedEncodingException, MessagingException {
		// 1.创建一封邮件
		MimeMessage message = new MimeMessage(session);
		// 2.FROM:发件人
		message.setFrom(new InternetAddress(sendMail, myEmailAccountName,
				"UTF-8"));
		// 3.邮件标题
		message.setSubject(subject, "UTF-8");
		// 4.TO:收件人（可添加多个收件人、抄送人、密送）
		InternetAddress[] internetAddress = new InternetAddress[receiveMailList
				.size()];
//		for (int i = 0; i < receiveMailList.size(); i++) {
//
//			//解密
//			String encrypt_mail = receiveMailList.get(i);
//			String decrypt_mail = "";
//			try {
//				decrypt_mail = SM4Util.decryptData_ECB(encrypt_mail);
//				if(decrypt_mail==null || "null".equals(decrypt_mail)){
//					decrypt_mail = encrypt_mail;
//				}
//			} catch (Exception e) {
//				System.out.println(encrypt_mail+"密码解密失败："+e.getMessage());
//				decrypt_mail = encrypt_mail;
//			}
//
//			internetAddress[i] = new InternetAddress(decrypt_mail);
//		}
		message.setSentDate(new Date());
		message.setRecipients(MimeMessage.RecipientType.TO, internetAddress);
		MimeMultipart msgMultipart = new MimeMultipart("mixed");
		message.setContent(msgMultipart);
		MimeBodyPart contents = new MimeBodyPart();
		msgMultipart.addBodyPart(contents);
		MimeMultipart bodyMultipart = new MimeMultipart("related");
		contents.setContent(bodyMultipart);
		MimeBodyPart htmlPart = new MimeBodyPart();
		bodyMultipart.addBodyPart(htmlPart);
		htmlPart.setContent(content, "text/html;charset=UTF-8");
		if ( files != null && !files.isEmpty()){
			for (File file : files) {
				MimeBodyPart filPart = new MimeBodyPart();
				FileDataSource dataSource=new FileDataSource(file);
				DataHandler dataHandler = new DataHandler(dataSource);
				filPart.setDataHandler(dataHandler);
				filPart.setFileName(fileName);
				if (StringUtils.isBlank(fileName)) {
					filPart.setFileName(file.getName());
				}
				msgMultipart.addBodyPart(filPart);
			}
		}
//		// 5.设置发件时间
//		message.setSentDate(new Date());
//		// 6.邮件正文
//		message.setContent(content, "text/html;charset=UTF-8");
//		// 7.保存设置
		message.saveChanges();
		return message;
	}
}
