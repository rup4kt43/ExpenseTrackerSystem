package com.example.expensetrackingsystem.MyTrip.MODEL;

import androidx.annotation.NonNull;

import com.example.expensetrackingsystem.MyTrip.CONTRACTS.MyTripInterfaces;
import com.example.expensetrackingsystem.MyTrip.DTO.MemberDetailsDTO;
import com.example.expensetrackingsystem.MyTrip.DTO.MyTripDTO;
import com.example.expensetrackingsystem.Utilities.Global;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyTripModel {
    public void retriveMyTripInfo(final MyTripInterfaces.presenterModelCallBack callBack) {
        DatabaseReference tripList = Global.mDatabase.child("TRIP LIST");
        tripList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot keyPhone : dataSnapshot.getChildren()) {
                    String num = keyPhone.getKey();
                    if (Global.userPhone.matches(num)) {
                        ArrayList<MyTripDTO> arrayList = new ArrayList<>();
                        for (DataSnapshot time : keyPhone.getChildren()) {

                            try {
                                MyTripDTO myTripDTO = new MyTripDTO();      //creating new object of myTripDto
                                myTripDTO.setFromDate(time.child("From Date").getValue().toString());   //invoking getter and setter
                                myTripDTO.setLocationFrom(time.child("Location From").getValue().toString());
                                myTripDTO.setToDate(time.child("To Date").getValue().toString());
                                myTripDTO.setTime(time.getKey().toString());
                                myTripDTO.setLocationTo(time.child("Location To").getValue().toString());
                                arrayList.add(myTripDTO);       //ADDING mytrip dto object which has set properties into arraylist
                                callBack.tripDetails(arrayList);//sending the arraylist to presenter through callback
                            } catch (Exception e) {
                                e.printStackTrace();
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

    public void addMemberToMyTrip(String time, ArrayList<MemberDetailsDTO> memberDetailsArray) {
        DatabaseReference myTripReference = Global.mDatabase.child("TRIP LIST").child(Global.userPhone).child(time).child("Members");
        for (int i = 0; i < memberDetailsArray.size(); i++) {
            myTripReference.child(memberDetailsArray.get(i).getPersonName()).setValue(memberDetailsArray.get(i).getPersonPhone());
        }
    }
}
