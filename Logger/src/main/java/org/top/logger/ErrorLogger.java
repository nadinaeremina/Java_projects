package org.top.logger;

import org.top.message.Message;
import org.top.message.MessageType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ErrorLogger extends Logger{
    public ErrorLogger(MessageType type) {
        super(type);
    }

    @Override
    public void write(String message) {
        String error = "error.txt";
        System.out.println(message);
        try {
            File file = new File(error);
            if(!file.exists()){
                Path path = Paths.get(error);
                Files.createFile(path);
            }
            Files.write(Paths.get(error), (message+"\n").getBytes(),
                    StandardOpenOption.APPEND);
        }catch (IOException e) {
            //вызов error
            sender(new Message(e.getMessage(), MessageType.ERROR));
        }
    }
}
