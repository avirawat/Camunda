package com.camunda.training.delegates;

import javax.inject.Named; 

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Named
public class NotifyApplicant implements JavaDelegate {
	
	private static final Log logger = LogFactory.getLog(NotifyApplicant.class);


	
	/**
	 * @author AvinashRavat
	 * @param  execution
	 * @return This Service will assign receiver mail , status and reason
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
		String email="susan@acheron-tech.com";
		String status="Approved";
		String reason="notified : related mail";
		
		SendEmail obj=new SendEmail();
		obj.sendEmail(email,status,reason);
		logger.info("Mail sent Successfully");
		
	}

}
