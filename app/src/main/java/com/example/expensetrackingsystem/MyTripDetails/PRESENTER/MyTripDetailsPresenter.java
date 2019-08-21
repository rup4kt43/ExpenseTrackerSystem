package com.example.expensetrackingsystem.MyTripDetails.PRESENTER;

import com.example.expensetrackingsystem.MyTripDetails.DTO.MyTripDetailsDTO;
import com.example.expensetrackingsystem.MyTripDetails.INTERFACES.MyTripDetailsInterfaces;
import com.example.expensetrackingsystem.MyTripDetails.MODEL.MyTripDetailsModel;
import com.example.expensetrackingsystem.MyTripDetails.VIEW.MyTripDetailsView;

import java.util.ArrayList;

public class MyTripDetailsPresenter implements MyTripDetailsInterfaces.presenter {
    private MyTripDetailsInterfaces.view view;
    private MyTripDetailsModel model;

    public MyTripDetailsPresenter(MyTripDetailsView myTripDetailsView) {
        this.view = myTripDetailsView;
        model = new MyTripDetailsModel();
    }

    @Override
    public void retriveMembers(String time) {
        model.retriveMembersList(time, new MyTripDetailsInterfaces.presenterModelCallback() {
            @Override
            public void memberList(ArrayList<MyTripDetailsDTO> memberList) {
                view.loadMemberList(memberList);
            }
        });
    }
}
