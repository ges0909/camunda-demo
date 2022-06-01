package workflow.demo.junit5;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import workflow.demo.MyServiceDelegate;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
@ExtendWith(ProcessEngineExtension.class) // requires 'camunda.cfg.xml'
class MockedMyServiceTest {

    private ProcessEngine processEngine;

    @Test
    @Deployment(resources = {"my-service.bpmn"})
    void shouldExecuteProcess() {
        Mocks.register("MyServiceDelegate", new MyServiceDelegate());
        final ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("MyServiceProcess");
        assertThat(processInstance)
            .isStarted()
            .hasPassed("RunTestStartEvent", "MyServiceTaskTask", "FinishTestEndEvent")
            .isEnded();
    }
}
