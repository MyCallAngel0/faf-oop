package org.oop.lab.two.behavior;

import java.io.File;

public class FileManager {
    private final String directoryPath = "Java/src/main/java/org/oop/lab/two/local";
    private final File directory = new File(directoryPath);

    public File[] files;

    public FileManager() {
        this.files = directory.listFiles();
    }

    public void getAllFiles() {

    }


}
