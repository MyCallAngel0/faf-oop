package org.oop.secondlab.models;

import java.util.ArrayList;

public class University {
    public ArrayList<Faculty> faculties = new ArrayList<>();

    public void createFaculty(String[] faculty) {
        try {
            this.faculties.add(new Faculty(faculty[1], faculty[2], StudyField.valueOf(faculty[3])));
            System.out.println("Faculty added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchStudentFaculty(String email) {
        faculties.stream().filter(faculty -> faculty.students.stream().filter(student -> student.getEmail().equals(email))
                .findFirst().isPresent()).findFirst().ifPresent(faculty -> System.out.println(faculty.getAbbreviation()));
    }

    public void displayUniversityFaculties() {
        faculties.stream().forEach(faculty -> System.out.println(faculty.getName()));
    }

    public void displayFacultiesBelongingToAField(String studyField) {
        faculties.stream().filter(faculty -> faculty.getStudyField().equals(StudyField.valueOf(studyField)))
                .forEach(faculty -> System.out.println(faculty.getName()));
    }

    @Override
    public String toString() {
        return faculties.toString();
    }

    /*1. Create a new faculty.
2. Search what faculty a student belongs to by a unique identifier (for example by email
            or a unique ID).
            3. Display University faculties.
4. Display all faculties belonging to a field. (Ex. FOOD_TECHNOLOGY)*/
}
