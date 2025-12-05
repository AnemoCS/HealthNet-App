  package com.jdbc;

import java.sql.Statement;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnection {
	
	
	
	public static void main(String[] args )  {
		
		/*
		try {
			//Step 2 Establish the connection
			 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthnettestdbone", "root", "AnemoSQL18!");
		    Statement statement = con.createStatement();
		    ResultSet resultSet =  statement.executeQuery("SELECT * FROM User");
		    System.out.println("Connection created");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("first_name"));
			} //end of while loop
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
		//This is the test file
		
	}
	
	 public static void fetchUserFirstNames() {
	        String url = "jdbc:mysql://localhost:3306/healthnettestdbone";
	        String username = "root";
	        String password = "AnemoSQL18!";

	        try (
	            Connection con = DriverManager.getConnection(url, username, password);
	            Statement statement = con.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT first_name FROM User");
	        ) {
	            System.out.println("Connection created");

	            while (resultSet.next()) {
	                System.out.println(resultSet.getString("first_name"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void fetchSqlStatement(String sql) {
	        String url = "jdbc:mysql://localhost:3306/healthnettestdbone";
	        String username = "root";
	        String password = "AnemoSQL18!";

	        try (
	            Connection con = DriverManager.getConnection(url, username, password);
	            Statement statement = con.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);
	        ) {
	            System.out.println("Connection created");

	            while (resultSet.next()) {
	                System.out.println(resultSet.getString("first_name"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	
}
	
	
