package org.oop.lab.two.behavior;

import org.oop.lab.two.filetype.FileType;

import java.util.Scanner;

import static org.oop.lab.two.behavior.Commands.*;

public class Application {

    private final Scanner scanner;
    private FileManager fileManager;
    private String command;
    private boolean saved;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager();
        this.command = "";
    }

    public void run() {
        Commands.takeSnapshot();
        System.out.println("Available commands: commit, info, status");
        while (true) {
            System.out.print("> ");
            String action = scanner.nextLine();

            switch (action) {
                case "commit":
                    commit();
                    break;
                case "info":
                    info();
                    break;
                case "status":
                    status();
                    break;
                case "exit":
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}
