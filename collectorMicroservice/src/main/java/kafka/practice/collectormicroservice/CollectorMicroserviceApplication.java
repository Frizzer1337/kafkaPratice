package kafka.practice.collectormicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CollectorMicroserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CollectorMicroserviceApplication.class, args);
  }
}
