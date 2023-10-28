package org.oop.lab.two;

import org.oop.lab.two.behavior.Application;
import org.oop.lab.two.behavior.FileChangeDetection;

public class Main {
    public static void main(String[] args) {
        FileChangeDetection fcd = new FileChangeDetection();
        fcd.start();
        Application app = new Application();
        app.run();
    }
}
