package com.camunda.training.service2;


import org.springframework.stereotype.Service;
import com.camunda.training.model.ProjectDetail;

@Service
public interface ProjectDetailServiceInterface {
	 void saveProjectData(ProjectDetail projectDetail);
	
	
}
