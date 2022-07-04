package com.camunda.training;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineCoverageExtension.class)
public class ProcessJUnitTest {

	@Test
	@Deployment(resources = "Twitter-QA.bpmn")
	public void testHappyPath() {

		// Create a HashMap to put in variables for the process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		// variables.put("approval", true);
		variables.put("content", "----- test Avinash");
		
		// Start process with Java API and variables
		ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("Twitter", variables);
		// Make assertions on the process instance
		// assertThat(processInstance).isWaitingAt("Manager checking");
		assertThat(processInstance).isWaitingAt("Manager123");
		
		List<Task> taskList = taskService().createTaskQuery().taskCandidateGroup("management")
				.processInstanceId(processInstance.getId()).list();
		
		assertThat(taskList).isNotNull();
		assertThat(taskList).hasSize(1);
		Task task = taskList.get(0);
		Map<String, Object> approvedMap = new HashMap<String, Object>();
		approvedMap.put("approval", true);
		taskService().complete(task.getId(), approvedMap);
		
	}

}
