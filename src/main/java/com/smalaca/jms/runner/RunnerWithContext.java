package com.smalaca.jms.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RunnerWithContext {
    public void run(String contextName, ClientOperation clientOperation) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(contextName);

        clientOperation.work();

        ((ClassPathXmlApplicationContext)ctx).close();
    }
}
