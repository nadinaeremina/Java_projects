package org.top.logger;

import org.top.message.MessageType;

public class DebugLogger extends Logger {

    public DebugLogger(MessageType type) {
        super(type);
    }

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}