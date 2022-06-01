package workflow.demo.junit5;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest
class SayHelloTest {

    private final static String PROCESS_DEFINITION_KEY = "SayHelloProcess";
    private final static String TASK_DEFINITION_KEY = "SayHelloTask";
    private final static String CANDIDATE_USER_ID = "admin";
    private final static String CANDIDATE_GROUP_ID = "admin";

    @Inject
    private RuntimeService runtimeService;

    @Test
    void shouldExecuteHappyPath() {
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
        assertThat(processInstance)
            .isStarted()
            .task()
            .hasDefinitionKey(TASK_DEFINITION_KEY)
            .hasCandidateUser(CANDIDATE_USER_ID)
            .hasCandidateGroup(CANDIDATE_GROUP_ID)
            .isNotAssigned();
    }

    @Test
    void shouldExecute() {
        final ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
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
