package com.camunda.training.service2;


//import org.slf4j.Logger; 
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camunda.training.dao.ProjectDetailDAOInterface;
import com.camunda.training.model.ProjectDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailServiceInterface {
	private static final Log logger = LogFactory.getLog(ProjectDetailServiceImpl.class);
	
	@Autowired
	ProjectDetailDAOInterface projectDetailDAO;
	
	public void saveProjectData(ProjectDetail projectDetail) {
	logger.debug("Inside Service Impl class inserting value in table");
	//saving data by using Data JPA 
	 projectDetailDAO.save(projectDetail);
	
	}
}
