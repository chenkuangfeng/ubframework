package com.ubsoft.framework.core.util;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailUtil {

	public static boolean sendMail(String host, String from, String pwd, String to, String copyto, String subject, String content) throws Exception {
		return sendMail(host, from, pwd, to, copyto, subject, content, null);
	}

	public static boolean sendMail(String host, String from, String pwd, String to, String copyto, String subject, String content, String[] filenames) throws Exception{
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.sender.username", from);
		properties.put("mail.sender.password", pwd);
		Session session = null;
		// 邮件会话对象 
		MimeMessage mimeMsg = null;
		session = Session.getInstance(properties);
		mimeMsg = new MimeMessage(session);
		// MIME邮件对象
		Multipart mp = new MimeMultipart();
		// Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
		
			// 设置发信人
			mimeMsg.setFrom(new InternetAddress(from));
			// 设置接收人
			if (to != null) {
				String[] tos = to.split(";");
				InternetAddress[] address = new InternetAddress[tos.length];
				for (int i = 0; i < tos.length; i++) {
					address[i] = new InternetAddress(tos[i]);
					
				}

				// for (int i = 0; i < to.length; i++) {
				mimeMsg.setRecipients(Message.RecipientType.TO, address);
				// }
				// mimeMsg.setr
			}
			// 设置抄送人
			if (copyto != null) {
				// for (int i = 0; i < copyto.length; i++) {
				mimeMsg.setRecipients(Message.RecipientType.CC, copyto);
				// }
			}
			// 设置主题
			mimeMsg.setSubject(subject);
			// 设置正文
			BodyPart bp = new MimeBodyPart();
			bp.setContent(content, "text/html;charset=utf-8");
			mp.addBodyPart(bp);
			// 设置附件
			if (!StringUtil.isEmpty(filenames)) {
				for (String file : filenames) {
					BodyPart bptemp = new MimeBodyPart();
					FileDataSource fileds = new FileDataSource(file);
					bptemp.setDataHandler(new DataHandler(fileds));
					bptemp.setFileName(MimeUtility.encodeText(fileds.getName(), "UTF-8", "B"));
					mp.addBodyPart(bptemp);
				}
			}
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			// 发送邮件
			if (properties.get("mail.smtp.auth").equals("true")) {// props.get("mail.smtp.auth").equals("true")
				Transport transport = session.getTransport("smtp");
				transport.connect(host, from, pwd);
				transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
				transport.close();
			} else {
				Transport.send(mimeMsg);
			}

		
		return true;
	}
	
	public static void main(String [] arg){
		try {
			MailUtil.sendMail("192.168.17.12", "fkadmin@lachapelle.cn", "abc@123", "9600543@qq.com", null, "测试", "速度分散对方");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
