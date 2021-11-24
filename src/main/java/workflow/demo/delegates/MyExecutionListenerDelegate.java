package workflow.demo.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Named;

@Slf4j
@RequestScoped
@Named("MyExecutionListener")
public class MyExecutionListenerDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        if ("start".equals(execution.getEventName())) {
            log.info("Start event");
        } else if ("end".equals(execution.getEventName())) {
            log.info("End event");
        }
    }
}
