package workflow.demo.junit4;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
class MyServiceTest extends AbstractProcessEngineRuleTest {

    @Inject
    public RuntimeService runtimeService;

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
