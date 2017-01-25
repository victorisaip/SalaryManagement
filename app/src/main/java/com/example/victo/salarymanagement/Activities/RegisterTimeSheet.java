package com.example.victo.salarymanagement.Activities;


import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.victo.salarymanagement.Fragments.RegisterTimesheetFragment;
import com.example.victo.salarymanagement.Interfaces.IComm;
import com.example.victo.salarymanagement.R;

public class RegisterTimeSheet extends AppCompatActivity implements IComm{

    Toolbar toolbar;
    static final String TAG = "Register Timesheet";
    RegisterTimesheetFragment registerTimesheetFragment;
    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_time_sheet);

        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);

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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.action_logout:
//                break;
//            case R.id.action_help:
//
//                break;
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
