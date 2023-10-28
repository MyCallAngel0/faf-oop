package org.oop.lab.two.filetype;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public abstract class FileType {
    protected String fileName;
    protected String filePath;
    protected File file;
    protected FileTime creationTime;
    protected FileTime modifiedTime;
    protected BasicFileAttributes attributes;

    public FileType(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.file = new File(filePath);
        Path path = Path.of(filePath);
        try {
            this.creationTime = Files.getFileAttributeView(
                    path, BasicFileAttributeView.class).readAttributes().creationTime();
            this.modifiedTime = Files.getFileAttributeView(
                    path, BasicFileAttributeView.class).readAttributes().lastModifiedTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected abstract void getAdditionalFileInfo();
    public abstract void getInfo();

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }

    public FileTime getCreationTime() {
        return this.creationTime;
    }
    public FileTime getModifiedTime() {
        return this.modifiedTime;
    }

}
