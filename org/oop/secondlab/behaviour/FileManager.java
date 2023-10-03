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
            FileWriter myWriter = new FileWriter("D:\\Coding\\OOP\\Java\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\save");
            for(Faculty faculty : faculties) {
                myWriter.write(faculty.toString());
            }
            myWriter.close();
            LogManager.log("INFO: In function save -> Faculties saved successfully!");
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
            File myFile = new File("D:\\Coding\\OOP\\Java\\src\\main\\java\\org\\oop\\secondlab\\behaviour\\save");
            if(myFile.exists() && myFile.length()==0) {
                return null;
            }
            Scanner myReader = new Scanner(myFile);
            int index = 0;
            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                System.out.println(name);
                String abbreviation = myReader.nextLine();
                System.out.println(abbreviation);
                String studyField = myReader.nextLine();
                System.out.println(studyField);
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

    public static ArrayList<String> readStudents(String filename) {
        LogManager.log("INFO: From function readStudents -> Executing...");
        try {
            ArrayList<String> emailList = new ArrayList<>();
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
            LogManager.log("INFO: From function readStudents -> Successfully Read Data!");
            return emailList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("File not found!");
            LogManager.log("ERROR: In function readStudents -> File not found!");
        }
        return null;
    }
    public static void readStudents(String filename, int indexOfFaculty, List<Faculty> faculties) {
        LogManager.log("INFO: From function readStudents -> Executing...");
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
            fileReader.close();
            LogManager.log("INFO: From function readStudents -> Data read succesfully!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            LogManager.log("ERROR: In function readStudents -> File not found!");
            System.out.println("File not found!");
        }
    }
}