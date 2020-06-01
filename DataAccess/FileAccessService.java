/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		FileAccessService.java
 *
 * 				This file is used to create the FileAccessService
 * 				for the contacts. Declares the methods to save
 * 				all contacts into a file, and also read all contacts from file.
 * Statement
 * of own Work: This is my own work as influenced by the
 *  				Milestone 2 guide.
 *
 */
package com.example.contactapp.DataAccess;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import com.example.contactapp.Models.AddressBook;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileAccessService {

    //Properties
    //private File textFile = new File("Contacts.json");
    ObjectMapper om = new ObjectMapper();
    Context context;

    //Default Constructor
    public FileAccessService(Context context) {
            this.context = context;
    }

    //Constructor with parameters
    //public FileAccessService(File textFile) {
        //this.setTextFile(textFile);
    //

    //Method to load the records of contacts in beginning of program
    public AddressBook loadRecords(String filename, AddressBook addyBook) { //throws IOException {
        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        //use the objectMapper to call the readValue method and read addressBook class form the textfile
        try {
            addyBook = om.readValue(file, AddressBook.class);
        } catch (JsonParseException e) {
            System.out.println("Exception occurred while converting JSON into Java Object" + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Exception occurred while converting JSON into Java Object" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Exception occurred while converting JSON into Java Object" + e.getMessage());
        }
        return addyBook;
    }

    //Method to save the records when program ends
    public void saveRecords(AddressBook addyBook, String filename) {//throws JsonGenerationException, JsonMappingException, IOException {

        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        //use the objectMapper object to call the
        try {
            om.writerWithDefaultPrettyPrinter().writeValue(file, addyBook);
        } catch (JsonGenerationException e) {
            System.out.println("Exception occurred while converting Java Object into Json " + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Exception occurred while converting Java Object into Json " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Exception occurred while converting Java Object into Json " + e.getMessage());
        }

    }


}

