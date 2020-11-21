package ru.interview.intech.publisher.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.interview.intech.publisher.threads.ThreadsPool;

@Component
public class Starter {
    private ThreadsPool taskThreadPool;
    private ApplicationContext context;

    @Autowired
    public Starter(ThreadsPool taskThreadPool,
                   ApplicationContext context) {
        this.taskThreadPool = taskThreadPool;
        this.context = context;
    }

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void start(){
        while (taskThreadPool.isThreadAvailable()) {
            Sender sender = (Sender) context.getBean("sender");
            taskThreadPool.execute(() -> sender.run());
        }
    }
}
