package workflow.demo.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Named;

@Slf4j
@RequestScoped
@Named("MyServiceDelegate")
// @ConfigurationProperties(prefix = "custom")
public class MyServiceDelegate implements JavaDelegate {

    private String hello;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.warn("MyServiceDelegate: {}", hello);
    }
}
