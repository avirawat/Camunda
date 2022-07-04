package com.camunda.training.delegates;

//import org.slf4j.Logger; 
//import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SendEmail {
	private static final Log logger = LogFactory.getLog(SendEmail.class);

	
	/**
	 * @author AvinashRavat
	 * @param  toAddress
	 * @param  status
	 * @param  rejectReason
	 * @return This Service will assign CC,BCC,Subject sender Mail and receiver mail
	 */
	public void sendEmail(String toAddress,String status,String rejectReason) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("brad@acheron-tech.com");
        msg.setTo(toAddress);

       
        msg.setSubject("Reg:Status of mail");
        
        if(status.equals("Approved")) {

            msg.setText("This is to inform you that email sent : "+ status);
        }else{

            msg.setText("This is to inform you that email rejected "+ status +"with reason"+ rejectReason);
        }

        	logger.info("calling Email Sender ");
        EmailSender emailSender=new EmailSender();
        JavaMailSender javamailsender=emailSender.getJavaMailSender();
        	logger.info("Java Mail Sender Object "+javamailsender);
        javamailsender.send(msg);
        	logger.info("Java Mail Sender done ");

    }

//	public void sendEmail(String email, String status, String reason) {
//		// TODO Auto-generated method stub
//		
//	}

}
