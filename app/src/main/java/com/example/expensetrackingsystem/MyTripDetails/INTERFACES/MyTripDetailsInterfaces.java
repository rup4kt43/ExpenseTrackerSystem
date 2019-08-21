package com.example.expensetrackingsystem.MyTripDetails.INTERFACES;

import com.example.expensetrackingsystem.MyTripDetails.DTO.MyTripDetailsDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MyTripDetailsInterfaces {

    interface view {
        void loadMemberList(ArrayList<MyTripDetailsDTO> memberList);

    }

    interface presenter {

        void retriveMembers(String time);


    }

    interface  presenterModelCallback{
        void memberList(ArrayList<MyTripDetailsDTO> memberList);
    }


}
