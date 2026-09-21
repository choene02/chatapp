
package com.mycompany.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DatabaseConnection.createTables();
        
        boolean running = true;

        System.out.println("WELCOME TO CHAT APP");

        while (running) {

            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:");

            // Check that the user enters a number
            if (!scanner.hasNextInt()) {

                System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

           //prompt user to enter user credentials

            if (choice == 1) {

                System.out.println("== REGISTRATION ==");

                String username;

                // USERNAME
                while (true) {

                    System.out.println("Enter username:");
                    username = scanner.nextLine();

                    if (username.length() <= 5
                            && username.contains("_")) {

                        System.out.println("Username successfully captured.");
                        break;

                    } else {

                        System.out.println(
                                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length."
                        );
                    }
                }

              //password
                String password;

                while (true) {

                    System.out.println("Enter password:");
                    password = scanner.nextLine();

                    if (password.length() >= 8
                            && password.matches(".*[A-Z].*")
                            && password.matches(".*[0-9].*")
                            && password.matches(".*[!@#$%^&*(),.?\\\":{}|<>_].*")) {

                        System.out.println("Password successfully captured.");
                        break;

                    } else {

                        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special characters."
                               
                        );
                    }
                }

               // cell phone number
               
                
                String cellphone;

                while (true) {

                    System.out.println("Enter cellphone:");
                    cellphone = scanner.nextLine();

                    if (cellphone.matches("^\\+27[0-9]{9}$")) {

                        System.out.println("Cell phone number successfully captured.");
                        break;

                    } else {

                        System.out.println(
                                "Cell phone number incorrectly formatted or does not contain international code."
                        );
                    }
                }

            

                String sql = "INSERT INTO users (username, password, cellphone) "
                           + "VALUES (?, ?, ?)";

                try (Connection connection = DatabaseConnection.connect();
                     PreparedStatement statement =
                             connection.prepareStatement(sql)) {

                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.setString(3, cellphone);

                    statement.executeUpdate();

                    System.out.println();
                    System.out.println("Registration successful!");

                } catch (SQLException e) {

                    System.out.println();
                    System.out.println("Registration failed.");
                    System.out.println("Error: " + e.getMessage());
                }


            } else if (choice == 2) {

                loginuser.login(scanner);

            

            } else if (choice == 3) {

                System.out.println("Thank you for using Chat App!");
                running = false;

            } else {

                System.out.println(
                        "Invalid choice. Please enter 1, 2 or 3."
                );
            }
        }

        scanner.close();
    }
}
