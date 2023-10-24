package org.oop.lab.zero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert: \n1 for User class output\n2 for Diamond class output");
        int choice = scanner.nextInt();
        while (true) {
            if (choice == 1) {
                User user1 = new User("Ion", "Doe");
                User user2 = new User("Miley", "Cyrus");
                System.out.println(user1.displayInitials());
                System.out.println(user2.displayInitials());
                break;
            } else if (choice == 2) {
                Diamond diamond = new Diamond(7);
                diamond.printDiamond();
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}