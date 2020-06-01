/*	Name:		Ana Sanchez
 * 	Date:		1/30/2020
 * 	Course:		CST- 135
 * 				Milestone 2
 * 	File:		Photo.java
 *
 * 				This file is used to create a Photo object.
 * 				Contains the properties for photo.
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
public class Photo {

    //Properties for  Object
    private int photoID;
    private String fileName;
    private String dateOfPhoto;
    private String description;
    //private BaseContact contact;

    //Default Constructor
    public Photo(){
        this.fileName = "No name";
        this.dateOfPhoto = "No data of photo";
        this.description = "No description";
        //this.contact = null;
    }

    //Constructor with paramteres
    public Photo(int photoID, String fileName, String dateOfPhoto, String description) {
        this.photoID = photoID;
        this.fileName = fileName;
        this.dateOfPhoto = dateOfPhoto;
        this.description = description;
        //this.contact = contact;
        System.out.println("Created a photo with: " + toString());
    }

    //Getter and Setters
    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDateOfPhoto() {
        return dateOfPhoto;
    }

    public void setDateOfPhoto(String dateOfPhoto) {
        this.dateOfPhoto = dateOfPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        System.out.println("Changed the photo " + this.photoID + " description to " + description);
    }
    //toString method used to display the Photo object
    @Override
    public String toString() {
        return "Photo: photoID= " + photoID + ", fileName= " + fileName + ", dateOfPhoto= " + dateOfPhoto
                + ", description= " + description;
    }


}

