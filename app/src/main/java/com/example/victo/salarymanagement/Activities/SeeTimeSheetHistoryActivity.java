package com.example.victo.salarymanagement.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.victo.salarymanagement.Fragments.DetailTimeSheetFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsFragment;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.R;


public class SeeTimeSheetHistoryActivity extends AppCompatActivity implements TimesheetComm {
    private static final String TAG = "STATUS";
    private static final String TAG2 = "SeeActivity";

    FragmentManager fm;
    FragmentTransaction ft;
    TimesheetsFragment timeSheetsFragment;
    DetailTimeSheetFragment detailTimeSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_time_sheet_history);
        timeSheetsFragment = new TimesheetsFragment();
        detailTimeSheetFragment = new DetailTimeSheetFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.frTimeSheets, timeSheetsFragment);
        ft.add(R.id.frDetailTimeSheet, detailTimeSheetFragment);
        ft.commit();
    }

    @Override
    public void setTextToTimeSheet(String text) {
        detailTimeSheetFragment.setMyTextViewTo(text);
    }
}
