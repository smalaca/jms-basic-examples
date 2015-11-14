package com.smalaca.jms.client;

import com.smalaca.jms.sender.JmsMessageSender;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Queue;

public class SenderClient {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-sender-context.xml");
        JmsMessageSender jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");

        jmsMessageSender.send("hello JMS once again");
        jmsMessageSender.sendAndReplyTo("replyTo", "hello JMS once again");

        Queue queue = new ActiveMQQueue("AnotherDest");
        jmsMessageSender.send(queue, "hello Another Message once again");

        ((ClassPathXmlApplicationContext)ctx).close();
    }
}
