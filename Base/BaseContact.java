/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		BaseContant.java
 *
 * 				This file is used to describe the abstract class
 * 				of a BaseContact Object. Will then be
 * 				inherited by Person and Business contacts.
 *
 * Statement
 * of own Work: This is my own work as influenced by the
 * 				Milestone 2 guide.
 *
 */
package com.example.contactapp.Base;


import com.example.contactapp.Models.Address;
import com.example.contactapp.Models.BusinessContact;
import com.example.contactapp.Models.PersonContact;
import com.example.contactapp.Models.Photo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value= PersonContact.class, name= "personal"),
        @JsonSubTypes.Type(value= BusinessContact.class, name="business")})
public abstract class BaseContact implements Comparable<BaseContact> {

    // Properties
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected Address address;
    protected Photo photo;
    protected int photoNum;
    protected String email;
    protected int idNum;

    @JsonIgnore
    static protected int numOfContacts  = 0;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setters
    public String getFirstName() {
        return this.firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return this.lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;

    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Photo getPhoto() {
        return this.photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
        System.out.println("Set photo for: " + this.firstName +": " + this.photo);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdNum() {
        return this.idNum;
    }

    private void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public int getNumOfContacts() {
        return BaseContact.numOfContacts;
    }

    public int getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(int photoNum) {
        this.photoNum = photoNum;
    }

    // Default Constructor
    public BaseContact() {
        this.firstName = "No Name";
        this.lastName = "No last name";
        this.phoneNumber = "No phone number";
        this.address = null;
        this.photo = new Photo();
        this.photo.setPhotoID(0);
        this.email = "No Email";
        setIdNum(++numOfContacts);


    }

    // Constructor with parameters
    public BaseContact(String firstName, String lastName, String phoneNumber, Address address, Photo photo,
                       String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.photo = photo;
        this.email = email;
        setIdNum(++numOfContacts);
        this.photoNum = 0;
    }

    public abstract void displayContact();




}
