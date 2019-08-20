package com.example.expensetrackingsystem.PlanATrip.MODEL;

import com.example.expensetrackingsystem.Utilities.Global;
import com.google.firebase.database.DatabaseReference;

public class PlanATripModel {
    public void createATrip(String starting, String destination, String startDate, String endDate) {
        DatabaseReference tripCreated = Global.mDatabase.child("TRIP LIST"); // refrencing node TRIP LIST
        DatabaseReference number = tripCreated.child(Global.userPhone);     // Adding node according to number

        DatabaseReference startingLocation = number.child("Location From");          //child node of respective number
        startingLocation.setValue(starting);        //saving the value to Location from node

        DatabaseReference destinationLocation = number.child("Location To");         //child node of respective number
        destinationLocation.setValue(destination);      //saving the value to Location To node

        DatabaseReference fromDate = number.child("From Date");         // child node of respective number
        fromDate.setValue(startDate);   //setting the value to From Date node

        DatabaseReference toDate = number.child("To Date");         //child node of respective number
        toDate.setValue(endDate);       // saving the value to To date node


    }
}
