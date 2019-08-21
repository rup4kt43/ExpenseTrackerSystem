package com.example.expensetrackingsystem.MyTripDetails.MODEL;

import androidx.annotation.NonNull;

import com.example.expensetrackingsystem.MyTripDetails.DTO.MyTripDetailsDTO;
import com.example.expensetrackingsystem.MyTripDetails.INTERFACES.MyTripDetailsInterfaces;
import com.example.expensetrackingsystem.Utilities.Global;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyTripDetailsModel {
    public void retriveMembersList(String time, final MyTripDetailsInterfaces.presenterModelCallback callback) {
        final ArrayList<MyTripDetailsDTO> memberList = new ArrayList<>();
        DatabaseReference members = Global.mDatabase.child("TRIP LIST").child(Global.userPhone).child(time).child("Members");
        members.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot details : dataSnapshot.getChildren()) {
                    MyTripDetailsDTO myTripDetailsDTO = new MyTripDetailsDTO();
                    String name = details.getKey();
                    String phone = (String) details.getValue();

                    myTripDetailsDTO.setPersonName(name);
                    myTripDetailsDTO.setPersonPhone(phone);

                    memberList.add(myTripDetailsDTO);

                }
                callback.memberList(memberList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
