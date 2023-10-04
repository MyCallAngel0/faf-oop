package org.oop.secondlab.behaviour;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class LogManager {
    public static void log(String message) {
        try {
            FileWriter loggingFile = new FileWriter(".\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\saves\\log", true);
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            loggingFile.write(String.format("[%s] %s \n", currentTime, message));
            loggingFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}