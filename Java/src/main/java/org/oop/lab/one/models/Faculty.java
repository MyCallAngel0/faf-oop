package org.oop.lab.one.models;

import org.oop.lab.one.behaviour.LogManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Faculty  {

    private final String name;
    private final String abbreviation;
    public ArrayList<Student> students = new ArrayList<>();
    private final StudyField studyField;

    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
    }

    public void createAndAssignStudent(String[] student) {
        try {
            this.students.add(new Student(student[2], student[3], student[4], LocalDate.now(), Integer.parseInt(student[5]), Integer.parseInt(student[6]), Integer.parseInt(student[7])));
            System.out.println("Student added");
            LogManager.log("INFO: From function createAndAssignStudent -> Student added!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LogManager.log("ERROR: In function createAndAssignStudent -> Student failed to be added!");
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void graduateStudent(String email) {
        AtomicBoolean bool = new AtomicBoolean(false);
        students.stream()
                .filter(student -> student.getEmail().equals(email)).findFirst()
                .ifPresent(student -> { if(student.isGraduated()) {
                    System.out.println("Student with email " + email + " is already a graduate!");
                    LogManager.log("ERROR: In function graduateStudent -> Student is already a graduate!");
                } else {
                    student.setGraduated(true);
                    bool.set(true);
                    System.out.println(student.getFirstName() + " " + student.getLastName() + " has graduated!");
                    LogManager.log("INFO: From function graduateStudent -> " + student.getFirstName() + " " + student.getLastName() + " has graduated!");
                }
                });
        if (!bool.get()) {
            System.out.println("Can't graduate student with email: " + email + "(student not present)!");
            LogManager.log("ERROR: In function graduateStudent -> Can't graduate student with email: " + email + "(student not present)!");
        }
    }

    public void displayEnrolledStudents() {
        System.out.println("Enrolled students: ");
        LogManager.log("INFO: From function displayEnrolledStudents -> Displaying enrolled students...");
        students.stream().filter(student -> !student.isGraduated())
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));
    }

    public void displayGraduatedStudents() {
        System.out.println("Graduated students: ");
        LogManager.log("INFO: From function displayGraduatedStudents -> Displaying graduates...");
        students.stream().filter(Student::isGraduated)
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));
    }

    public void displayStudent(String email) {
        AtomicBoolean bool = new AtomicBoolean(false);
        students.stream().filter(student -> student.getEmail().equals(email)).findFirst()
                .ifPresent(student -> {
                    System.out.println(student);
                    LogManager.log("INFO: From function displayStudent -> Student displayed successfully!");
                    bool.set(true);
                });
        if (bool.get()) {
            LogManager.log("Error: In function displayStudent -> Student not found!");
            System.out.println("Student not found!");
        }
    }

    public void isBelongingToThisFaculty(String email) {
        LogManager.log("INFO: From function isBelongingToThisFaculty -> Executing...");
        System.out.println( students.stream().anyMatch(student -> student.getEmail().equals(email))
                ? "Student is enrolled in this faculty!" : "Student is not enrolled in this faculty!" );

    }
    public void changeStudentFirstName(String email, String firstName) {
        AtomicBoolean bool = new AtomicBoolean(false);
        students.stream().filter(student -> student.getEmail().equals(email))
                .findFirst().ifPresent(student -> {
                    student.setFirstName(firstName);
                    bool.set(true);
                    LogManager.log("Info: From function changeStudentFirstName -> Student first name changed!");
                });
        if (bool.get()) {
            LogManager.log("Error: In function changeStudentFirstName -> Student not found!");
            System.out.println("Student not found!");
        }
    }

    public void changeStudentLastName(String email, String lastName) {
        AtomicBoolean bool = new AtomicBoolean(false);
        students.stream().filter(student -> student.getEmail().equals(email))
                .findFirst().ifPresent(student -> {
                    student.setLastName(lastName);
                    bool.set(true);
                    LogManager.log("Info: From function changeStudentLastName -> Student last name changed!");
                });
        if (bool.get()) {
            LogManager.log("Error: In function changeStudentLastName -> Student not found!");
            System.out.println("Student not found!");
        }
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    @Override
    public String toString() {
        return name + '\n' +
                abbreviation + "\n" +
                studyField + "\n" +
                students + "\n" ;
    }
}