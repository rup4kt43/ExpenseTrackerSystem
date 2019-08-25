package com.example.expensetrackingsystem.Utilities;

import com.example.expensetrackingsystem.TripRequest.DTO.TripRequestDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Global {

    public static String userEmail;
    public static String userName;
    public static String userPhone;
    public static int createTripState ;  // 0 for succusful create 1 for failed to create
    //Creating creatTripState static variable because to stop activity from getting destroy if create trip fails deu to validation
    // and destroying the activity and go back to home if trip create successfully

    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public static ArrayList<TripRequestDTO> tripReqArray = new ArrayList<>();


}
