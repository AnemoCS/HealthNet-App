package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMySQLConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/healthnet";
        String user = "root";
        String pass = "AnemoSQL18!";

        try {
            // Load MySQL driver (optional for modern versions, but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Could not load MySQL driver!");
            e.printStackTrace();
            return;
        }

        String sql = """
            SELECT medicaid_employee_credentials_id, login_email, login_password_hash
            FROM medicaidemployeecredentials
            INNER JOIN medicaidemployee
            ON medicaidemployeecredentials.medicaid_employee_id = medicaidemployee.medicaid_employee_id
        """;

        try (
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ) {
            System.out.println("✅ Successfully connected to MySQL!");

            boolean found = false;

            while (resultSet.next()) {
                found = true;

                int id = resultSet.getInt("medicaid_employee_credentials_id");
                String email = resultSet.getString("login_email");
                String passwordHash = resultSet.getString("login_password_hash");

                System.out.println("ID: " + id);
                System.out.println("Email: " + email);
                System.out.println("Password Hash: " + passwordHash);
                System.out.println("-----------------------");
            }

            if (!found) {
                System.out.println("No matching employees found.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}
