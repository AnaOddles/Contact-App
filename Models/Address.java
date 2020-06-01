/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		Address.java
 *
 * 				This file is used to create an Address object.
 * 				Contains the properties for address.
 *
 * Statement
 * of own Work: This is my own work as influenced by the
 *  				Milestone 2 guide.
 *
 */

package com.example.contactapp.Models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use=JsonTypeInfo.Id.NAME,
        include=JsonTypeInfo.As.PROPERTY,
        property="address")
public class Address  {

    //Properties for the class
    private int locationID;
    private String street;
    private String city;
    private String state;
    private int zipCode;
    private String country;


    //Default Constructor
    public Address() {
        this.locationID = 000;
        this.street = "No addres";
        this.city = "No city";
        this.state = "No state";
        this.zipCode = 0;
        //this.contact = null;
    }

    //Constructor with parameters
    public Address(int locationID, String street, String city, String state, int zipCode, String country) {
        this.locationID = locationID;
        this.street = street;
        this.city = city;
        this.state = state;
        //this.contact = contact;
        this.zipCode = zipCode;
        this.country = country;
        //System.out.println("Created new address for: " + contact + ": " + toString());
    }

    //Getter and Setters
    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        //System.out.println("Changed street for " + this.contact + " to " + street);
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        //System.out.println("Changed city for " + this.contact + " to " + city);
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    //toString method override to display the Address object
    @Override
    public String toString() {
        return "Address: locationID= " + locationID + ", street= " + street + ", city= " + city +
                ", state= " + street + ", zip code = " + zipCode + ", country= " + country;
    }
}
