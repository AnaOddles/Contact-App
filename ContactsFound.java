package com.example.contactapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactapp.Base.BaseContact;

import java.util.ArrayList;

public class ContactsFound extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    //listen for incoming messages
    Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages !=null) {
            //ArrayList<BaseContact> contacts = incomingMessages.getParcelableArrayList("contactType");


        }
    }
}