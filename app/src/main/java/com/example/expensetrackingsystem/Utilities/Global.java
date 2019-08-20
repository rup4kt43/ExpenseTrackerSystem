package com.example.expensetrackingsystem.Utilities;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Global {

    public static String userEmail;
    public static String userPhone;
    public static int createTripState ;  // 0 for succusful create 1 for failed to create
    //Creating creatTripState static variable because to stop activity from getting destroy if create trip fails deu to validation
    // and destroying the activity and go back to home if trip create successfully

    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


}
