# Contact-App
Android app that manages business and personal contacts, allows functionality for deleting, edited, and searching for contacts.

General Technical Approach: Contact Management Methods
This Milestone focused on implementing User Stories Part 2: Utilize communication features of Android 

Propose of Program:
	The purpose of the programs implemented in this Milestone is to allow the user to manage a list of contacts by allowing the user to create business and personal contacts. Both contact types can be deleted, edited, and searched for.  This is done by expanding on the MainActivity script and implementing more activities. This milestone also implements the objects and script that we had written in previous milestones. Like those previous milestones the main structure that holds and manages our contacts is the AddressBook object. This object is also used in this milestone to create a custom list adapter which connects our address book to the list view. 
	In this milestone we added the ability to community with the contact via android service. Throughout this milestone we are using Implicit intents meaning we are allowing the Android operating system to choose what program to use for our services. For example we give the intent a phone number and call the ACTION_CALL argument for the parameter of the intent and the operating system knows to open the phone app on our phones. 

Current Functionality:
Major decisions made and for what reasons 

1-	Using JSON- It is way easier and simpler to read and write from a json file because we do not need to set up our separators. We create a file in ther internal storage of the app that can we read from and load contacts from instantly. 
2-	Using annotations- Using annotations in JSON is required because we want to enable the complier to understand the different classes we are going to write and read 
3-	Using the file access service class- because we set up our menu system really organized, we can easily include the fileAccessService class methods into the program with no confusion. 
4-	Creating a custom AddressBookAdapter- in order to allow our contact object to reach the list view we created a custom list view adapter that allows us to pass the specific contact position in the address book to the list view and later to view its properties such as names, etc.  

Using JSON enables us export and import the file easily from a database using MySQL or another server-side language. 
