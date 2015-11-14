package com.smalaca.jms.receiver;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JmsMessageListener implements SessionAwareMessageListener<TextMessage> {
    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        System.out.println("Receive: "+ message.getText());
        sendAReply(message, session);
    }

    private void sendAReply(TextMessage message, Session session) throws JMSException {
        aProducer(message, session).send(aTextMessage("ACK"));
    }

    private ActiveMQTextMessage aTextMessage(String text) throws MessageNotWriteableException {
        ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText(text);
        return textMessage;
    }

    private MessageProducer aProducer(TextMessage message, Session session) throws JMSException {
        return session.createProducer(message.getJMSReplyTo());
    }
}
