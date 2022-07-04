package com.camunda.training.service;

import org.camunda.bpm.engine.RuntimeService; 
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camunda.training.model.RequestDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class ProjectStartService {
	private static final Log logger = LogFactory.getLog(ProjectStartService.class);

	
	@Autowired
	RuntimeService runtimeService;
	
	/**
	 * @author AvinashRavat
	 * @param  requestDetails
	 * @return This Service is creating and setting Process variable for Request values
	 */
	public void startPorcessByMessage(RequestDetail requestDetail) throws Exception {
		logger.debug("Setting process variable");
		runtimeService.createMessageCorrelation("startProcess")
		        .setVariable("requestID", requestDetail.getRequestID())
				.setVariable("requesterName", requestDetail.getRequesterName())
				.setVariable("requestStatus", requestDetail.getRequestStatus())
				.setVariable("requestDueDate", requestDetail.getRequestDueDate())
				.correlate();

	}

}
