package com.dao;

import java.sql.*;

import com.objects.Employee;
import com.objects.EmployeeController;

public class EmployeeDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthnet";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "AnemoSQL18!";

    // Method to validate login credentials 
    public boolean validateLogin(String email, String password) {

        String sql = """
            SELECT login_password_hash, login_password_salt
            FROM medicaidemployeecredentials
            WHERE login_email = ?
        """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                // Email not found
                return false;
            }

            String storedHash = rs.getString("login_password_hash");
            String storedSalt = rs.getString("login_password_salt");

            // Hash the incoming password using the stored salt
            String computedHash = PasswordUtils.hashPassword(password, storedSalt);

            // Compare hashes
            return computedHash.equals(storedHash);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //returns true if there isn't 
    public boolean validateEmail(String email) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM medicaidemployeecredentials
            WHERE login_email = ?
        """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("total");
                return count == 0; // true if email does NOT exist
            } else {
                return true; // no rows returned, so email doesn't exist
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
  //returns true if there isn't 
    public boolean validateSSN(String SSN) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM medicaidemployee
            WHERE social_security_num = ?
        """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, SSN);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("total");
                return count == 0; // true if email does NOT exist
            } else {
                return true; // no rows returned, so email doesn't exist
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void addEmployee(Employee employee ) {
    	String sql = """
                INSERT INTO MedicaidEmployee (first_name, last_name, job_title, social_security_num)
    			VALUES (
    			?,
    			?,
    			?,
    			?  
    			);
            """;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, employee.getFirstName());
                stmt.setString(2, employee.getLastName());
                stmt.setString(3, employee.getJobTtitle());
                stmt.setString(4, employee.getSSN());


                stmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
             
            }
    }
    
    public int getEmployeeID(Employee employee) {
    	 String sql = """
    	            SELECT medicaid_employee_id
    	            FROM MedicaidEmployee	         
    	            WHERE social_security_num = ?
    	        """;

    	        try {
    	            Class.forName("com.mysql.cj.jdbc.Driver");

    	            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    	            PreparedStatement stmt = conn.prepareStatement(sql);
    	            stmt.setString(1, employee.getSSN());

    	            ResultSet rs = stmt.executeQuery();

    	            if (!rs.next()) {
    	                // Email not found
    	                return -1;
    	            } else {
    	            	return rs.getInt("medicaid_employee_id");
    	            }

    	        } catch (Exception e) {
    	        	e.printStackTrace();
        
    	        }
    	        
    	        return -1;
    }

	public void addEmployeeCredentials(EmployeeController controller) {
		// TODO Auto-generated method stub
		String sql = """
                INSERT INTO MedicaidEmployeeCredentials ( medicaid_employee_id, login_email, login_password_hash, login_password_salt)
    			VALUES (
    			?,
    			?,
    			?,
    			?  
    			);
            """;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, controller.getEmployeeID());
                stmt.setString(2, controller.getEmail() );
                stmt.setString(3, controller.getHashedPassword());
                stmt.setString(4, controller.getSalt());

                stmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
             
            }
	}
    



}

