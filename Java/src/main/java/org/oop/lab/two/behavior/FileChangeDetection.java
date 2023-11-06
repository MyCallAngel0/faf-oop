package org.oop.lab.two.behavior;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class FileChangeDetection extends Thread {
    protected static Map<String, FileInfo> fileSnapshot;
    protected static List<FileInfo> deletedFiles = new ArrayList<FileInfo>();

    public FileChangeDetection() {
        Commands.takeSnapshot();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkStatus();
        }
    }

    private void checkStatus() {
        fileSnapshot = Commands.fileSnapshot;
        FileManager fileManager = new FileManager();
        boolean changes = false;
        int size = 0;
        for (File file : fileManager.getFiles()) {
            FileInfo fileInfo = fileSnapshot.get(file.getName());
            if (fileInfo != null) {
                size++;
                if (fileInfo.getLastModified() < file.lastModified()) {
                    System.out.print("\n" + file.getName() + " - Changed");
                    fileInfo.setLastModified(file.lastModified());
                    changes = true;
                }
                else if(fileInfo == null) {
                    System.out.println("\n" + file.getName() + " - Created");
                    fileInfo.setLastModified(file.lastModified());
                    changes = true;
                }
            }
        }
        if (size < fileSnapshot.size()) {
            List<String> fileNameArray = Arrays.stream(fileManager.getFiles()).map(File::getName).toList();
            for (FileInfo fileInfo1 : fileSnapshot.values()) {
                if (!fileNameArray.contains(fileInfo1.getFileName()) && !deletedFiles.contains(fileInfo1)) {
                    System.out.print("\n" + fileInfo1.getFileName() + " - Deleted");
                    deletedFiles.add(fileInfo1);
                    changes = true;
                }
            }
        }
        if (changes) {
            System.out.print("\n> ");
        }
    }
}
