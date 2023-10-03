package org.oop.secondlab.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty  {

    private String name;
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void graduateStudent(String email) {
        students.stream()
                .filter(student -> student.getEmail().equals(email)).findFirst()
                .ifPresent(student -> { if(student.isGraduated()) {
                    System.out.println("Student is already a graduate!");
                } else {
                    student.setGraduated(true);
                    System.out.println(student.getFirstName() + " " + student.getLastName() + " has graduated!"); }
                });
    }

    public void displayEnrolledStudents() {
        students.stream().filter(student -> !student.isGraduated())
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));
    }

    public void displayGraduatedStudents() {
        students.stream().filter(student -> student.isGraduated())
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getLastName()));
    }

    public void isBelongingToThisFaculty(String email) {
        System.out.println( students.stream().anyMatch(student -> student.getEmail().equals(email))
                ? "Student is enrolled in this faculty!" : "Student is not enrolled in this faculty!" );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public ArrayList<Student> getStudents() {
        return students;
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
