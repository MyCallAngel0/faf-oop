package org.oop.secondlab.behaviour;

import org.oop.secondlab.models.University;

import java.util.Scanner;

public class ApplicationLoop {
    public University university;
    private final Scanner scanner;
    private String command;

    public ApplicationLoop() {
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this.command = "";
    }

    public void run() {
        System.out.println("Welcome to TUM's student management system! \n" +
                "What do you want to do?");
        while (!this.command.equals("q")) {
            System.out.println("g - General operations \n" +
                    "f - Faculty operations \n" +
                    "s - Student operations \n \n" +
                    "q - Quit program");

            this.command = takeUserInput();

            switch (command) {
                case "f":
                    do {
                        System.out.println("Faculty operations \n" +
                                "What do you want to do? \n \n" +
                                "ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - " +
                                "create student \n" +
                                "gs/<email> - (g)raduate (s)tudent \n" +
                                "ds/<faculty abbreviation> - " +
                                "(d)isplay enrolled (s)tudents\n" +
                                "dg/<faculty abbreviation> - (d)isplay (g)raduated students\n" +
                                "bf/<faculty abbreviation>/<email> - check if student (b)elongs to (f)aculty \n \n" +
                                "b - Back \n" +
                                "q - Quit program");

                        this.command = takeUserInput();
                        String[] commandsListF = this.command.split("/");
                        try {
                            switch (commandsListF[0]) {
                                case "ns":
                                    university.faculties.stream().filter(faculty -> faculty.getAbbreviation().equals(commandsListF[1]))
                                            .findFirst().ifPresent(faculty -> faculty.createAndAssignStudent(commandsListF));
                                    break;
                                case "gs":
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
                                case "b":
                                    break;
                                case "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (Exception e) {
                            System.out.println("Not enough information! Please add more information.");
                        }
                        System.out.println();
                    } while (!command.equals("b") && !command.equals("q"));
                    break;
                case "g":
                    do {
                        System.out.println("General operations \n" +
                                "What do you want to do? \n \n" +
                                "nf/<faculty name>/<faculty abbreviation>/<field> -" +
                                "create faculty\n" +
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
                            System.out.println("Not enough information! Please add more information.");
                        }
                        catch (Exception e) {
                            System.out.println("Not enough information! Please add more information.");
                        }
                        System.out.println();
                    } while (!command.equals("b") && !command.equals("q"));
                    break;
            }
        }
        scanner.close();
    }

        private String takeUserInput() {
            System.out.print("command input> ");
            return scanner.nextLine();
        }
}

