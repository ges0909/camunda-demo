package workflow.demo.junit5;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
class MyServiceTest {

    @Inject
    private RuntimeService runtimeService;

    @Test
    void shouldExecuteProcess() {
        final String processDefinitionKey = "MyServiceProcess"; // Id

        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

        assertThat(processInstance)
            .isStarted()
            .hasPassed("RunTestStartEvent", "MyServiceTaskTask", "FinishTestEndEvent")
            .isEnded();
    }
}
