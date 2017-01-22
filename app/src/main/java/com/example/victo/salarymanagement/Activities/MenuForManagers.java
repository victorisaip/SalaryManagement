package com.example.victo.salarymanagement.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;

public class MenuForManagers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_for_managers);
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
}