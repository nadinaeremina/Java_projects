package org.top.message;

public class Message {
    public String message;
    public MessageType type;

    public Message(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ": " + message;
    }
}
