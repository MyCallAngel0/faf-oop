package org.oop.secondlab.behaviour;

import org.oop.secondlab.models.University;

import java.io.File;
import java.util.Scanner;

public class ApplicationLoop {
    public University university;
    private final Scanner scanner;
    private String command;
    private boolean saved;

    public ApplicationLoop() {
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this.command = "";
        this.saved = true;
    }

    public void run() {
        System.out.println("Welcome to TUM's student management system! \n" +
                "What do you want to do?");
        while (!this.command.equals("q")) {
            System.out.println("g - General operations \n" +
                    "f - Faculty operations \n" +
                    "s - Student operations \n" +
                    "l - Load previous save\n \n" +
                    "q - Quit program");

            this.command = takeUserInput();

            switch (command) {
                case "f":
                    do {
                        System.out.println("Faculty operations \n" +
                                "What do you want to do? \n \n" +
                                "ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - " +
                                "add a (n)ew (s)tudent \n" +
                                "gs/<email> - (g)raduate (s)tudent \n" +
                                "ds/<faculty abbreviation> - " +
                                "(d)isplay enrolled (s)tudents\n" +
                                "dg/<faculty abbreviation> - (d)isplay (g)raduated students\n" +
                                "bf/<faculty abbreviation>/<email> - check if student (b)elongs to (f)aculty \n \n" +
                                "s - (s)ave current iteration\n" +
                                "b - Back \n" +
                                "q - Quit program");

                        this.command = takeUserInput();
                        String[] commandsListF = this.command.split("/");
                        try {
                            switch (commandsListF[0]) {
                                case "ns":
                                    this.saved = false;
                                    university.faculties.stream().filter(faculty -> faculty.getAbbreviation().equals(commandsListF[1]))
                                            .findFirst().ifPresent(faculty -> faculty.createAndAssignStudent(commandsListF));
                                    break;
                                case "gs":
                                    this.saved = false;
                                    university.faculties.stream().forEach(faculty -> faculty.graduateStudent(commandsListF[1]));
                                    break;
                                case "ds":
                                    university.faculties.stream()
                                            .filter(faculty -> faculty.getAbbreviation().equals(commandsListF[1]))
                                            .findFirst().ifPresent(faculty -> faculty.displayEnrolledStudents());
                                    break;
                                case "dg":
                                    university.faculties.stream()
                                            .filter(faculty -> faculty.getAbbreviation().equals(commandsListF[1]))
                                            .findFirst().ifPresent(faculty -> faculty.displayGraduatedStudents());
                                    break;
                                case "bf":
                                    university.faculties.stream()
                                            .filter(faculty -> faculty.getAbbreviation().equals(commandsListF[1]))
                                            .findFirst().ifPresentOrElse(faculty -> faculty.isBelongingToThisFaculty(commandsListF[2])
                                                    , () -> System.out.println("Student not found!"));
                                case "s":
                                    FileManager.save(university.faculties);
                                    this.saved = true;
                                    break;
                                case "b":
                                    break;
                                case "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command input!");
                        }
                        System.out.println();
                    } while (!command.equals("b") && !command.equals("q"));
                    break;
                case "g":
                    do {
                        System.out.println("General operations \n" +
                                "What do you want to do? \n \n" +
                                "nf/<faculty name>/<faculty abbreviation>/<field> -" +
                                "create a (n)ew (f)aculty\n" +
                                "ss/<student email>" +
                                " - (s)earch (s)tudent and show faculty\n" +
                                "da - (d)isplay (a)ll faculties\n" +
                                "df/<field> - (d)isplay (f)aculties of a field\n" +
                                "b- Back \n" +
                                "q - Quit program");

                        this.command = takeUserInput();
                        String[] commandsListG = this.command.split("/");
                        try {
                            switch (commandsListG[0]) {
                                case "nf":
                                    this.saved = false;
                                    university.createFaculty(commandsListG);
                                    break;
                                case "ss":
                                    university.searchStudentFaculty(commandsListG[1]);
                                    break;
                                case "da":
                                    university.displayUniversityFaculties();
                                    break;
                                case "df":
                                    university.displayFacultiesBelongingToAField(commandsListG[1]);
                                    break;
                                case "b":
                                    break;
                                case "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command input!");
                        }
                        catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        System.out.println();
                    } while (!command.equals("b") && !command.equals("q"));
                    break;
                case "s":
                    do {
                        System.out.println("Student operations \n" +
                                "What do you want to do? \n \n" +
                                "cf/<student email>/<first name> -" +
                                "(c)hange (f)irst name\n" +
                                "cl/<student email>/<last name>" +
                                " - (c)hange (l)ast name\n" +
                                "ds/<student email> - (d)isplay (s)tudent information\n" +
                                "b- Back \n" +
                                "q - Quit program");

                        this.command = takeUserInput();
                        String[] commandsListS = this.command.split("/");
                        try {
                            switch (commandsListS[0]) {
                                case "cf":
                                    this.saved = false;
                                    university.faculties.stream().filter(faculty ->
                                            faculty.students.stream().filter(student -> student.getEmail().equals(commandsListS[1]))
                                                    .findFirst().isPresent()).findFirst()
                                            .ifPresent(faculty -> faculty.changeStudentFirstName(commandsListS[1], commandsListS[2]));
                                    break;
                                case "cl":
                                    this.saved = false;
                                    university.faculties.stream().filter(faculty ->
                                                    faculty.students.stream().filter(student -> student.getEmail().equals(commandsListS[1]))
                                                            .findFirst().isPresent()).findFirst()
                                            .ifPresent(faculty -> faculty.changeStudentLastName(commandsListS[1], commandsListS[2]));
                                    break;
                                case "ds":
                                    university.faculties.stream().filter(faculty ->
                                                faculty.students.stream().filter(student -> student.getEmail().equals(commandsListS[1]))
                                                        .findFirst().isPresent()).findFirst()
                                        .ifPresent(faculty -> faculty.displayStudent(commandsListS[1]));
                                    break;
                                case "b":
                                    break;
                                case "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command input!");
                        }
                        catch (Exception e) {
                            System.out.println("Something went wrong!");
                        }
                        System.out.println();
                    } while (!command.equals("b") && !command.equals("q"));
                    break;
                case "l":
                    university.faculties = FileManager.load();
                    this.saved = true;
                    break;
                default:
                    System.out.println("Invalid command input!");
            }
        }
        if(!saved) {
            System.out.println("Do you want to save before quitting? y/n");
            takeUserInput();
            if(this.command.toLowerCase().equals("y")) {
                FileManager.save(university.faculties);
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }

        private String takeUserInput() {
            System.out.print("command input> ");
            return scanner.nextLine();
        }
}