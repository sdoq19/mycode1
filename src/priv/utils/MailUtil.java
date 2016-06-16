package priv.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * 用来发送邮件的辅助类。<br/>
 * 主要依赖jar包：activation.jar，mail.jar。
 * @version 1.0
 * @author 
 */
public class MailUtil {
	private String hostSmtp ; // 邮箱smtp
	private String hostAddress ; // 发件箱地址
	private String hostPwd ; // 发件箱密码
	
	/**
	 * 构造函数
	 * @param hostSmtp 发送邮件的邮箱smtp地址，例如：smtp.126.com
	 * @param hostAddress 发送邮件的邮箱地址
	 * @param hostPwd 发送邮件的邮箱密码
	 */
	public MailUtil(String hostSmtp,String hostAddress,String hostPwd){
		this.hostAddress=hostAddress;
		this.hostSmtp=hostSmtp;
		this.hostPwd=hostPwd;
	}

	/**
	 * 向指定邮箱发送邮件
	 * @param title email标题
	 * @param content Email内容
	 * @param toAddress 接收邮箱地址 如：***@qq.com
	 * @throws Exception
	 */
	public void sendMail(String title, String content, String toAddress) throws Exception {
		String mail = content;
		// properties里面包含发送邮件服务器的地址
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", hostSmtp);
		mailProps.put("mail.smtp.auth", "true");
		SMTPAuthenticator smtpAuthenticator = new SMTPAuthenticator(
				hostAddress, hostPwd);
		Session mailSession = Session.getDefaultInstance(mailProps,
				smtpAuthenticator);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(hostAddress));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				toAddress, false));
		message.setSubject(title);
		// System.out.println("准备发送邮件！！！");
		message.setText(mail);
		Transport.send(message);
	}
}

/**
 * 用于Jmail返回邮箱账号和密码的校验(在这里被Jmail类所用)
 * 
 * @author 
 * 
 */
class SMTPAuthenticator extends Authenticator {
	private String name = "";
	private String password = "";

	public SMTPAuthenticator(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(name, password);
	}

}
