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

    /* TODO
    HashMap fileInfo
    if !getvalues - created
    if last modified == - not changed else changed
    last modified should be datetime
    if key not in directory - deleted
    thread should have the same but updated each 5s
    may use the filetype for some variables
     */
}

