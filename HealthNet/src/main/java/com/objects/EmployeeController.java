package com.objects;

import java.util.Random;

/*
 * Project: HealthNet Application
 * Authors: Peter Barnes IV
 * Date: November 3, 2025
 * Purpose: This object will control the login information for a recipient object
 *
 *TODO: Change the @generateRecipientCredentialsID method to reflect the SQL database
 *
 */

public class EmployeeController {
	
		private int employeeID;
	    private String hashedPassword;
	    private String salt;
	    private String email;

	    public EmployeeController( int employeeID, String hashedPassword, String salt, String email) {
	    	this.employeeID = employeeID;
	        this.hashedPassword = hashedPassword;
	        this.salt = salt;
	        this.email = email;
	    }

	    //Getter methods
		public String getHashedPassword() {
			return hashedPassword;
		}

		public String getSalt() {
			return salt;
		}

		public String getEmail() {
			return email;
		}
		
		public int getEmployeeID() {
			return employeeID;
		}

		//Setter Methods
		public void setHashedPassword(String hashedPassword) {
			this.hashedPassword = hashedPassword;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public void setEmployeeID( int employeeID) {
			this.employeeID = employeeID;
		}

	    
	    

    
}
