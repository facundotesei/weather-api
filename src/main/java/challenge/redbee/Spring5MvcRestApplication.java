package challenge.redbee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Spring5MvcRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(Spring5MvcRestApplication.class, args);
    }
}


