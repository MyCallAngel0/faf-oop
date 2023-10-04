package org.oop.secondlab.behaviour;

import org.oop.secondlab.models.Faculty;
import org.oop.secondlab.models.Student;
import org.oop.secondlab.models.StudyField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {
    public static void save(List<Faculty> faculties) {
        try {
            FileWriter myWriter = new FileWriter(".\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\saves\\save");
            for(Faculty faculty : faculties) {
                myWriter.write(faculty.toString());
            }
            myWriter.close();
            LogManager.log("INFO: From function save -> Faculties saved successfully!");
            System.out.println("Faculties saved successfully");
        } catch(IOException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR : In function save -> File not found!");
            System.out.println("File not found!");
        } catch(NullPointerException e) {
            LogManager.log("ERROR : In function save -> There are no faculties to save!");
            System.out.println("There are no faculties to save!");
        }
    }
    public static ArrayList<Faculty> load() {
        ArrayList<Faculty> faculties = new ArrayList<>();
        LogManager.log("INFO: From function load -> Executing...");
        try {
            File myFile = new File(".\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\saves\\save");
            if(myFile.exists() && myFile.length()==0) {
                return null;
            }
            Scanner myReader = new Scanner(myFile);
            int index = 0;
            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                String abbreviation = myReader.nextLine();
                String studyField = myReader.nextLine();
                String checkStudent = myReader.nextLine();
                if (checkStudent.equals("[]")) {
                    faculties.add(new Faculty(name, abbreviation, StudyField.valueOf(studyField)));
                }
                else {
                    checkStudent = myReader.nextLine();
                    faculties.add(new Faculty(name, abbreviation, StudyField.valueOf(studyField)));
                    while (!checkStudent.equals("]")) {
                        String firstName = checkStudent;
                        String lastName = myReader.nextLine();
                        String email = myReader.nextLine();
                        String enrollmentDate = myReader.nextLine();
                        String dateOfBirth = myReader.nextLine();
                        String graduated = myReader.nextLine();

                        Student student = new Student(firstName, lastName, email, LocalDate.parse(enrollmentDate), LocalDate.parse(dateOfBirth), Boolean.parseBoolean(graduated));
                        faculties.get(index).addStudent(student);
                        checkStudent = myReader.nextLine();
                        if(checkStudent.equals(",")) {
                            checkStudent = myReader.nextLine();
                        }
                    }
                }
                index++;
            }
            myReader.close();
            LogManager.log("INFO: From function load -> Successfully Read Data!");
            System.out.println("Data loaded successfully!");
            return faculties;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR: In function load -> File not found!");
            System.out.println("File not found!");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            LogManager.log("ERROR: In function load -> Wrong Argument!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            LogManager.log("ERROR: In function load -> An error has occurred!");
        }
        return null;
    }

    public static ArrayList<String> graduateStudents() {
        LogManager.log("INFO: From function graduateStudents -> Executing...");
        try {
            ArrayList<String> emailList = new ArrayList<>();
            File myFile = new File(".\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\batchfiles\\graduates");
            if(myFile.length()==0) {
                System.out.println("File is empty!");
                LogManager.log("ERROR: From function graduateStudent -> File is empty!");
                return null;
            }
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String fileLine = myReader.nextLine();
                if(!Objects.equals(fileLine, "")) {
                    emailList.add(fileLine);
                }
            }
            myReader.close();
            LogManager.log("INFO: From function graduateStudents -> Successfully Read Data!");
            return emailList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("File not found!");
            LogManager.log("ERROR: In function graduateStudents -> File not found!");
        }
        return null;
    }
    public static void readAndAssignStudents(String filename, String abbreviation, List<Faculty> faculties) {
        LogManager.log("INFO: From function readAndAssignStudents -> Executing...");
        try{
            File myFile = new File(".\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\batchfiles\\" + filename);
            if(myFile.exists() && myFile.length()==0) {
                System.out.println("File doesn't exists!");
                LogManager.log("ERROR: In function readAndAssignStudents -> Executing...");
                return;
            }
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()){
                String newLine = myReader.nextLine();
                if(faculties.stream().noneMatch(faculty -> faculty.getAbbreviation().equals(abbreviation))) {
                    System.out.println("Faculty with abbreviation " + abbreviation + " was not found!");
                    LogManager.log("ERROR: In function readAndAssignStudents -> " + abbreviation + "is not a valid abbreviation!");
                    return;
                }
                if(!newLine.equals("")) {
                    String firstName = newLine;
                    String lastName = myReader.nextLine();
                    String email = myReader.nextLine();
                    String enrollmentDate = myReader.nextLine();
                    String dateOfBirth = myReader.nextLine();
                    faculties.stream().filter(faculty -> faculty.getAbbreviation().equals(abbreviation)).findFirst()
                            .ifPresent(faculty -> faculty.students.stream().filter(student -> student.getEmail().equals(email))
                                    .findFirst().ifPresentOrElse(student -> {
                                                System.out.println("Email " + email + " is already taken!");
                                                LogManager.log("ERROR: In function readAndAssignStudents -> Email "+ email + " was already taken!");
                                            },
                                            () -> {
                                                faculties.stream().filter(faculty1 -> faculty1.getAbbreviation().equals(abbreviation))
                                                        .findFirst().ifPresent(faculty1 -> faculty1
                                                                .addStudent(new Student(firstName, lastName, email, LocalDate.parse(dateOfBirth), LocalDate.parse(enrollmentDate))));
                                                System.out.println(firstName + " " + lastName + " was successfully enrolled!");
                                                LogManager.log("INFO: In function readAndAssignStudents -> Student assigned to faculty " + abbreviation);
                                            }));
                }
            }
            myReader.close();
            LogManager.log("INFO: From function readAndAssignStudents -> Successfully Read Data");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR: In function readAndAssignStudents -> File not found!");
            System.out.println("File not found!");
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong input!");
            System.out.println(e.getMessage());
            LogManager.log("ERROR: In function readAndAssignStudents -> Wrong Information assigned!");
        }
    }
}