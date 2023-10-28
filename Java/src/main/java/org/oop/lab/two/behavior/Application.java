package org.oop.lab.two.behavior;

import java.util.Scanner;

import static org.oop.lab.two.behavior.Commands.*;

public class Application {

    private final Scanner scanner;
    private String command;

    public Application() {
        this.scanner = new Scanner(System.in);
        this.command = "";
    }

    public void run() {
        Commands.takeSnapshot();
        System.out.println("Available commands: commit, info, status");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] action = input.split("\\s");
            switch (action[0]) {
                case "commit":
                    commit();
                    break;
                case "info":
                    info(input);
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
