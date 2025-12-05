package com.objects;
import java.util.Random;

/*
 * Project: HealthNet Application
 * Authors: Peter Barnes IV
 * Date: November 3, 2025
 * Purpose: This object will control the login information for a recipient object
 */

public class RecipientController {

    //Instance variables for logging in
    private int recipientCredentialsID;
    private int recipientID;
    private String loginEmail;
    private String loginPasswordHash;
    private String salt;

    public RecipientController(int recipientID, String email, String passwordHash, String salt){
        this.recipientID = recipientID;
        this.loginEmail = email;
        this.loginPasswordHash = passwordHash;
        this.salt = salt;
    }

	public int getRecipientCredentialsID() {
		return recipientCredentialsID;
	}

	public int getRecipientID() {
		return recipientID;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public String getLoginPasswordHash() {
		return loginPasswordHash;
	}

	public String getSalt() {
		return salt;
	}

	public void setRecipientCredentialsID(int recipientCredentialsID) {
		this.recipientCredentialsID = recipientCredentialsID;
	}

	public void setRecipientID(int recipientID) {
		this.recipientID = recipientID;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public void setLoginPasswordHash(String loginPasswordHash) {
		this.loginPasswordHash = loginPasswordHash;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    
    
    
}
