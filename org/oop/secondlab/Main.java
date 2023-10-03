package org.oop.secondlab;

import org.oop.secondlab.behaviour.ApplicationLoop;
import org.oop.secondlab.behaviour.FileManager;

public class Main {
    public static void main(String[] args) {
        ApplicationLoop app = new ApplicationLoop();
        app.run();
        FileManager.save(app.university.faculties);
    }
}
