package com.example.expensetrackingsystem.MyTrip.PRESENTER;

import com.example.expensetrackingsystem.MyTrip.CONTRACTS.MyTripInterfaces;
import com.example.expensetrackingsystem.MyTrip.DTO.MemberDetailsDTO;
import com.example.expensetrackingsystem.MyTrip.DTO.MyTripDTO;
import com.example.expensetrackingsystem.MyTrip.MODEL.MyTripModel;
import com.example.expensetrackingsystem.MyTrip.VIEW.MyTripView;

import java.util.ArrayList;

public class MyTripPresenter implements MyTripInterfaces.presenter {
    private MyTripInterfaces.view view;
    private MyTripModel model;

    public MyTripPresenter(MyTripView myTripView) {
        this.view = myTripView;
        model = new MyTripModel();  //Initiating the model object to call its method through object

    }

    @Override
    public void retriveMyTripInfo() {
        model.retriveMyTripInfo(new MyTripInterfaces.presenterModelCallBack() {
            @Override
            public void tripDetails(ArrayList<MyTripDTO> myTripDetails) {
                view.loadMyTrip(myTripDetails);
            }
        });

    }

    @Override
    public void addMemberToMyTrip(String time, ArrayList<MemberDetailsDTO> memberDetailsArray) {
        model.addMemberToMyTrip(time,memberDetailsArray);
    }
}
