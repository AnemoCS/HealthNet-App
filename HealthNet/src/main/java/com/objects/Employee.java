package com.objects;

/*
 * Project: HealthNet Application
 * Authors: Peter Barnes IV
 * Date: November 8, 2025
 * Purpose: This object will represent all employees that sign up for Medicaid
 *
 *
 */


import java.util.Random;

public class Employee {
	//Instance variables for logging in (primary key)
    int recipientID;

    //Instance variables for object
    String firstName, middleName, lastName, jobTtitle, SSN;
    
    public Employee(String firstName, String lastName, String jobTitle, String SSN ) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.jobTtitle = jobTitle;
    	this.SSN = SSN;
    }

    //Getter methods
	public int getRecipientID() {
		return recipientID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getJobTtitle() {
		return jobTtitle;
	}
	
	public String getSSN() {
		return SSN;
	}


	//Setter Methods
	public void setRecipientID(int recipientID) {
		this.recipientID = recipientID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setJobTtitle(String jobTtitle) {
		this.jobTtitle = jobTtitle;
	}
	
	public void setSSN(String SSN) {
		this.SSN = SSN;
	}
    
    
}
