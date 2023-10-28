package org.oop.lab.two;

import org.oop.lab.two.behavior.Application;
import org.oop.lab.two.behavior.FileManager;

public class Main {
    public static void main(String[] args) {
        /*FileType image = new ImageFile("Bostan.png", ".in\\src\\main\\java\\org\\oop\\lab\\two\\git\\Bostan.png");
        image.getInfo();
        FileType text = new TextFile("text.txt", ".\\src\\main\\java\\org\\oop\\lab\\two\\git\\text");
        text.getInfo();*/
        /*FileManager fileManager = new FileManager();
        fileManager.getFileInformation("python.py");*/
        Application app = new Application();
        app.run();
    }
}
