package com.example.victo.salarymanagement.Activities;


import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.victo.salarymanagement.Fragments.DatePickerFragment;
import com.example.victo.salarymanagement.Fragments.RegisterTimesheetFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsFragment;
import com.example.victo.salarymanagement.Interfaces.IComm;
import com.example.victo.salarymanagement.R;

import java.util.Calendar;
import java.util.Date;

public class RegisterTimeSheet extends AppCompatActivity implements IComm{
    static final String TAG = "Register Timesheet";
    RegisterTimesheetFragment registerTimesheetFragment;
    TimesheetsFragment timesheetsFragment;
    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_time_sheet);
        registerTimesheetFragment = new RegisterTimesheetFragment();
        timesheetsFragment = new TimesheetsFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.frRegisterTimeSheet,registerTimesheetFragment);
        ft.add(R.id.frTimeSheets,timesheetsFragment);
        ft.commit();
    }
    @Override
    public void setTextTo(String s) {
        registerTimesheetFragment.setStartDate(s);
    }

    @Override
    public void setEndDate(String s) {
        registerTimesheetFragment.setEtEndDate(s);
    }
}
