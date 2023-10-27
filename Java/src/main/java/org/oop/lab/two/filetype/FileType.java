package org.oop.lab.two.filetype;

import java.io.File;

public abstract class FileType {
    protected String fileName;
    protected String filePath;
    protected File file;

    public FileType(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    protected abstract void getAdditionalFileInfo();
    public abstract void getInfo();

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }
}
