package com.example.victo.salarymanagement.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.victo.salarymanagement.Fragments.TimesheetDetailToApproveFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsToApproveFragment;
import com.example.victo.salarymanagement.Interfaces.TimesheetApproveIcomm;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.R;

public class ApproveTimesheetsActivity extends AppCompatActivity implements
        TimesheetApproveIcomm{

    LinearLayout layout;
    int sizeInDp;


    Toolbar toolbar;
    FragmentManager fm;
    FragmentTransaction ft;
    TimesheetsToApproveFragment timeSheetsFragment;
    TimesheetDetailToApproveFragment timesheetDetailToApproveFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_timesheets);

        layout = (LinearLayout) findViewById(R.id.layout_detail_manager);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)layout.getLayoutParams();
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (sizeInDp*scale + 0.5f);

        params.setMargins(dpAsPixels, 0, dpAsPixels, 0);
        layout.setLayoutParams(params);



        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);
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
