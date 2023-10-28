package org.oop.lab.two.behavior;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInfo implements Serializable {
    private String fileName;
    private String extension;
    private long lastModified;

    public FileInfo(File file) {
        fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        extension = dotIndex > 0 ? fileName.substring(dotIndex + 1) : "";
        lastModified = file.lastModified();
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

}

