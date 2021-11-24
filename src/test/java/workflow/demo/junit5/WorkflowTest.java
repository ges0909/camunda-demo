package workflow.demo.junit5;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest
class WorkflowTest {

    @Inject
    private RuntimeService runtimeService;

    @Test
    void shouldExecuteHappyPath() {
        // given
        final String processDefinitionKey = "SayHelloProcess";

        // when
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

        // then
        assertThat(processInstance)
            .isStarted()
            .task()
            .hasDefinitionKey("SayHelloTask")
            .hasCandidateUser("admin")
            .isNotAssigned();
    }

    @Test
    void shouldExecute() {
        // given
        final String processDefinitionKey = "SayHelloProcess";

        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

        assertThat(processInstance)
            .isActive(); // should be active

        // Assertions.assertThat(processInstanceQuery().count()).isEqualTo(1);  // should be the only instance
        assertThat(task(processInstance))
            .isNotNull(); // should exist just a single task within that process instance

        complete(task(processInstance)); // when we complete that task ...
        assertThat(processInstance)
            .isEnded(); // ... then the process instance should be ended
    }
}
