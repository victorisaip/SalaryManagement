package com.example.victo.salarymanagement.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.victo.salarymanagement.BroadcastReceivers.MyBroadcastReceiver;
import com.example.victo.salarymanagement.Fragments.DetailTimeSheetFragment;
import com.example.victo.salarymanagement.Fragments.TimesheetsFragment;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.R;


public class SeeTimeSheetHistoryActivity extends AppCompatActivity implements TimesheetComm {
    private static final String TAG = "STATUS";
    private static final String TAG2 = "SeeActivity";

    Toolbar toolbar;
    FragmentManager fm;
    FragmentTransaction ft;
    TimesheetsFragment timeSheetsFragment;
    DetailTimeSheetFragment detailTimeSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_time_sheet_history);
        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);

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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_logout:
                break;
            case R.id.action_help:

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
