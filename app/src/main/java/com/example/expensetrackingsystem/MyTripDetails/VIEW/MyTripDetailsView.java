package com.example.expensetrackingsystem.MyTripDetails.VIEW;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetrackingsystem.MyTripDetails.ADAPTERS.MyExpenseDialogAdapter;
import com.example.expensetrackingsystem.MyTripDetails.ADAPTERS.MyTripDetailsAdapter;
import com.example.expensetrackingsystem.MyTripDetails.DTO.ExpensesDTO;
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
    ArrayList<ExpensesDTO> addExpenseArray;
    String time;
    private ArrayList<ExpensesDTO> expenseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip_details_view);

        //Arraylist to save new expense on add listener
        addExpenseArray = new ArrayList<>();

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
        time = i.getStringExtra("time");

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

        retriveMyExpense();


    }

    private void retriveMyExpense() {

        presenter.retriveMyExpense(time);
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
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMyExpenses(ArrayList<ExpensesDTO> myExpense) {
        this.expenseArray = myExpense;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                MyTripDetailsView.this.finish();
                break;
            case R.id.action_add_expense:
                addExpenseArray.clear();
                createAddNewExpenseCustomDialog();
                break;
            case R.id.action_my_expense:
                Toast.makeText(this, "my epense clicked", Toast.LENGTH_SHORT).show();
                createMyExpenseCustomDialog();
                break;
            default:
                return true;
        }
        return true;
    }

    private void createMyExpenseCustomDialog() {
        double total_price = 0.0;

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.myexpense_custom_dialog);

        //Refrencing the widgets
        ImageView cross = dialog.findViewById(R.id.iv_cross);
        RecyclerView recyclerView = dialog.findViewById(R.id.dialog_rv_my_expense);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView total = dialog.findViewById(R.id.tv_total);


        MyExpenseDialogAdapter myExpenseDialogAdapter = new MyExpenseDialogAdapter(this, expenseArray);
        if (!expenseArray.isEmpty()) {

            for (int i = 0; i < expenseArray.size(); i++) {
                double price = Double.parseDouble(expenseArray.get(i).getExpenseAmount());
                total_price = total_price + price;
            }

            total.setText(String.valueOf(total_price));
            recyclerView.setAdapter(myExpenseDialogAdapter);
        }

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = d.getWidth() * 1;
        /*lp.height = (int) (d.getHeight() * 0.7);*/

        dialog.show();
    }


    //dialog for add new expense
    private void createAddNewExpenseCustomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.addexpense_custom_dialog);

        //Refrencing button widgets
        final EditText et_expense_name, et_expense_amount;
        Button btn_add, btn_apply, btn_cancel;
        et_expense_name = dialog.findViewById(R.id.et_expense_name);
        et_expense_amount = dialog.findViewById(R.id.et_expense_amount);
        btn_add = dialog.findViewById(R.id.btn_add);
        btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_apply = dialog.findViewById(R.id.btn_apply);


        //Widge Action Performed .i.e onClicked
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String expenseName = et_expense_name.getText().toString();
                String expenseAmount = et_expense_amount.getText().toString();
                ExpensesDTO expensesDTO = presenter.addNewExpense(expenseName, expenseAmount);
                et_expense_amount.setText("");
                et_expense_name.setText("");
                addExpenseArray.add(expensesDTO);
            }
        });

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!addExpenseArray.isEmpty()) {        //checking whether if the array is empty or not

                    //if not empty saving it in database
                    presenter.saveNewExpenseToFirebase(addExpenseArray, time);
                    retriveMyExpense();
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
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = d.getWidth() * 1;

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mytripdetails_menu, menu);
        return true;
    }
}
