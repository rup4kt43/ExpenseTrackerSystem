package com.example.expensetrackingsystem.Home.PRESENTER;

import com.example.expensetrackingsystem.Home.INTERFACES.HomeInterface;
import com.example.expensetrackingsystem.Home.MODEL.HomeModel;
import com.example.expensetrackingsystem.Home.VIEW.HomeView;

public class HomePresenter implements HomeInterface.presenter {
    HomeInterface.view view;
    HomeModel model;
    public HomePresenter(HomeView homeView) {
        this.view = homeView;
        model = new HomeModel();
    }

    @Override
    public void retriveUserDetail() {
        model.retriveUserDetail();
    }
}
