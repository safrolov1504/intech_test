package ru.interview.intech.publisher.threads;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import ru.interview.intech.publisher.ApplicationProperty;
import ru.interview.intech.publisher.send.Starter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
@ConditionalOnBean(Starter.class)
public class ThreadsPool {
    private Executor executor;

    @Autowired
    public ThreadsPool(ApplicationProperty properties) {
        this.executor =  Executors.newFixedThreadPool(properties.getMaxThreads());
    }

    public void execute(Runnable runnable){
        executor.execute(runnable);
    }

    public boolean isThreadAvailable(){
        ThreadPoolExecutor tpExecutor = (ThreadPoolExecutor) executor;
        return tpExecutor.getMaximumPoolSize() != tpExecutor.getActiveCount();
    }
}
