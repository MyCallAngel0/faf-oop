package org.oop.lab.two.filetype;

import java.io.File;

public abstract class FileType {
    protected String filename;
    protected File file;

    public FileType() {

    }
    private FileType(String filename) {
        this.filename = filename;
    }

    public abstract void getInfo();
}
