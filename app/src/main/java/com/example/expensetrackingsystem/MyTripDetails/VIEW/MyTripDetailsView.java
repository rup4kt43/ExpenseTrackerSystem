package com.example.expensetrackingsystem.MyTripDetails.VIEW;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackingsystem.MyTripDetails.ADAPTERS.MyTripDetailsAdapter;
import com.example.expensetrackingsystem.MyTripDetails.DTO.MyTripDetailsDTO;
import com.example.expensetrackingsystem.MyTripDetails.INTERFACES.MyTripDetailsInterfaces;
import com.example.expensetrackingsystem.MyTripDetails.PRESENTER.MyTripDetailsPresenter;
import com.example.expensetrackingsystem.R;
import com.example.expensetrackingsystem.Utilities.Global;

import java.util.ArrayList;

public class MyTripDetailsView extends AppCompatActivity implements MyTripDetailsInterfaces.view {

    private TextView tv_dateFrom, tv_dateTo, tv_locFrom, tv_locTo, tv_self_name, tv_memCount;
    private MyTripDetailsPresenter presenter;
    private RecyclerView recyclerView;
    LinearLayout emptyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip_details_view);

        getSupportActionBar().setTitle("My Trip Details");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        emptyLayout = findViewById(R.id.ll_empty);

        //Initiating the object of presenter to call its method
        presenter = new MyTripDetailsPresenter(this);
        recyclerView = findViewById(R.id.rv_myTripDetails);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        Intent i = getIntent();
        String locFrom = i.getStringExtra("locFrom");
        String locTo = i.getStringExtra("locTo");
        String dateFrom = i.getStringExtra("dateFrom");
        String dateTo = i.getStringExtra("dateTo");
        String time = i.getStringExtra("time");

        tv_dateFrom = findViewById(R.id.tv_dateFrom);
        tv_dateTo = findViewById(R.id.tv_dateTo);
        tv_locFrom = findViewById(R.id.tv_locationFrom);
        tv_locTo = findViewById(R.id.tv_locationTo);
        tv_self_name = findViewById(R.id.tv_self_name);

        tv_dateFrom.setText(dateFrom);
        tv_dateTo.setText(dateTo);
        tv_locFrom.setText(locFrom);
        tv_locTo.setText(locTo);
        tv_self_name.setText(Global.userName);

        tv_memCount = findViewById(R.id.tv_memCount);

        presenter.retriveMembers(time);


    }

    @Override
    public void loadMemberList(ArrayList<MyTripDetailsDTO> memberList) {
        tv_memCount.setText(String.valueOf(memberList.size() + 1));

        if (!memberList.isEmpty()) {
            recyclerView.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.GONE);


            MyTripDetailsAdapter myTripDetailsAdapter = new MyTripDetailsAdapter(this, memberList);
            recyclerView.setAdapter(myTripDetailsAdapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            MyTripDetailsView.this.finish();
        }
        return true;
    }
}
