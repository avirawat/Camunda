package com.camunda.training.delegates;

import java.util.Properties; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailSender {
	private static final Log logger = LogFactory.getLog(EmailSender.class);
	
	/**
	 * @author AvinashRavat
	 * @return This Service will connect mail server and port
	 */
	public JavaMailSender getJavaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("ad.acheron-tech.com");
		mailSender.setPort(25);
		mailSender.setUsername("susan@acheron-tech.com");
		mailSender.setPassword("Acheron@123");
		        
		Properties props = mailSender.getJavaMailProperties();
		//props.setProperty("mail.smtp.starttls.enable", "true");  //mandatory to start 
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		//props.put("mail.smtp.ssl.protocols", "TLSv1.2");   commented to remove TLS
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		         logger.info("Properties Updated ");
		return mailSender;
	}

}
