package processTest;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessUnitTest {
	@Rule
	public ProcessEngineRule engine = new ProcessEngineRule();

	@Test
	@Deployment(resources = { "httpConnectorPOST.bpmn" })
	public void happyPathPOSTTest() {
		ProcessInstance pi = runtimeService().startProcessInstanceByKey("HttpConnectorTestPOSTProcess",
				withVariables("name", "Avinash"));
		assertThat(pi).isEnded().hasPassed("UserCreatedEndEvent").hasVariables("response", "name");
	}

}
