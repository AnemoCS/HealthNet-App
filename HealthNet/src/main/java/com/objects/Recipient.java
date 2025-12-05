package com.objects;
import java.time.LocalDate;


/*
 * Project: HealthNet Application
 * Authors: Peter Barnes IV
 * Date: November 2, 2025
 * Purpose: This object will represent all applicants that sign up for Medicaid
 */



public class Recipient {

    // Primary key
    private int recipientID;

    // Required fields
    private String firstName;
    private String middleName; //Optional
    private String lastName;
    private String namePrefix; //Optional
    private LocalDate dateOfBirth;
    private String addressStreet;
    private String addressAptNum; // Optional
    private String addressCity;
    private String addressState;
    private String addressZipCode;
    private String SSN;
    private String phoneNumber;

    public Recipient(String firstName, String lastName, LocalDate dateOfBirth,
                     String addressStreet, String addressCity, String addressState,
                     String addressZipCode, String SSN, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
        this.SSN = SSN;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
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

    public String getNamePrefix() {
        return namePrefix;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressAptNum() {
        return addressAptNum;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }


    public String getSSN() {return SSN;}

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public void setAddressAptNum(String addressAptNum) {
        this.addressAptNum = addressAptNum;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public void setSSN(String SSN) {this.SSN = SSN;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}