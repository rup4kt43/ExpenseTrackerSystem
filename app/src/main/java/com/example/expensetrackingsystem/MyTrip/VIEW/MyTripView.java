package com.example.expensetrackingsystem.MyTrip.VIEW;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackingsystem.MyTrip.ADAPTER.MyTripCustomAdapter;
import com.example.expensetrackingsystem.MyTrip.CONTRACTS.MyTripInterfaces;
import com.example.expensetrackingsystem.MyTrip.DTO.MemberDetailsDTO;
import com.example.expensetrackingsystem.MyTrip.DTO.MyTripDTO;
import com.example.expensetrackingsystem.MyTrip.PRESENTER.MyTripPresenter;
import com.example.expensetrackingsystem.MyTripDetails.VIEW.MyTripDetailsView;
import com.example.expensetrackingsystem.R;

import java.util.ArrayList;

public class MyTripView extends AppCompatActivity implements MyTripInterfaces.view {

    MyTripPresenter presenter;
    RecyclerView recyclerView;
    MyTripCustomAdapter myTripCustomAdapter;
    LinearLayout emptyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrip);

        getSupportActionBar().setTitle("My Trip");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        emptyLayout = findViewById(R.id.ll_empty);


        //Initiating the presenter object to call presenter method through its object
        presenter = new MyTripPresenter(this);

        //Retriving myTrip info that is Date from, Date to , start location and destination from firebase
        presenter.retriveMyTripInfo();

        //Refrencing widgets
        recyclerView = findViewById(R.id.rv_myTrip);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void loadMyTrip(ArrayList<MyTripDTO> tripDetails) {
        if (!tripDetails.isEmpty()) {
            emptyLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            myTripCustomAdapter = new MyTripCustomAdapter(this, tripDetails);
            setAdapter();
        } else {
            emptyLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }


    }

    @Override
    public void addMemberToMyTrip(String time, ArrayList<MemberDetailsDTO> memberDetailsArray) {


        presenter.addMemberToMyTrip(time, memberDetailsArray);
    }

    private void setAdapter() {
        recyclerView.setAdapter(myTripCustomAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            MyTripView.this.finish();
        }
        return true;
    }

    public void switchToMyTripDetails(String locFrom, String locTo, String dateFrom, String dateTo, String time) {
        Intent i = new Intent(MyTripView.this, MyTripDetailsView.class);
        i.putExtra("locFrom", locFrom);
        i.putExtra("locTo", locTo);
        i.putExtra("dateFrom", dateFrom);
        i.putExtra("dateTo", dateTo);
        i.putExtra("time", time);

        startActivity(i);
    }
}
