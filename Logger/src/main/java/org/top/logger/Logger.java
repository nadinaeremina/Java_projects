package org.top.logger;

import org.top.message.Message;
import org.top.message.MessageType;

public abstract class Logger {
    private MessageType type;
    private Logger next;

    public Logger(MessageType type) {
        this.type = type;
    }

    public void setNext(Logger next) {
        this.next = next;
    }

    public void sender(Message message){
        // если совпадает с типом логгера, то пишем, если нет, то передаем дальше
        // 'ordinal' - возвращает порядковый номер константы 'type'
        if(message.type.ordinal() == type.ordinal()){
            write(message.toString());
        }
        if(next != null){
            next.sender(message);
        }
    }

    public abstract void write(String message);
}
