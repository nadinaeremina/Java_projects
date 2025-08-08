package org.top.logger;

import org.top.message.Message;
import org.top.message.MessageType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class InfoLogger extends Logger {
    public InfoLogger(MessageType type) {
        super(type);
    }

    @Override
    public void write(String message) {
        String info = "info.txt";
        System.out.println(message);
        try {
            File file = new File(info);
            if (!file.exists()) {
                Path path = Paths.get(info);
                Files.createFile(path);
            }
            Files.write(Paths.get(info), (message+"\n").getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            //вызов самого себя
            sender(new Message(e.getMessage(), MessageType.ERROR));
        }
    }
}
