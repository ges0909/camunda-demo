package workflow.demo.junit4;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SayHelloTest extends AbstractProcessEngineRuleTest {

    @Autowired
    public RuntimeService runtimeService;

    @Test
    public void shouldExecuteHappyPath() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("SayHelloProcess");
        assertThat(processInstance).isStarted()
            .task()
            .hasDefinitionKey("SayHelloTask")
            .hasCandidateUser("admin")
            .isNotAssigned();
    }
}
