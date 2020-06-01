/*	Name:		Ana Sanchez
 * 	Date:		4/11/2020
 * 	Course:		CST- 135
 * 				Milestone 6
 * 	File:		PersonAdapter.java
 *
 * 				This file is used to create the the address book
 *              adapter that is the middle man between the AddressBook
 *              class and the list view for the contact app.
 * Statement
 * of own Work: This is my own work as influenced by the
 *  				Shad Sluiter videos.
 *
 */
package com.example.contactapp;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactapp.Base.BaseContact;
import com.example.contactapp.Models.AddressBook;

import org.w3c.dom.Text;

public class AddressBookAdapter extends BaseAdapter {

    //Class properties
    Activity myActivity;
    AddressBook addyBook;

    //Constructor
    public AddressBookAdapter(Activity myActivity, AddressBook addyBook) {
        this.myActivity = myActivity;
        this.addyBook = addyBook;
    }

    //Override getCount method from BaseAdapter
    @Override
    public int getCount() {
        return addyBook.getContactList().size();
    }

    //Override the getItem method
    @Override
    public BaseContact getItem(int position) {
        return addyBook.getContactList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //method to send back a view, a collection of properties used in the one contact in the lsit
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View oneContactLine;

        //Inflator allows us to used to create a new View Object from the xml Layout
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        oneContactLine = inflater.inflate(R.layout.onecontactinlist, parent, false);

        //get the references from the xml to get the views
        TextView tv_FirstName = oneContactLine.findViewById(R.id.tv_FirstName);
        TextView tv_LastName = oneContactLine.findViewById(R.id.tv_LastName);
        ImageView iv_ContactPicture = oneContactLine.findViewById(R.id.iv_ContactPicture);

        //make a new contact to extract information from
        BaseContact contact = this.getItem(position);
        //Assign values to the views
        tv_FirstName.setText(contact.getFirstName());
        tv_LastName.setText(contact.getlastName());
        int icon_resource_numbers [] = {
                R.drawable.icon01_01,
                R.drawable.icon01_02,
                R.drawable.icon01_03,
                R.drawable.icon01_04,
                R.drawable.icon01_05,
                R.drawable.icon01_06,
                R.drawable.icon01_07,
                R.drawable.icon01_08,
                R.drawable.icon01_09,
                R.drawable.icon01_10,
                R.drawable.icon01_11,
                R.drawable.icon01_12,
                R.drawable.icon01_13,
                R.drawable.icon01_14,
                R.drawable.icon01_15,
                R.drawable.icon01_16,
                R.drawable.icon01_17,
                R.drawable.icon01_18,
                R.drawable.icon01_19,
                R.drawable.icon01_20,
                R.drawable.icon01_21,
                R.drawable.icon01_22,
                R.drawable.icon01_23,
                R.drawable.icon01_24,
                R.drawable.icon01_25,
                R.drawable.icon01_26,
                R.drawable.icon01_27,
                R.drawable.icon01_28,
                R.drawable.icon01_29,
                R.drawable.icon01_30,
        };
        iv_ContactPicture.setImageResource(icon_resource_numbers[contact.getPhotoNum()]);
        return oneContactLine;
    }
}
