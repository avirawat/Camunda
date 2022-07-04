package com.camunda.training;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class CamundaMainApplication {
//	@Autowired
//	RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(CamundaMainApplication.class, args);

	}
//	
//	 @EventListener
//	  private void processPostDeploy(PostDeployEvent event) {
//	    runtimeService.startProcessInstanceByKey("HttpConnectorTestProcess");
//	  }

}
