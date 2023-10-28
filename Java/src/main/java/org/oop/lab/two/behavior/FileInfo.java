package org.oop.lab.two.behavior;

import java.io.File;
import java.io.Serializable;

public class FileInfo implements Serializable {
    private final String fileName;
    private long lastModified;

    public FileInfo(File file) {
        fileName = file.getName();
        lastModified = file.lastModified();
    }

    public String getFileName() {
        return fileName;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

}

