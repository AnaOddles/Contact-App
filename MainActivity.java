package com.example.contactapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Person;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contactapp.Base.BaseContact;
import com.example.contactapp.DataAccess.FileAccessService;
import com.example.contactapp.Models.Address;
import com.example.contactapp.Models.AddressBook;
import com.example.contactapp.Models.BusinessContact;
import com.example.contactapp.Models.PersonContact;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declare our views
    Button btn_AddBus, btn_AddPer, btn_SearchContact;
    ListView lv_Contacts;
    EditText et_SearchContact;

    //Declare a address book adapter
    AddressBookAdapter adapter;

    //Declare an address book object
    static AddressBook addyBook;

    //Declare a data access object
    static FileAccessService FAS;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create an instance of needed objects and assign them
        addyBook = new AddressBook();
        FAS = new FileAccessService(this);

        //Load in the contacts
        addyBook= FAS.loadRecords("Contacts4.txt", addyBook);

        //Add a new contact
        //PersonContact pc = new PersonContact();
        //addyBook.addOne(pc);
        //FAS.saveRecords(addyBook, "Contacts2.txt");

        //Create an Address Book Adapter that has the updated contacts from in address book
        setContentView(R.layout.activity_main);

        //Assign the buttons and list view a value
        btn_AddBus = findViewById(R.id.btn_AddBus);
        btn_AddPer = findViewById(R.id.btn_AddPer);
        btn_SearchContact = findViewById(R.id.btn_SearchContact);
        lv_Contacts = findViewById(R.id.lv_Contacts);
        et_SearchContact = findViewById(R.id.et_SearchContact);
        adapter = new AddressBookAdapter(MainActivity.this, addyBook);

        //Set the adapter to the list view
        lv_Contacts.setAdapter(adapter);

        //listen for incoming messages
        Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages != null){
            String contactType = incomingMessages.getString("contactType");
            String searchingFor = incomingMessages.getString("searchingFor");
            if(searchingFor != null){
                String searchWith = incomingMessages.getString("searchWith");
                ArrayList<BaseContact> contactsFound = addyBook.firstNameSearch(searchWith);
                AddressBook addyBook2 = new AddressBook(contactsFound);
                adapter = new AddressBookAdapter(MainActivity.this, addyBook2);
                lv_Contacts.setAdapter(adapter);
            }
            else if((contactType != null ) && (contactType.equals("p"))) {
                //if we are making a person contact

                //capture incoming data
                String firstName = incomingMessages.getString("firstName");
                String lastName = incomingMessages.getString("lastName");
                String phoneNumber = incomingMessages.getString("phoneNumber");
                String DOB = incomingMessages.getString("DOB");
                String email = incomingMessages.getString("email");
                String street = incomingMessages.getString("street");
                String state = incomingMessages.getString("state");
                String city = incomingMessages.getString("city");
                int zipCode = Integer.parseInt(incomingMessages.getString("zipCode"));
                String country = incomingMessages.getString("country");
                String description = incomingMessages.getString("description");
                int positionEdited = incomingMessages.getInt("edit");

                //create a new person contact object with address
                Address addy = new Address(000, street, city, state, zipCode, country);
                PersonContact pc = new PersonContact(firstName, lastName, phoneNumber, addy, null, email, DOB,
                        description, null);
                pc.setAddress(addy);

                //add person contact to the list and update adapter
                //also add into the file

                //if the contact is being edited, we will remove previous contact to
                //remove duplicate
                if(positionEdited > -1){
                    addyBook.getContactList().remove(positionEdited);
                }
                addyBook.addOne(pc);
                FAS.saveRecords(addyBook, "Contacts4.txt");
                //update the adapter
                adapter.notifyDataSetChanged();
            }
            //Business contact
            else if ((contactType.equals("b")) && (contactType != null)) {
                //if we are making a business contact

                //capture incoming data
                String firstName = incomingMessages.getString("firstName");
                String lastName = incomingMessages.getString("lastName");
                String phoneNumber = incomingMessages.getString("phoneNumber");
                String email = incomingMessages.getString("email");
                String street = incomingMessages.getString("street");
                String city = incomingMessages.getString("city");
                String state = incomingMessages.getString("state");
                int zipCode = Integer.parseInt(incomingMessages.getString("zipCode"));
                String country = incomingMessages.getString("country");
                String openingTime = incomingMessages.getString("openingTime");
                String closingTime = incomingMessages.getString("closingTime");
                String daysOpen = incomingMessages.getString("daysOpen");
                String website = incomingMessages.getString("website");
                int positionEdited = incomingMessages.getInt("edit");


                //create a new business contact object with address
                Address addy = new Address(000, street, city, state, zipCode, country);
                BusinessContact bc = new BusinessContact(firstName, lastName, phoneNumber, addy, null, email,
                        openingTime, closingTime, daysOpen, website);
                bc.setAddress(addy);

                //add business contact to the list and update adapter
                //also add into the file
                //if the contact is being edited, we will remove previous contact to
                //remove duplicate
                if(positionEdited > -1){
                    addyBook.getContactList().remove(positionEdited);
                }
                addyBook.addOne(bc);
                FAS.saveRecords(addyBook, "Contacts4.txt");
                //update the adapter
                adapter.notifyDataSetChanged();

            }
            else{

            }


        }
        //Click listener for the business contact
        btn_AddBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open the add business activity
                //Create an intent which is a class that is used to open another activity and other apps
                Intent i = new Intent(v.getContext(), AddNewBusiness.class);
                startActivity(i);
            }
        });

        //Click listener for the person contact
        btn_AddPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open the add person activity
                //Create an intent which is a class that is used to open another activity and other apps
                Intent i = new Intent(v.getContext(), AddNewPerson.class);
                startActivity(i);
            }
        });

        //Click listener for when user clicks on person
        lv_Contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editContact(position);


            }
        });

        //Click listener for when user searches for user
        btn_SearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                String searchWith = et_SearchContact.getText().toString();
                i.putExtra("searchingFor", "y");
                i.putExtra("searchWith", searchWith);

                startActivity(i);
            }
        });


    }

    //Method to edit contact
    public void editContact(int position){
        //Get the class type that is going to be edited
        BaseContact contact = addyBook.getContactList().get(position);

        //if the contact is a person contact
        if(contact instanceof PersonContact ){
            Intent i = new Intent(getApplicationContext(), AddNewPerson.class);
            //put strings into the message for activity that takes contact properties
            i.putExtra("edit", position);
            i.putExtra("firstName", contact.getFirstName());
            i.putExtra("lastName", contact.getlastName());
            i.putExtra("phoneNumber", contact.getPhoneNumber());
            i.putExtra("DOB", ((PersonContact) contact).getDateOfBirth());
            i.putExtra("email", contact.getEmail());
            i.putExtra("street", contact.getAddress().getStreet());
            i.putExtra("city", contact.getAddress().getCity());
            i.putExtra("state", contact.getAddress().getState());
            i.putExtra("zipCode", contact.getAddress().getZipCode());
            i.putExtra("country", contact.getAddress().getCountry());
            i.putExtra("description", ((PersonContact) contact).getDescription());

            startActivity(i);
        }
        //if the contact is a business contact
        else{
            Intent i = new Intent (getApplicationContext(), AddNewBusiness.class);

            //put strings into the message for activity that takes contact properties
            i.putExtra("edit", position);
            i.putExtra("firstName", contact.getFirstName());
            i.putExtra("lastName", contact.getlastName());
            i.putExtra("phoneNumber", contact.getPhoneNumber());
            i.putExtra("email", contact.getEmail());
            i.putExtra("street", contact.getAddress().getStreet());
            i.putExtra("city", contact.getAddress().getStreet());
            i.putExtra("state", contact.getAddress().getState());
            i.putExtra("zipCode", contact.getAddress().getZipCode());
            i.putExtra("country", contact.getAddress().getCountry());
            i.putExtra("openingTime", ((BusinessContact)contact).getOpening());
            i.putExtra("closingTime", ((BusinessContact) contact).getClosing());
            i.putExtra("daysOpen", ((BusinessContact) contact).getDaysAWeekOpen());
            i.putExtra("website", ((BusinessContact) contact).getWebsiteURL());

            startActivity(i);
        }

    }

    public static void deleteContact(int positionToEdit){
        //Get the class type that is going to be edited
        BaseContact contact = addyBook.getContactList().get(positionToEdit);
        addyBook.deleteOne(contact);
        FAS.saveRecords(addyBook, "Contacts4.txt");

    }


}
