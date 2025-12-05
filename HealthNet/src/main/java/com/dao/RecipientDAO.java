package com.dao;

import java.sql.*;
import java.time.LocalDate;

import com.objects.Recipient;
import com.objects.RecipientController;
import com.objects.Employee;
import com.objects.EmployeeController;

public class RecipientDAO {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthnet";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "AnemoSQL18!";

    // Method to validate login credentials
    public boolean validateLogin(String email, String password) {

        String sql = """
            SELECT login_password_hash, login_password_salt
            FROM recipientcredentials
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
            FROM recipientcredentials
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
            FROM recipient
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

    //returns true if there isn't
    public boolean validatePhoneNumber(String phoneNumber) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM recipient
            WHERE phone_number = ?
        """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phoneNumber);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("total");
                return count ==   0; // true if email does NOT exist
            } else {
                return true; // no rows returned, so email doesn't exist
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void safeSetString(PreparedStatement stmt, int index, String value) throws SQLException {
        if (value == null || value.trim().isEmpty()) {
            stmt.setNull(index, Types.VARCHAR);
        } else {
            stmt.setString(index, value);
        }
    }

    public void addRecipient(Recipient recipient) {
        String sql = """
        INSERT INTO Recipient (
            first_name,
            middle_name,
            last_name,
            name_prefix,
            date_of_birth,
            social_security_num,
            address_street,
            address_apt_num,
            address_city,
            address_state,
            address_zipcode,
            phone_number
        )
        VALUES (
            ?,     -- 1 first_name
            ?,     -- 2 middle_name
            ?,     -- 3 last_name
            ?,     -- 4 name_prefix
            ?,     -- 5 date_of_birth (DATE)
            ?,     -- 6 ssn
            ?,     -- 7 street
            ?,     -- 8 apt
            ?,     -- 9 city
            'Missouri', -- address_state (hardcoded)
            ?,     -- 10 zipcode
            ?      -- 11 phone
        );
    """;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recipient.getFirstName());
            safeSetString(stmt, 2, recipient.getMiddleName());
            stmt.setString(3, recipient.getLastName());
            safeSetString(stmt, 4, recipient.getNamePrefix());

            //conversion for LocalDate -> SQL DATE
            stmt.setDate(5, java.sql.Date.valueOf(recipient.getDateOfBirth()));

            stmt.setString(6, recipient.getSSN());
            stmt.setString(7, recipient.getAddressStreet());
            safeSetString(stmt, 8, recipient.getAddressAptNum());
            stmt.setString(9, recipient.getAddressCity());
            stmt.setString(10, recipient.getAddressZipCode());
            stmt.setString(11, recipient.getPhoneNumber());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public int getRecipientID(Recipient recipient) {
        String sql = """
    	            SELECT recipient_id
    	            FROM recipient
    	            WHERE social_security_num = ?
    	        """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, recipient.getSSN());

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return -1;
            } else {
                return rs.getInt("recipient_id");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return -1;
    }

    public void addRecipientCredentials(RecipientController controller) {
        // TODO Auto-generated method stub
        String sql = """
                INSERT INTO RecipientCredentials ( recipient_id, login_email, login_password_hash, login_password_salt)
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
            stmt.setInt(1, controller.getRecipientID());
            stmt.setString(2, controller.getLoginEmail() );
            stmt.setString(3, controller.getLoginPasswordHash());
            stmt.setString(4, controller.getSalt());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Recipient createRecipient(String recipientID) {
        String sql = """
            SELECT *
            FROM recipient
            WHERE recipient_id = ?
        """;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recipientID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String middleName = rs.getString("middle_name"); // Optional
                    String lastName = rs.getString("last_name");
                    String namePrefix = rs.getString("name_prefix"); // Optional
                    java.sql.Date sqlDate = rs.getDate("date_of_birth");
                    LocalDate DOB = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                    String SSN = rs.getString("social_security_num"); // Check schema!
                    String streetAddress = rs.getString("address_street");
                    String aptNum = rs.getString("address_apt_num"); // Optional
                    String city = rs.getString("address_city");
                    String state = rs.getString("address_state");
                    String zipCode = rs.getString("address_zipcode");
                    String phoneNumber = rs.getString("phone_number");

                    Recipient recipient = new Recipient(firstName, lastName, DOB,
                            streetAddress, city, state, zipCode, SSN, phoneNumber);

                    if (!isBlank(middleName)) recipient.setMiddleName(middleName);
                    if (!isBlank(aptNum)) recipient.setAddressAptNum(aptNum);
                    if (!isBlank(namePrefix)) recipient.setNamePrefix(namePrefix);

                    return recipient;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // No recipient found or error
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }




}

