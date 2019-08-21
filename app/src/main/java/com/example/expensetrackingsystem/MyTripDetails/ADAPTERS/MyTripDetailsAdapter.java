package com.example.expensetrackingsystem.MyTripDetails.ADAPTERS;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackingsystem.MyTripDetails.DTO.MyTripDetailsDTO;
import com.example.expensetrackingsystem.MyTripDetails.VIEW.MyTripDetailsView;
import com.example.expensetrackingsystem.R;

import java.util.ArrayList;

public class MyTripDetailsAdapter extends RecyclerView.Adapter {
    private final MyTripDetailsView context;
    private final ArrayList<MyTripDetailsDTO> list;
    private TextView name, phone;

    public MyTripDetailsAdapter(MyTripDetailsView myTripDetailsView, ArrayList<MyTripDetailsDTO> memberList) {
        this.context = myTripDetailsView;
        this.list = memberList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_memberlist_containeer, parent, false);
        return new MyTripDetailsCustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        name.setText(list.get(position).getPersonName());
        phone.setText(list.get(position).getPersonPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class MyTripDetailsCustomHolder extends RecyclerView.ViewHolder {
        public MyTripDetailsCustomHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_rv_username);
            phone = view.findViewById(R.id.tv_rv_phone);

        }
    }
}
