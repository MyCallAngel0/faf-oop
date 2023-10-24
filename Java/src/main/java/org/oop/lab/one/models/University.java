package org.oop.lab.one.models;

import org.oop.lab.one.behaviour.FileManager;
import org.oop.lab.one.behaviour.LogManager;

import java.util.ArrayList;

public class University {
    public ArrayList<Faculty> faculties = new ArrayList<>();

    public void createFaculty(String[] faculty) {
        try {
            this.faculties.add(new Faculty(faculty[1], faculty[2], StudyField.valueOf(faculty[3])));
            System.out.println("Faculty added");
            LogManager.log("INFO: From function createFaculty -> Faculty added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LogManager.log("ERROR: From function createFaculty -> Failed to create faculty!");
        }
    }

    public void searchStudentFaculty(String email) {
        LogManager.log("INFO: From function searchStudentFaculty -> Executing...");
        faculties.stream().filter(faculty -> faculty.students.stream().anyMatch(student -> student.getEmail().equals(email)))
                .findFirst().ifPresent(faculty -> System.out.println("Student belongs to " + faculty.getAbbreviation()));
    }

    public void displayUniversityFaculties() {
        System.out.println("University faculties:");
        LogManager.log("INFO: From function displayUniversityFaculties -> Executing...");
        faculties.forEach(faculty -> System.out.println(faculty.getName()));
    }

    public void displayFacultiesBelongingToAField(String studyField) {
        System.out.println("Faculties belonging to " + studyField);
        LogManager.log("INFO: From function displayFacultiesBelongingToAField -> Executing...");
        faculties.stream().filter(faculty -> faculty.getStudyField().equals(StudyField.valueOf(studyField)))
                .forEach(faculty -> System.out.println(faculty.getName()));
    }

    public void createStudent(String[] information) {
        faculties.stream().filter(faculty -> faculty.getAbbreviation().equals(information[1]))
                .findFirst().ifPresent(faculty -> faculty.createAndAssignStudent(information));
    }

    public void displayStudentsThatAreEnrolled(String abbreviation) {
        faculties.stream().filter(faculty -> faculty.getAbbreviation().equals(abbreviation))
                .findFirst().ifPresent(Faculty::displayEnrolledStudents);
    }

    public void displayStudentsThatGraduated(String abbreviation) {
        faculties.stream().filter(faculty -> faculty.getAbbreviation().equals(abbreviation))
                .findFirst().ifPresent(Faculty::displayGraduatedStudents);
    }
    public void isBelongingToFaculty(String abbreviation, String email) {
        faculties.stream()
                .filter(faculty -> faculty.getAbbreviation().equals(abbreviation))
                .findFirst().ifPresent(faculty -> faculty.isBelongingToThisFaculty(email));
    }
    public void changeStudentFirstName(String email, String firstName) {
        faculties.stream().filter(faculty ->
                        faculty.students.stream().anyMatch(student -> student.getEmail().equals(email))).findFirst()
                .ifPresent(faculty -> faculty.changeStudentFirstName(email, firstName));
    }
    public void changeStudentLastName(String email, String lastName) {
        faculties.stream().filter(faculty ->
                        faculty.students.stream().anyMatch(student -> student.getEmail().equals(email))).findFirst()
                .ifPresent(faculty -> faculty.changeStudentLastName(email, lastName));
    }
    public void displayStudent(String email) {
        faculties.stream().filter(faculty ->
                        faculty.students.stream().anyMatch(student -> student.getEmail().equals(email))).findFirst()
                .ifPresent(faculty -> faculty.displayStudent(email));
    }

    public void graduateBatchStudents() {
        ArrayList<String> emails = FileManager.graduateStudents();
        assert emails != null;
        emails.forEach(email -> faculties.forEach(faculty -> faculty.graduateStudent(email)));
    }

    @Override
    public String toString() {
        return faculties.toString();
    }

}