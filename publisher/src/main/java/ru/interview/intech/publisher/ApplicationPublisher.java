package ru.interview.intech.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
@Import(
		{
				ApplicationProperty.class,
		}
)
public class ApplicationPublisher {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationPublisher.class, args);
	}

}
