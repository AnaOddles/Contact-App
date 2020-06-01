/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		BussinessContact.java
 *
 * 				This file is used to create a BusinessContact object
 * 				and then create an instance of that object.
 *
 * Statement
 * of own Work: This is my own work as influenced by the
 * 				Milestone 2 guide.
 *
 */

package com.example.contactapp.Models;

//Import used libraries
import com.example.contactapp.Base.BaseContact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

//Import Base Contact
import com.example.contactapp.Base.BaseContact;


@JsonTypeName("business")
@JsonIgnoreProperties({"numOfContacts"})
public class BusinessContact extends BaseContact {
    // Properties not inherited from the BaseContact
    private String opening;
    private String closing;
    private String daysAWeekOpen;
    private String websiteURL;
    private int contactID;

    // Default Constructor
    public BusinessContact() {
        super("No name", "No last name", "No Phone number", null, null, "no email");
    }

    // Constructor with parameters
    public BusinessContact(String firstName, String lastName, String phoneNumber, Address address, Photo photo,
                           String email, String opening, String closing, String daysAWeekOpen, String websiteURL) {
        super(firstName, lastName, phoneNumber, address, photo, email);
        this.opening = opening;
        this.closing = closing;
        this.daysAWeekOpen = daysAWeekOpen;
        this.websiteURL = websiteURL;
        System.out.println("Created a business contact for: " + toString() + ".");
    }

    // Getter and Setters
    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getDaysAWeekOpen() {
        return daysAWeekOpen;
    }

    public void setDaysAWeekOpen(String daysAWeekOpen) {
        this.daysAWeekOpen = daysAWeekOpen;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    // Overriding the toString() method to print the object
    @Override
    public String toString() {
        return this.idNum + ": " + firstName + " " +  lastName;
    }

    // Override the compareTo() method
    @Override
    public int compareTo(BaseContact other) {
        int compareResult = this.firstName.compareTo(other.getFirstName());

        // If both last name and first match, return true
        if (compareResult == 0) {
            return this.lastName.compareTo(other.getlastName());
        } else {
            // the last names do not match, return the result
            return compareResult;
        }

    }

    //Method to display contents of contact, displays contents of contact
    public void displayContact() {
        System.out.println("Displaying contact:");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Hours of operation: " + opening + "-" + closing);
        System.out.println("Days a week open: " + daysAWeekOpen);
        System.out.println("Website: " + websiteURL);
        System.out.println();
    }

}

