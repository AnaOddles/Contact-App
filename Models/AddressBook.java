/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		AddressBook.java
 *
 * 				This file is used to create an AddressBook object
 * 				that saves contacts as an ArrayList of contact objects.
 * 				The ArrayList is of BaseContact Type so it can hold
 * 				both contacts types.
 *
 * 				Methods included to add, remove, display one, sort,
 * 				and search for contacts by any property.
 *
 *
 *
 *
 * Statement
 * of own Work: This is my own work as influenced by the
 * 				Milestone 2 guide.
 *
 */
package com.example.contactapp.Models;

//Import the necessary packages
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.example.contactapp.Base.BaseContact;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("AddressBook")
public class AddressBook {

    // Property for AddressBook
    private ArrayList<BaseContact> contactList;

    // Default Constructor, creates an empty list for the type BaseContact
    public AddressBook() {
        this.contactList = new ArrayList<BaseContact>();
        //System.out.println("Created an empty address book.");
    }

    // Constructor with parameters
    public AddressBook(ArrayList<BaseContact> contactList) {
        this.contactList = contactList;
        System.out.println("Created an address book with exisiting contacts!");

    }

    // Get the contact list
    public ArrayList<BaseContact> getContactList() {
        return this.contactList;
    }

    public void addToContactList() {

    }

    // Add a business/person contact to the arraylist
    @RequiresApi(api = Build.VERSION_CODES.N)
    public <T extends BaseContact> void addOne(BaseContact contact) {
        this.contactList.add(contact);
        System.out.println("Added " + contact + " to the address book.");
        sortContactList();

    }

    // Remove a contact from the list, return either true or false for success of
    // removal
    public <T extends BaseContact> boolean deleteOne(T contact) {
        // Check to see if the contact is in the list before removing it
        if (this.contactList.contains(contact)) {
            this.contactList.remove(contact);
            System.out.println("Removed " + contact + " from the address book");
            sortContactList();
            return true;
        } else {
            return false;
        }
    }

    // Method that uses the overrided compareTo() method to sort the ContactList
    public ArrayList<BaseContact> sortContactList() {
        System.out.println("Address book sorted by last name");
        Collections.sort(this.contactList);
        return contactList;

    }

    // Method that overrides the toString() method
    @Override
    public String toString() {
        return "Address Book = " + contactList;
    }

    // Searches contact for ID
    public BaseContact idSearch(int idNum) {
        System.out.println("Searching for contact with id: " + idNum);
        BaseContact idFound = null;
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getIdNum() == idNum) {
                System.out.println("Pulled " + contactList.get(i));
                idFound = contactList.get(i);
            }

        }
        idFound.displayContact();
        return idFound;
    }

    // Searches for contact by first name
    //Add to an arrayList and return that arryLIst
    public ArrayList<BaseContact> firstNameSearch(String name) {
        System.out.println("Searching for contact with name: " + name);
        ArrayList<BaseContact> namesFound = new ArrayList<BaseContact>();
        BaseContact nameFound = null;
        try {
            for (int i = 0; i < contactList.size(); i++) {
                if (contactList.get(i).getFirstName().equals(name)) {
                    System.out.println("Pulled " + contactList.get(i));
                    nameFound = contactList.get(i);
                    namesFound.add(nameFound);
                    nameFound.displayContact();
                }

            }

        } catch (NullPointerException e) {
            System.out.println("Could not find a contact with the first name: " + name + ".");
        }
        return namesFound;
    }

    // Searches for contact by last name
    public ArrayList<BaseContact> lastNameSearch(String name) {
        System.out.println("Searching for contact with name: " + name);
        ArrayList<BaseContact> namesFound = new ArrayList<BaseContact>();
        BaseContact nameFound = null;
        //ArrayList<BaseContact> contactsFound = new ArrayList<BaseContact>();
        //if(this.contactList.getName.contains(name)))
        try {
            for (int i = 0; i < contactList.size(); i++) {

                if (contactList.get(i).getlastName().equals(name)) {
                    System.out.println("Pulled " + contactList.get(i));
                    nameFound = contactList.get(i);
                    namesFound.add(nameFound);
                    nameFound.displayContact();
                }

            }
        } catch (NullPointerException e) {
            System.out.println("Could not find a contact with the last name: " + name + ".");
        }
        return namesFound;
    }

    // Searched for contact by city
    public ArrayList<BaseContact> citySearch(String city) {
        System.out.println("Searching for contact with city: " + city);
        ArrayList<BaseContact> citiesFound = new ArrayList<BaseContact>();
        BaseContact cityFound = null;
        for (int i = 0; i < contactList.size(); i++) {
            Address address = contactList.get(i).getAddress();
            // System.out.println(address);
            if (address.getCity() == city) {
                System.out.println("Pulled " + contactList.get(i));
                cityFound = contactList.get(i);
                citiesFound.add(cityFound);
                cityFound.displayContact();
            }

        }

        return citiesFound;
    }

    // Searches for contact by email
    public ArrayList<BaseContact> emailSearch(String email) {
        System.out.println("Searching for contact with email: " + email);
        ArrayList<BaseContact> emailsFound = new ArrayList<BaseContact>();
        BaseContact emailFound = null;
        try {
            for (int i = 0; i < contactList.size(); i++) {
                if (contactList.get(i).getEmail().equals(email)) {
                    System.out.println("Pulled " + contactList.get(i));
                    emailFound = contactList.get(i);
                    emailsFound.add(emailFound);
                    emailFound.displayContact();
                }

            }

        } catch (NullPointerException e) {
            System.out.println("Could not find a contact with the email: " + email + ".");
        }
        return emailsFound;
    }

}

