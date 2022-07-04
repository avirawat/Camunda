package com.camunda.training;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.decisionService;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.externalTask;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.findId;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.identityService;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.jobQuery;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineCoverageExtension.class)
public class ProcessJUnitTest {

	@Test
	@Deployment(resources = {"Tweet Approval.dmn","TwitterQAProcess.bpmn"})
	public void testHappyPath() {

		Mocks.register("createTweetDelegate", new LoggerDelegate());
		// Start process with Java API and variables
		identityService().setAuthenticatedUserId("demo");
		ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("TwitterQAProcess",
				withVariables("content", "I did it from Junit! Cheers Ingo1","email","jakob.freund@camunda.com"));
		
	
	  
		// Make assertions on the process instance
//		assertThat(processInstance).isWaitingAt(findId("Review tweet")).task().hasCandidateGroup("management");
//		complete(task(), withVariables("approved", true));
		

//		List<Job> jobList = jobQuery().processInstanceId(processInstance.getId()).list();
//		assertThat(jobList).hasSize(1);
//		Job job = jobList.get(0);
//		execute(job);

		//assertThat(processInstance).isEnded();

	}

	@Test
	@Deployment(resources = {"Tweet Approval.dmn","TwitterQAProcess.bpmn"})
	public void testRejectTweet() {

		// start the process
		Map<String, Object> varMap = new HashMap<>();
		varMap.put("approved", false);
		varMap.put("content", "This is my exercise 8 JUnit tweet!! " + System.currentTimeMillis());
		ProcessInstance processInstance = runtimeService().createProcessInstanceByKey("TwitterQAProcess")
				.setVariables(varMap).startAfterActivity(findId("Review tweet")).execute();
		
		assertThat(processInstance)
	     .isWaitingAt(findId("Notify user of rejection"))
	     .externalTask()
	     .hasTopicName("notification");
	  complete(externalTask());
		
		assertThat(processInstance).isEnded().hasPassed(findId("Tweet rejected"));
	}
	
	  @Test
	  @Deployment(resources = {"Tweet Approval.dmn","TwitterQAProcess.bpmn"})
	  public void testTweetFromJakob() {
		  Map<String, Object> variables = withVariables("email", "jakob.freund@camunda.com", "content", "this should be published");
		    DmnDecisionTableResult decisionResult = decisionService().evaluateDecisionTableByKey("tweetApproval", variables);
		    assertThat(decisionResult.getFirstResult()).contains(entry("approved", true));
	  }

}