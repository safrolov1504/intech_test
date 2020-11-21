package ru.interview.intech.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ApplicationSubscriber {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSubscriber.class, args);
    }
}

