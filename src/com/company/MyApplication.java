package com.company;

import com.company.controllers.PersonController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final PersonController controller;
    private final Scanner scanner;

    public MyApplication(PersonController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to Ticketon.com");
            System.out.println("Select option:");
            System.out.println("1. Get all persons");
            System.out.println("2. Get person by id");
            System.out.println("3. Create person");
            System.out.println("0. Exit");
            System.out.println();
            try {
                System.out.print("Enter option (1-3): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    getAllPersonsMenu();
                } else if (option == 2) {
                    getPersonByIdMenu();
                } else if (option == 3) {
                    createPersonMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer: " + e);
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*************************");
        }
    }

    public void getAllPersonsMenu() {
        String response = controller.getAllPersons();
        System.out.println(response);
    }

    public void getPersonByIdMenu() {
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        String response = controller.getPerson(id);
        System.out.println(response);
    }

    public void createPersonMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter age");
        String age = scanner.next();
        System.out.println("Please enter email");
        String email = scanner.next();

        String response = String.valueOf(String.valueOf(controller.createPerson(name, age, email)));
        System.out.println(response);
    }
}
