/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		PersonContact.java
 *
 * 				This file is used to create a PersonContact object
 * 				and then create an instance of that object.
 *
 * Statement
 * of own Work: This is my own work as influenced by the
 * 				Milestone 2 guide.
 *
 */
package com.example.contactapp.Models;

//Import used libraries
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

//Importing BaseContact in order to inherit
import com.example.contactapp.Base.BaseContact;

//@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonTypeName("personal")
@JsonIgnoreProperties({"numOfContacts"})
public class PersonContact extends BaseContact {

    // Properties no inherited from the BaseContact
    private String dateOfBirth;
    private String description;
    private BaseContact[] listOfRelatives;

    // Default Constructor
    public PersonContact() {
        super("No name", "No last name", "No phone number" , null, null, "no email");
    }

    // Constructor with parameters
    public PersonContact(String firstName, String lastName, String phoneNumber, Address address, Photo photo,
                         String email, String dateOfBirth, String description, BaseContact[] listOfRelatives) {
        super(firstName, lastName, phoneNumber, address, photo, email);
        this.dateOfBirth = dateOfBirth;
        this.description = description;
        System.out.println("Created a personal contact for: " + toString() + ".");
    }

    // Getter and Setters
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaseContact[] getListOfRelatives() {
        return listOfRelatives;
    }

    public void setListOfRelatives(BaseContact[] listOfRelatives) {
        this.listOfRelatives = listOfRelatives;
    }

    // Overriding the toString() method to display the object
    @Override
    public String toString() {
        return this.idNum + ": " + firstName + " " +  lastName;
    }

    //Override the compareTo() method when sorting
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

    //Method to display contents of contact, prints elements of contact
    public void displayContact() {
        System.out.println("Displaying contact: ");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Date of birth: " + dateOfBirth);
        System.out.println("Description: " + description);
        System.out.println();
    }

}

