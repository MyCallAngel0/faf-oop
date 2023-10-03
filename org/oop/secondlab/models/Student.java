package org.oop.secondlab.models;
import java.time.LocalDate;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate enrollmentDay;
    private final LocalDate dateOfBirth;
    private boolean graduated = false;

    public String getEmail() {
        return email;
    }

    protected String setEmail(String email) throws IllegalArgumentException {
        if(!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getEnrollmentDay() {
        return enrollmentDay;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isGraduated() {
        return this.graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public Student(String firstName, String lastName, String email, LocalDate enrollmentDay, int day, int month, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = setEmail(email);
        this.enrollmentDay = enrollmentDay;
        this.dateOfBirth = LocalDate.of(year, month, day);
    }

    public Student(String firstName, String lastName, String email, LocalDate enrollmentDay, LocalDate dateOfBirth, boolean graduated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDay = enrollmentDay;
        this.dateOfBirth = dateOfBirth;
        this.graduated = graduated;
    }

    public Student(String firstName, String lastName, String email, LocalDate enrollmentDay, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDay = enrollmentDay;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "\n" + firstName + "\n" +
                lastName + "\n" +
                email + "\n" +
                enrollmentDay + "\n" +
                dateOfBirth + "\n" +
                graduated + "\n";
    }
}
