package com.smalaca.jms.sender;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class JmsMessageSender {
    private JmsTemplate jmsTemplate;

    public JmsMessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.send(aMessageCreatorWithReply("Recv2Send", text));
    }

    public void sendAndReplyTo(final String replyTo, final String text) {
        jmsTemplate.send(aMessageCreatorWithReply(replyTo, text));
    }

    private MessageCreator aMessageCreatorWithReply(final String replyTo, final String text) {
        return new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                message.setJMSReplyTo(new ActiveMQQueue(replyTo));
                return message;
            }
        };
    }

    public void sendText(final String text) {
        jmsTemplate.convertAndSend(text);
    }

    public void send(final Destination dest,final String text) {
        jmsTemplate.send(dest, aMessageCreator(text));
    }

    private MessageCreator aMessageCreator(final String text) {
        return new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
            }
        };
    }
}
