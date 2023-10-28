package org.oop.lab.two.behavior;

import java.io.File;
import java.util.Map;


public class FileChangeDetection extends Thread {
    protected static Map<String, FileInfo> fileSnapshot = Commands.fileSnapshot;

    public FileChangeDetection() {

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
        FileManager fileManager = new FileManager();
        boolean changes = false;
        boolean printed = false;
        for (File file : fileManager.getFiles()) {
            FileInfo fileInfo = fileSnapshot.get(file.getName());
            if (fileInfo != null) {
                if (fileInfo.getLastModified() < file.lastModified()) {
                    if (!printed) {
                        System.out.println();
                    }
                    System.out.println(file.getName() + " - Changed");
                    fileInfo.setLastModified(file.lastModified());
                    changes = true;
                }
            }
        }
        if (changes) {
            System.out.print("> ");
        }
    }
}
