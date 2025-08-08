package org.top.reporter;

import org.top.logger.DebugLogger;
import org.top.logger.ErrorLogger;
import org.top.logger.InfoLogger;
import org.top.message.MessageType;

public class Reporter {
    public DebugLogger debug;

    public Reporter() {
        debug = new DebugLogger(MessageType.DEBUG);
        InfoLogger info = new InfoLogger(MessageType.INFO);
        ErrorLogger error = new ErrorLogger(MessageType.ERROR);
        debug.setNext(info);
        info.setNext(error);
    }
}
