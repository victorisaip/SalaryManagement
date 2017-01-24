package com.example.victo.salarymanagement.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;

public class MenuForManagers extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_for_managers);

        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);



//        linearLayout = (LinearLayout) findViewById(R.id.layout_menu_manager);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)linearLayout.getLayoutParams();
//        params.setMargins(100, 0, 0, 0);
//        linearLayout.setLayoutParams(params);

    }

    public void goEmployeeManagement(View view) {
        Intent i = new Intent(getApplicationContext(),EmployeeManagementActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<User> users = DatabaseManager.getInstance().employees;
    }

    public void approveTimesheets(View view) {
        Intent i = new Intent(getApplicationContext(),ApproveTimesheetsActivity.class);
        startActivity(i);
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