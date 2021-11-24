package workflow.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
public class CamundaApplication {

    public static void main(final String... args) {
        SpringApplication.run(CamundaApplication.class, args);
    }

    @PostConstruct
    public void start() {
        log.info("@PostConstruct");
    }

    @PreDestroy
    public void stop() {
        log.info("@PreDestroy");
    }
}
