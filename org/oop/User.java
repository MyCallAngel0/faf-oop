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

}
