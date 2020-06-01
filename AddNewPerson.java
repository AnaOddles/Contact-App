package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactapp.Models.AddressBook;

public class AddNewPerson extends AppCompatActivity {

    //Declare reference to the buttons and edit texts
    Button btn_CreatePerson, btn_CancelCreatePerson, btn_DeletePerson, btn_email, btn_text, btn_call, btn_maps;
    EditText et_FirstName, et_LastName, et_phoneNumber, et_DOB, et_Email, et_Street, et_City, et_State, et_ZipCode,
             et_Country, et_Description;
    TextView tv_Person;
    int positionToEdit  = -1;
    final static int PERMISSION_TO_CALL = 1;
    String callPhoneNumber ="";
    String contactEmail ="";
    String contactLocation= "";


    //Declare an address book object
    AddressBook addyBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewpersoncontact);

        //Set the reference to views by ids
        btn_CreatePerson  = findViewById(R.id.btn_DoneCreatePerson);
        btn_CancelCreatePerson = findViewById(R.id.btn_CancelCreatePerson);
        btn_DeletePerson = findViewById(R.id.btn_DeletePerson);
        btn_call = findViewById(R.id.btn_call);
        btn_email = findViewById(R.id.btn_email);
        btn_text = findViewById(R.id.btn_text);
        btn_maps = findViewById(R.id.btn_maps);
        et_FirstName = findViewById(R.id.et_FirstName);
        et_LastName = findViewById(R.id.et_LastName);
        et_phoneNumber = findViewById(R.id.et_phoneNumber2);
        et_DOB = findViewById(R.id.et_DOB);
        et_Email = findViewById(R.id.et_Email);
        et_Street = findViewById(R.id.et_Street);
        et_City = findViewById(R.id.et_City);
        et_State = findViewById(R.id.et_State);
        et_ZipCode = findViewById(R.id.et_ZipCode);
        et_Country = findViewById(R.id.et_Country);
        et_Description = findViewById(R.id.et_Description);
        tv_Person = findViewById(R.id.tv_Person);

        //listen for incoming data
        Bundle incomingMessages = getIntent().getExtras();
        if(incomingMessages != null){
            //capture incoming data
            String firstName = incomingMessages.getString("firstName");
            String lastName = incomingMessages.getString("lastName");
            String phoneNumber = incomingMessages.getString("phoneNumber");
            callPhoneNumber = incomingMessages.getString("phoneNumber");
            String DOB = incomingMessages.getString("DOB");
            String email = incomingMessages.getString("email");
            contactEmail = incomingMessages.getString("email");
            String street = incomingMessages.getString("street");
            String city = incomingMessages.getString("city");
            String state = incomingMessages.getString("state");
            contactLocation = incomingMessages.getString("street");
            int zipCode = incomingMessages.getInt("zipCode");
            String country = incomingMessages.getString("country");
            String description = incomingMessages.getString("description");
            positionToEdit = incomingMessages.getInt("edit");



            //fill in the form
            et_FirstName.setText(firstName);
            et_LastName.setText(lastName);
            et_phoneNumber.setText(phoneNumber);
            et_DOB.setText(DOB);
            et_Email.setText(email);
            et_Street.setText(street);
            et_City.setText(city);
            et_State.setText(state);
            et_ZipCode.setText(Integer.toString(zipCode));
            et_Country.setText(country);
            et_Description.setText(description);

        }
        //Click listener for create person button
        btn_CreatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get strings from the ets
                String firstName = et_FirstName.getText().toString();
                String lastName = et_LastName.getText().toString();
                String phoneNumber = et_phoneNumber.getText().toString();
                String DOB = et_DOB.getText().toString();
                String email = et_Email.getText().toString();
                String street = et_Street.getText().toString();
                String city = et_City.getText().toString();
                String state = et_State.getText().toString();
                String zipCode = et_ZipCode.getText().toString();
                String country= et_Country.getText().toString();
                String description = et_Description.getText().toString();
                String person = tv_Person.getText().toString();




                //start the main activity
                Intent i = new Intent(v.getContext(), MainActivity.class);

                //put strings into the message for main activity
                i.putExtra("edit", positionToEdit);
                i.putExtra("firstName", firstName);
                i.putExtra("phoneNumber", phoneNumber);
                i.putExtra("lastName", lastName);
                i.putExtra("DOB", DOB);
                i.putExtra("email", email);
                i.putExtra("street", street);
                i.putExtra("city", city);
                i.putExtra("state", state);
                i.putExtra("zipCode", zipCode);
                i.putExtra("country", country);
                i.putExtra("description", description);
                i.putExtra("contactType", person);

                //start the activity
                startActivity(i);
            }
        });
        //Click listener for cancel
        btn_CancelCreatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the intent for the main activity
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        //Click listener for deleting person contact
        btn_DeletePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.deleteContact(positionToEdit);
                Intent i = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

        //click listener for the call button
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber(callPhoneNumber);
            }
        });

        //click listener for the text button
        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(callPhoneNumber, "Hello I would like to talk");
            }
        });

        //Click listener for the email button
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] addresses = new String [1];
                addresses[0] = contactEmail;
                composeEmail(addresses, "Hello from Shad");
            }
        });

        //click listener for the maps button
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q=" + contactLocation;
                Uri mapsuri = Uri.parse(mapsQuery);
                showMap(mapsuri);
            }
        });
    }

    //Method to call a phone number
    public void callPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(AddNewPerson.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSION_TO_CALL);
        }
        else {
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_TO_CALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    callPhoneNumber(callPhoneNumber);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Cannot make a call without your permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    //Method for texting a number
    public void composeMmsMessage(String phoneNumber, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //Method for sending an email
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    //method for map
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
