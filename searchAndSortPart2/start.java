package searchAndSortPart2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class start {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        login();
    }

    private static void run(User user) {
        System.out.println("What would you like to do?");
        System.out.println("1. Add temperature");
        System.out.println("2. View temperatures");
        System.out.println("3. Search temperature");
        System.out.println("4. Logout");

        switch (choseAction()) {
            case 1:
            System.out.println("You are at "+user.getTemperatureCount()+" of 100 total temperatures");
                System.out.println("Enter temperature to add:");
                int temperature = input.nextInt();
                user.addTemperature(temperature);
                break;
            case 2:
                user.printTemperatures();
                break;
            case 3:
                System.out.println("Enter temperature to search:");
                int tempToSearch = input.nextInt();
                int resultIndex = user.searchTemperature(tempToSearch);
                if (resultIndex != -1) {
                    System.out.println("Temperature " + tempToSearch + " found at index " + resultIndex);
                } else {
                    System.out.println("Temperature " + tempToSearch + " not found.");
                }
                break;
            case 4:
                System.out.println("Logging out");
                login();
                break;
            default:
                System.out.println("nani");
                break;
        }

        run(user);
    }

    private static void login() {
        System.out.println("Enter username (or type 'quit' to exit):");
        String username = input.next();

        if (username.equalsIgnoreCase("quit")) {
            System.out.println("Exiting...");
            return;
        }

        System.out.println("Enter password:");
        String password = input.next();

        User foundUser = findUser(username);
        if (foundUser != null && foundUser.authenticate(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            run(foundUser);
        } else {
            System.out.println("User not found or incorrect password. Creating new account.");
            System.out.println("Creating new account. Enter password for new user:");
            String newPassword = input.next();
            users.add(new User(username, newPassword, 100)); 
            System.out.println("Account created. Welcome, " + username + "!");
            run(users.get(users.size() - 1));
        }
    }

    private static User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    private static int choseAction() {
        int choice = 0;
        while (true) {
            try {
                choice = input.nextInt();
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        }
    }
}