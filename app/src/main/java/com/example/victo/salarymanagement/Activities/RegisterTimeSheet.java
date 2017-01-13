package com.example.victo.salarymanagement.Activities;


import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.victo.salarymanagement.Fragments.RegisterTimesheetFragment;
import com.example.victo.salarymanagement.Interfaces.IComm;
import com.example.victo.salarymanagement.R;

public class RegisterTimeSheet extends AppCompatActivity implements IComm{
    static final String TAG = "Register Timesheet";
    RegisterTimesheetFragment registerTimesheetFragment;
    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_time_sheet);
        registerTimesheetFragment = new RegisterTimesheetFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.frRegisterTimeSheet,registerTimesheetFragment);
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
