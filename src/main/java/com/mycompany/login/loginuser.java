/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class loginuser {

    public static void login(Scanner scanner) {

        System.out.println("== LOGIN ==");
//prompt user to enter credential to run authentication
        while (true) {

            System.out.println("Enter username:");
            String username = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            try (Connection connection = DatabaseConnection.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet result = statement.executeQuery();

                if (result.next()) {
//Display Welcome with user name.
                    System.out.println("Welcome  "+ username );
                    break;

                } else {

                    System.out.println("Username or password incorrect, please try again.");
                    
                }

            } catch (SQLException e) {

                System.out.println("Database error: " + e.getMessage());
                break;
            }
        }
    }
}
