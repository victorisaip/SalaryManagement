package com.example.victo.salarymanagement.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.victo.salarymanagement.Fragments.TimesheetDetailToApproveFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsToApproveFragment;
import com.example.victo.salarymanagement.Interfaces.TimesheetApproveIcomm;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.R;

public class ApproveTimesheetsActivity extends AppCompatActivity implements
        TimesheetApproveIcomm{
    FragmentManager fm;
    FragmentTransaction ft;
    TimesheetsToApproveFragment timeSheetsFragment;
    TimesheetDetailToApproveFragment timesheetDetailToApproveFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_timesheets);
        timeSheetsFragment = new TimesheetsToApproveFragment();
        timesheetDetailToApproveFragment = new TimesheetDetailToApproveFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fr_timesheets_to_approve, timeSheetsFragment);
        ft.add(R.id.fr_detail_timesheet_to_approve,timesheetDetailToApproveFragment);
        ft.commit();
    }

    @Override
    public void setTextToTimesheet(String startDate,
                                   String endDate,
                                   String approver,
                                   String status,
                                   String monday,
                                   String tuesday,
                                   String wednesday,
                                   String thursday,
                                   String friday,
                                   String totalHours,
                                   String email,
                                   String actualDate) {
        timesheetDetailToApproveFragment.setText(startDate,
                endDate,
                approver,
                status,
                monday,
                tuesday,
                wednesday,
                thursday,
                friday,
                totalHours,
                email,
                actualDate);
    }
}
