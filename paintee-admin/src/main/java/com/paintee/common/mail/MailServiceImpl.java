/**
@file MailServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | MailServiceImpl.java |    
| Package | com.paintee.common.mail |    
| Project name | paintee-admin |    
| Type name | MailServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 13. 오후 3:37:12 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
@class MailServiceImpl
com.paintee.common.mail \n
   ㄴ MailServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 13. 오후 3:37:12 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - email 서비스
*/
@Service(value="com.paintee.common.mail.MailServiceImpl")
public class MailServiceImpl implements MailService {
	private final static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Value("#{config['mail.signup.sender'] }")
	private String senderEmail;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : email 발송
	 @remark
	 - 오버라이드 함수의 상세 설명 : email 발송
	 @see com.paintee.common.mail.MailService#sendMail(java.lang.String, java.lang.String, java.lang.String)
	*/
	public void sendMail(String to, String subject, String text) throws Exception {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			message.setSubject(subject, "UTF-8");
			String htmlContent = text;
			message.setText(htmlContent, "UTF-8", "html");
			message.setFrom(new InternetAddress(senderEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mailSender.send(message);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
