package org.top;

import org.top.logger.DebugLogger;
import org.top.logger.ErrorLogger;
import org.top.logger.InfoLogger;
import org.top.message.Message;
import org.top.message.MessageType;
import org.top.reporter.Reporter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Reporter reporter = new Reporter();

        reporter.debug.sender(new Message("Some error", MessageType.ERROR));
        reporter.debug.sender(new Message("Вывод действия на консоль", MessageType.DEBUG));
        reporter.debug.sender(new Message("Важная информация", MessageType.INFO));
        reporter.debug.sender(new Message("Some error2", MessageType.ERROR));
        reporter.debug.sender(new Message("Вывод действия на консоль2", MessageType.DEBUG));
        reporter.debug.sender(new Message("Важная информация2", MessageType.INFO));
    }
}