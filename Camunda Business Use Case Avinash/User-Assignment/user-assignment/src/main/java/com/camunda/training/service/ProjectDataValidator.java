package com.camunda.training.service;

import java.time.LocalDate; 
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camunda.training.model.ProjectDetail;
import com.camunda.training.service2.ProjectDetailServiceInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



@Service
public class ProjectDataValidator implements JavaDelegate {
	
	private static final Log logger = LogFactory.getLog(ProjectDataValidator.class);
	
	@Autowired
	ProjectDetailServiceInterface projectDetailService;
	
	/**
	 * @author AvinashRavat
	 * @param  execution
	 * @return This Service is creating and setting process variable values from User Task Form
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Inside Service task ");
		String projectName=(String) execution.getVariable("projectName");
		String episodeTitle=(String) execution.getVariable("episodeTitle");
		String priority=(String) execution.getVariable("priority");
		String companyName=(String) execution.getVariable("companyName");
		String territoryTitle=(String) execution.getVariable("territoryTitle");
		String description=(String) execution.getVariable("description");
		LocalDate projectDueDate=(LocalDate) execution.getVariable("projectDueDate");
		//Setting values in Class Object
		ProjectDetail projectDetail=new ProjectDetail(projectName,episodeTitle, priority, companyName, territoryTitle, description,projectDueDate);
		//calling service Interface for Storing value in SQL
		projectDetailService.saveProjectData(projectDetail);
		//creating process variable and assigning default value for further use
		String assignedUser="default";
		execution.setVariable("assignedUser", assignedUser);
		String languageList="default";
		execution.setVariable("languageList", languageList);
	}
	

}
