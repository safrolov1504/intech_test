package ru.interview.intech.publisher;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Component
public class ApplicationProperty {

    @Value("${sender.delay}")
    private String sendDelay;

    @Value("${interview.url}")
    private String url;

    @Value("${sender.maxThread}")
    private int maxThreads;

    @PostConstruct
    public void info(){
        log.info("Параметры запуска");
        log.info("-------------------------------------------");
        log.info("Максимальное количесвто потоко [{}]", maxThreads);
        log.info("Время задержки [{}]", sendDelay);
        log.info("Ссылка на subscriber [{}]", url);
        log.info("-------------------------------------------");
    }
}
