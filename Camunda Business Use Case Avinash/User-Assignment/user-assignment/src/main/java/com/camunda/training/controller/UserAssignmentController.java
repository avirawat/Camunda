package com.camunda.training.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.training.model.RequestDetail;
import com.camunda.training.service.ProjectStartService;

@RestController
public class UserAssignmentController {

	
	ProjectStartService projectStartService;
	
	
	public ProjectStartService getProjectStartService() {
		return projectStartService;
	}

	@Autowired
	public void setProjectStartService(ProjectStartService projectStartService) {
		this.projectStartService = projectStartService;
	}


	public  String getName() {
		return name;
	}

	@Value("${spring.datasource.username}")
	public  void setName(String name) {
		this.name = name;
	}



	public  String name;
	
	//checking controller
	@RequestMapping("/check")
	public String test() {
		return "checking........."+name;
	}
	
	
	//starting Process by using restAPI
	//localhost:8080/msgeventstart
	@RequestMapping(value = "/msgeventstart", method = RequestMethod.POST)
	public void persistPerson(@RequestBody RequestDetail obj) throws Exception {
		//calling service class by passing data object value
		projectStartService.startPorcessByMessage(obj);
	}
	
	//localhost:8080/restAPI
	@RequestMapping(value = "/restAPI", method = RequestMethod.GET)
	public String getResponseByRestAPI(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName) throws Exception {
		return "Hello "+firstName+" "+lastName;
	}

	 
	   
}
