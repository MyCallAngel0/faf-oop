package org.oop.lab.two.behavior;

import org.oop.lab.two.filetype.FileType;
import org.oop.lab.two.filetype.ImageFile;
import org.oop.lab.two.filetype.ProgramFile;
import org.oop.lab.two.filetype.TextFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Commands {
    protected static Map<String, FileInfo> fileSnapshot = new HashMap<>();
    protected static void commit() {
        System.out.println("Created Snapshot at: " + LocalDateTime.now());
        status();
        fileSnapshot.clear();
        FileManager fileManager = new FileManager();
        Arrays.stream(fileManager.getFiles()).forEach(file -> fileSnapshot.put(file.getName(), new FileInfo(file)));
        storeSnapshot();
    }

    protected static void info(String input) {
        FileManager fileManager = new FileManager();
        String fileInput = input.substring(input.indexOf(" ") + 1).trim();
        switch (fileInput) {
            case "all files":
                fileManager.fileArray.forEach(FileType::getInfo);
                break;
            case "image files":
                fileManager.fileArray.forEach(fileType -> {
                    if(fileType instanceof ImageFile) {
                        fileType.getInfo();
                    }
                });
                break;
            case "text files":
                fileManager.fileArray.forEach(fileType -> {
                    if(fileType instanceof TextFile) {
                        fileType.getInfo();
                    }
                });
                break;
            case "program files":
                new FileManager().fileArray.forEach(fileType -> {
                    if(fileType instanceof ProgramFile) {
                        fileType.getInfo();
                    }
                });
                break;
            case "exit":
                break;
            default:
                fileManager.getFileInformation(fileInput);
        }
    }

    protected static void status() {
        FileManager fileManager = new FileManager();
        int size = 0;
        for (File file : fileManager.getFiles()) {
            FileInfo fileInfo = fileSnapshot.get(file.getName());
            if(fileInfo == null) {
                System.out.println(file.getName() + " - Created");
                size++;
            } else if (fileInfo.getLastModified() < file.lastModified()) {
                System.out.println(file.getName() + " - Changed");
                size++;
            } else {
                System.out.println(file.getName() + " - Not Changed");
                size++;
            }
        }
        if (size < fileSnapshot.size()) {
            List<String> fileNameArray = Arrays.stream(fileManager.getFiles()).map(File::getName).toList();
            for (FileInfo fileInfo1 : fileSnapshot.values()) {
                if (!fileNameArray.contains(fileInfo1.getFileName())) {
                    System.out.println(fileInfo1.getFileName() + " - Deleted");
                }
            }
        }
    }

    private static void storeSnapshot() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(".\\src\\main\\java\\org\\oop\\lab\\two\\behavior\\snapshot.txt"))){
            outputStream.writeObject(fileSnapshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected static void takeSnapshot() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(".\\src\\main\\java\\org\\oop\\lab\\two\\behavior\\snapshot.txt"))) {
            fileSnapshot = (HashMap<String, FileInfo>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There are no previous commits");
        }
    }
}
