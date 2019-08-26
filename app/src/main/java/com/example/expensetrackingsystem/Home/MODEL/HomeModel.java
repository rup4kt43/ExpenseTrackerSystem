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
                    Log.e("phojne",num);
                    for (DataSnapshot detail : phone.getChildren()) {  //get detail


                        if (detail.getKey().matches("userEmail")) {
                            String email = (String) detail.getValue();
                            if (email.matches(Global.userEmail)) {
                                Global.userPhone = num;       // setting the phone number to global vairable userphone

                                DatabaseReference databaseReference = Global.mDatabase.child("USER DETAIL").child(Global.userPhone);
                                databaseReference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot detail:dataSnapshot.getChildren()){
                                            if(detail.getKey().matches("userName")){
                                                Global.userName = detail.getValue().toString();

                                                Log.e("Username",Global.userName);
                                                Log.e("Username",Global.userName);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
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
