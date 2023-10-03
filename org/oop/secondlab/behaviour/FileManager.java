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
import java.util.Scanner;

public class FileManager {
    public static void save(List<Faculty> faculties) {
        try {
            FileWriter myWriter = new FileWriter("save.txt");
            for(Faculty faculty : faculties) {
                myWriter.write(faculty.toString());
            }
            myWriter.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR : function readDataFromFile -> File not found!");
            System.out.println("File not found!");
        }
    }
    public static List<Faculty> load() {
        List<Faculty> faculties = new ArrayList<>();
        LogManager.log("INFO: function readDataFromFile -> Executing...");
        try {
            File myFile = new File("save.txt");
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
                    }
                }
                index++;
            }
            myReader.close();
            LogManager.log("INFO: function readDataFromFile -> Successfully Read Data!");
            return faculties;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR: function readDataFromFile -> File not found!");
            System.out.println("File not found!");
        }
        return null;
    }

    public static List<String> readStudents(String filename) {
        LogManager.log("INFO: function readListOfStudentsEmail -> Proccessing...");
        try {
            List<String> emailList = new ArrayList<>();
            File myFile = new File(filename+".txt");
            if(myFile.exists() && myFile.length()==0) {
                return null;
            }
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String fileLine = myReader.nextLine();
                if(fileLine != "") {
                    emailList.add(fileLine);
                }
            }
            myReader.close();
            LogManager.log("INFO: function readDataFromFile -> Successfully Read Data!");
            return emailList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("File not found!");
            LogManager.log("ERROR: function readDataFromFile -> File not found!");
        }
        return null;
    }
    public static void readStudents(String filename, int indexOfFaculty, List<Faculty> faculties) {
        LogManager.log("INFO: function readListOfStudentsEmail -> Proccessing...");
        try{
            File myFile = new File(filename + ".txt");
            if(myFile.exists() && myFile.length()==0) {
                System.out.println("File doesn't exists!");
            }
            Scanner fileReader = new Scanner(myFile);
            while (fileReader.hasNextLine()) {
                String fileLine = fileReader.nextLine();
                if(!fileLine.equals("") && !fileLine.equals(",")) {
                    String firstName = fileLine;
                    String lastName = fileReader.nextLine();
                    String email = fileReader.nextLine();
                    String enrollmentDate = fileReader.nextLine();
                    String dateOfBirth = fileReader.nextLine();
                    Student student = new Student(firstName, lastName, email, LocalDate.parse(dateOfBirth), LocalDate.parse(enrollmentDate));
                    if(faculties.contains(student)) {
                        System.out.println("Email "+ email + " is already taken.");
                    } else {
                        faculties.get(indexOfFaculty).addStudent(student);
                    }
                }
            }
            save(faculties);
            fileReader.close();
            LogManager.log("INFO: function readDataFromFile -> Data read succesfully!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR : function readDataFromFile -> File not found!");
            System.out.println("File not found!");
        }
    }
}