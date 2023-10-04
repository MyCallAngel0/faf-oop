package org.oop.secondlab.behaviour;

import org.oop.secondlab.models.University;

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
        while (!this.command.equalsIgnoreCase("q")) {
            System.out.println("""
                    g - General operations\s
                    f - Faculty operations\s
                    s - Student operations\s
                    b - Batch operations\s
                    l - Load previous save
                    \s
                    q - Quit program""");

            this.command = takeUserInput();

            switch (command.toLowerCase()) {
                case "f" -> {
                    do {
                        System.out.println("""
                                Faculty operations\s
                                What do you want to do?\s
                                \s
                                ns/<faculty abbreviation>/<first name>/<last name>/<email>/<day>/<month>/<year> - add a (n)ew (s)tudent\s
                                gs/<email> - (g)raduate (s)tudent\s
                                ds/<faculty abbreviation> - (d)isplay enrolled (s)tudents
                                dg/<faculty abbreviation> - (d)isplay (g)raduated students
                                bf/<faculty abbreviation>/<email> - check if student (b)elongs to (f)aculty\s
                                s - (s)ave current iteration
                                b - Back\s
                                \s
                                q - Quit program""");

                        this.command = takeUserInput();
                        String[] commandsListF = this.command.split("/");
                        try {
                            switch (commandsListF[0].toLowerCase()) {
                                case "ns":
                                    this.saved = false;
                                    university.createStudent(commandsListF);
                                    break;
                                case "gs":
                                    this.saved = false;
                                    university.faculties.forEach(faculty -> faculty.graduateStudent(commandsListF[1]));
                                    break;
                                case "ds":
                                    university.displayStudentsThatAreEnrolled(commandsListF[1]);
                                    break;
                                case "dg":
                                    university.displayStudentsThatGraduated(commandsListF[1]);
                                    break;
                                case "bf":
                                    university.isBelongingToFaculty(commandsListF[1], commandsListF[2]);
                                case "s":
                                    FileManager.save(university.faculties);
                                    this.saved = true;
                                    break;
                                case "b", "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command input!");
                        }
                        System.out.println();
                    } while (!command.equalsIgnoreCase("b") && !command.equalsIgnoreCase("q"));
                }
                case "g" -> {
                    do {
                        System.out.println("""
                                General operations\s
                                What do you want to do?\s
                                \s
                                nf/<faculty name>/<faculty abbreviation>/<field> -create a (n)ew (f)aculty
                                ss/<student email> - (s)earch (s)tudent and show faculty
                                da - (d)isplay (a)ll faculties
                                df/<field> - (d)isplay (f)aculties of a field
                                b- Back\s
                                q - Quit program""");

                        this.command = takeUserInput();
                        String[] commandsListG = this.command.split("/");
                        try {
                            switch (commandsListG[0].toLowerCase()) {
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
                                case "b", "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command input!");
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        System.out.println();
                    } while (!command.equalsIgnoreCase("b") && !command.equalsIgnoreCase("q"));
                }
                case "s" -> {
                    do {
                        System.out.println("""
                                Student operations\s
                                What do you want to do?\s
                                \s
                                cf/<student email>/<first name> - (c)hange (f)irst name
                                cl/<student email>/<last name> - (c)hange (l)ast name
                                ds/<student email> - (d)isplay (s)tudent information
                                b- Back\s
                                q - Quit program""");

                        this.command = takeUserInput();
                        String[] commandsListS = this.command.split("/");
                        try {
                            switch (commandsListS[0].toLowerCase()) {
                                case "cf":
                                    this.saved = false;
                                    university.changeStudentFirstName(commandsListS[1], commandsListS[2]);
                                    break;
                                case "cl":
                                    this.saved = false;
                                    university.changeStudentLastName(commandsListS[1], commandsListS[2]);
                                    break;
                                case "ds":
                                    university.displayStudent(commandsListS[1]);
                                    break;
                                case "b", "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command input!");
                        } catch (Exception e) {
                            System.out.println("Something went wrong!");
                        }
                        System.out.println();
                    } while (!command.equalsIgnoreCase("b") && !command.equalsIgnoreCase("q"));
                }
                case "b" -> {
                    do {
                        System.out.println("""
                                Batch operations\s
                                What do you want to do?\s
                                \s
                                gs - (g)raduate batch (s)tudents
                                as/<faculty abbreviation>/<filename> - (a)ssign (s)students to a faculty
                                \s
                                b- Back\s
                                q - Quit program""");

                        this.command = takeUserInput();
                        String[] commandsListB = this.command.split("/");
                        try {
                            switch (commandsListB[0].toLowerCase()) {
                                case "gs":
                                    university.graduateBatchStudents();
                                    this.saved = false;
                                    break;
                                case "as":
                                    this.saved = false;
                                    FileManager.readAndAssignStudents(commandsListB[2], commandsListB[1], university.faculties);
                                    break;
                                case "b", "q":
                                    break;
                                default:
                                    System.out.println("Invalid command input!");
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid command input!");
                        } catch (Exception e) {
                            System.out.println("Something went wrong!");
                        }
                        System.out.println();
                    } while (!command.equalsIgnoreCase("b") && !command.equalsIgnoreCase("q"));
                }
                case "l" -> {
                    university.faculties = FileManager.load();
                    this.saved = true;
                }
            }
        }
        if(!saved) {
            System.out.println("Do you want to save before quitting? y/n");
            takeUserInput();
            if(this.command.equalsIgnoreCase("y")) {
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