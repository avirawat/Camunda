package processTest;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;


public class ProcessUnitTest {
	@Rule
    public ProcessEngineRule engine = new ProcessEngineRule();

    @Test
    @Deployment(resources = {"httpConnector.bpmn"})
    public void happyPathGETTest() {
    	//System.out.println("1st funciton");
        ProcessInstance pi = runtimeService().startProcessInstanceByKey("HttpConnectorTestProcess", withVariables("userId", 1));
       // System.out.println("Process Instance Created ");
        assertThat(pi).isEnded().hasPassed("EmailFoundEndEvent").hasVariables("email", "userId");
    }

    @Test(expected = ProcessEngineException.class)
    @Deployment(resources = {"httpConnector.bpmn"})
    public void failingPathTest() {
    	//System.out.println("2nd funciton");
        runtimeService().startProcessInstanceByKey("HttpConnectorTestProcess", withVariables("userId", 1));
    }


}
