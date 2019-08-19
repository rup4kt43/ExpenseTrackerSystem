package com.example.expensetrackingsystem.Utilities;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Global {

    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


}
