package org.oop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert: \n1 for User class output\n2 for Diamond class output");
        int choice = scanner.nextInt();
        while (true) {
            if (choice == 1) {
                org.oop.User user1 = new org.oop.User("Ion", "Doe");
                org.oop.User user2 = new org.oop.User("Miley", "Cyrus");
                System.out.println(user1.displayInitials());
                System.out.println(user2.displayInitials());
                break;
            } else if (choice == 2) {
                org.oop.Diamond diamond = new org.oop.Diamond(7);
                diamond.printDiamond();
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}