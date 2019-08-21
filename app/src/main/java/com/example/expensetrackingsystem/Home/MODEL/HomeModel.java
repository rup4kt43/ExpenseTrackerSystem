package com.example.expensetrackingsystem.Home.MODEL;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.expensetrackingsystem.Utilities.Global;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class HomeModel {
    public void retriveUserDetail() {
        DatabaseReference userDetailRef = Global.mDatabase.child("USER DETAIL");

        userDetailRef.addValueEventListener(new ValueEventListener() {  //for looping
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot phone : dataSnapshot.getChildren()) {       //children --> phone number
                    String num = phone.getKey();    //get phone number
                    for (DataSnapshot detail : phone.getChildren()) {   //get detail
                        if (detail.getKey().matches("userEmail")) {
                            String email = (String) detail.getValue();
                            if (email.matches(Global.userEmail)) {
                                Global.userPhone = num;       // setting the phone number to global vairable userphone

                            }
                        }
                        if (detail.getKey().matches("userName")) {
                            String name = (String) detail.getValue();
                             Global.userName = name;




                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
