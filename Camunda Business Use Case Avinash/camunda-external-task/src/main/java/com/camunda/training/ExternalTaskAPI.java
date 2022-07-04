package com.camunda.training;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;

/**
 * @author AvinashRavat
 *
 */
public class ExternalTaskAPI {
	
	
	/**
	 * @author AvinashRavat
	 * @return This Service is integrating  external code with process engine
	 */
	public static void main(String[] args) {
		
		ExternalTaskClient client = ExternalTaskClient.create()
		        .baseUrl("http://localhost:8080/engine-rest")
		        .asyncResponseTimeout(20000)
		        .lockDuration(10000)
		        .maxTasks(1)
		        .build();
		 TopicSubscriptionBuilder subscriptionBuilder = client
			        .subscribe("notification");
		   subscriptionBuilder.handler((externalTask, externalTaskService) -> {
		          String content = externalTask.getVariable("projectName");
		          System.out.println("Project Name: " + content); 
		          Map<String, Object> variables = new HashMap<String, Object>();
		          variables.put("notficationTimestamp", new Date());
		          externalTaskService.complete(externalTask, variables);
		    });
		   subscriptionBuilder.open();   

	}

}
