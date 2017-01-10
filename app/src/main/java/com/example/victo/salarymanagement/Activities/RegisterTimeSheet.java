package com.example.victo.salarymanagement.Activities;


import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victo.salarymanagement.Fragments.DatePickerFragment;
import com.example.victo.salarymanagement.Fragments.RegisterTimesheetFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsFragment;
import com.example.victo.salarymanagement.R;

import java.util.Calendar;
import java.util.Date;

public class RegisterTimeSheet extends AppCompatActivity {
    static final String TAG = "Register Timesheet";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    RegisterTimesheetFragment registerTimesheetFragment;
    TimesheetsFragment timesheetsFragment;
    FragmentManager fm;
    FragmentTransaction ft;
    Button btnShowDialog;
    EditText startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Setting content ");
        setContentView(R.layout.activity_register_time_sheet);
        startDate = (EditText) findViewById(R.id.etStartDate);


        Log.d(TAG, "onCreate: "+"Dialog completed");

        registerTimesheetFragment = new RegisterTimesheetFragment();
        timesheetsFragment = new TimesheetsFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.add(R.id.frRegisterTimeSheet,registerTimesheetFragment);
        ft.commit();

        findViewById(R.id.btnTypeDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                showDatePicker();
            }
        });

    }

    public void showDatePicker(){
        FragmentManager manager = getSupportFragmentManager();
        Date date = new Date();
        DatePickerFragment dialog = DatePickerFragment.newInstance(date);
        dialog.show(manager, DIALOG_DATE);

    }

}
