package com.smalaca.jms.client;

import com.smalaca.jms.runner.RunnerWithContext;

public class ReceiverViaAdapterClient {
    public static void main(String[] args) {
        new RunnerWithContext().run("app-receiverviaadapter-context.xml",  () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
