package org.oop;

public class User {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String displayInitials() {
        return this.firstName.charAt(0) + ". " + this.lastName.charAt(0) + ".";
    }

    public static void main(String[] args) {
        User user1 = new User("Ion", "Doe");
        User user2 = new User("Miley", "Cyrus");
        System.out.println(user1.displayInitials());
        System.out.println(user2.displayInitials());
    }
}
