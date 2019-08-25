package com.example.expensetrackingsystem.MyTrip.ADAPTER;

import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackingsystem.MyTrip.DTO.MemberDetailsDTO;
import com.example.expensetrackingsystem.MyTrip.DTO.MyTripDTO;
import com.example.expensetrackingsystem.MyTrip.VIEW.MyTripView;
import com.example.expensetrackingsystem.MyTripDetails.VIEW.MyTripDetailsView;
import com.example.expensetrackingsystem.R;
import com.example.expensetrackingsystem.Utilities.Global;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyTripCustomAdapter extends RecyclerView.Adapter {
    private final MyTripView context;
    private final ArrayList<MyTripDTO> myTripDetails;
    private TextView locFrom, locTo, DateFrom, DateTo;

    private CardView cv_details,cv_addMember;
    private ArrayList<MemberDetailsDTO> memberDetailsArray;


    public MyTripCustomAdapter(MyTripView myTripView, ArrayList<MyTripDTO> tripDetails) {
        this.context = myTripView;
        this.myTripDetails = tripDetails;
        memberDetailsArray = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mytrip_rv_containeer, parent, false);
        return new myTripCustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        locFrom.setText(String.valueOf(myTripDetails.get(position).getLocationFrom()));
        locTo.setText(String.valueOf(myTripDetails.get(position).getLocationTo()));
        DateFrom.setText(String.valueOf(myTripDetails.get(position).getFromDate()));
        DateTo.setText(String.valueOf(myTripDetails.get(position).getToDate()));

    }

    @Override
    public int getItemCount() {
        return myTripDetails.size();
    }

    private class myTripCustomHolder extends RecyclerView.ViewHolder {
        public myTripCustomHolder(View view) {
            super(view);
            locFrom = view.findViewById(R.id.tv_locationFrom);
            locTo = view.findViewById(R.id.tv_locationTo);
            DateFrom = view.findViewById(R.id.tv_dateFrom);
            DateTo = view.findViewById(R.id.tv_dateTo);
            cv_addMember = view.findViewById(R.id.cv_addMember);
            cv_addMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    createDialogueBox(pos);
                }
            });


            cv_details = view.findViewById(R.id.cv_details);
            cv_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String locFrom = myTripDetails.get(pos).getLocationFrom();
                    String locTo = myTripDetails.get(pos).getLocationTo();
                    String dateFrom = myTripDetails.get(pos).getFromDate();
                    String dateTo = myTripDetails.get(pos).getToDate();
                    String time = myTripDetails.get(pos).getTime();

                    context.switchToMyTripDetails(locFrom,locTo,dateFrom,dateTo,time);

                }
            });
           /* cv_addMember = view.findViewById(R.id.cv_addMember);
            cv_addMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    createDialogueBox(pos);

                }
            });*/


        }
    }

    private void createDialogueBox(final int pos) {

        final EditText personName, personPhone;
        Button btn_apply, btn_add, btn_cancel;

        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_member_custom_dialog);

        personName = dialog.findViewById(R.id.et_personName);
        personPhone = dialog.findViewById(R.id.et_personPhone);

        btn_add = dialog.findViewById(R.id.btn_add);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);


        //save file to database and retrive to set on recycler v iew

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = personName.getText().toString();
                String phone = personPhone.getText().toString();



                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference userDetail = Global.mDatabase.child("USER DETAIL").child(phone).child("Request");
                   userDetail.child(Global.userPhone).setValue(myTripDetails.get(pos).getTime());

                   /* MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO();
                    memberDetailsDTO.setPersonName(name);
                    memberDetailsDTO.setPersonPhone(phone);
                    memberDetailsArray.add(memberDetailsDTO);
                    personName.setText("");
                    personPhone.setText("");*/
                }
            }
        });


        btn_apply = dialog.findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!memberDetailsArray.isEmpty()) {
                    context.addMemberToMyTrip(myTripDetails.get(pos).getTime(),memberDetailsArray);
                    dialog.dismiss();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        Window dialogWindow = dialog.getWindow();
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = d.getWidth() * 1;

        dialog.show();
    }
}
