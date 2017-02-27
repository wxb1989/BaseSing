package com.cx.sin.utils.base;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 邮件
 * 
 * @author xuexianbin
 *
 * @date 2014年12月13日 上午9:38:06
 */
@Component
public class MailUtil {

	private static final Logger LOGGER = Logger.getLogger(MailUtil.class);

	/**
	 * 此段代码用来发送普通电子邮件
	 */
	public static void send(String host, String userName, String password,
			String subject, String body, String mailFrom, String mailTo)
			throws Exception {
		try {
			Properties props = new Properties();
			Authenticator auth = new EmailAutherticator(userName, password);
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, auth);

			// 设置session,和邮件服务器进行通讯。
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject); // 设置邮件主题
			message.setText(body); // 设置邮件正文
			message.setSentDate(new Date()); // 设置邮件发送日期
			Address address = new InternetAddress(mailFrom);
			message.setFrom(address); // 设置邮件发送者的地址
			Address toAddress = new InternetAddress(mailTo); // 设置邮件接收方的地址
			message.addRecipient(Message.RecipientType.TO, toAddress);
			Transport.send(message); // 发送邮件
			LOGGER.info("邮件发送成功!");
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	/**
	 * 进行邮件服务器用户认证
	 * 
	 * @author nick
	 * 
	 */
	public static class EmailAutherticator extends Authenticator {

		String userName;

		String password;

		public EmailAutherticator() {
			super();
		}

		public EmailAutherticator(String user, String pwd) {
			super();
			userName = user;
			password = pwd;

		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}
}
