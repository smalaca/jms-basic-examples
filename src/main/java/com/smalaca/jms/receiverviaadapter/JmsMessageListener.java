package com.smalaca.jms.receiverviaadapter;

import org.springframework.stereotype.Service;

@Service
public class JmsMessageListener {
    public String handleMessage(String text) {
        System.out.println("Received: " + text);
        return anAcknowledgmentMessage();
    }

    private String anAcknowledgmentMessage() {
        return "ACK from handleMessage";
    }
}
