package workflow.demo.junit5;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
class MyServiceTest {

    private final static String PROCESS_DEFINITION_KEY = "MyServiceProcess";

    @Inject
    private RuntimeService runtimeService;

    @Test
    void shouldExecuteProcess() {
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
        assertThat(processInstance)
            .isStarted()
            .hasPassed("StartStartEvent", "MyServiceTask", "EndEndEvent")
            .isEnded();
    }
}
