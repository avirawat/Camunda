package com.camunda.training;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineExtension.class)
public class ProcessJUnitTest {
  
  @Test
  @Deployment(resources="Twitter-QA.bpmn")
  public void testHappyPath() {
    
	   // Create a HashMap to put in variables for the process instance
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("approval", true);
	    variables.put("content","Exercise 4 test - Avinash");
	    // Start process with Java API and variables
	    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("Twitter", variables);
	    // Make assertions on the process instance
	    assertThat(processInstance).isEnded();
  }

}
