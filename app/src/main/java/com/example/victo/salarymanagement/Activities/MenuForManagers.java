package com.example.victo.salarymanagement.Activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MenuForManagers extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    Button btnEmployeeManagement, btnApproveTimesheets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_for_managers);

        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);
        btnApproveTimesheets = (Button) findViewById(R.id.btnApproveTimesheets);
        btnEmployeeManagement = (Button) findViewById(R.id.btnEmployeeManagement);

        AssetManager assetManager = getAssets();
        Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");
        btnEmployeeManagement.setTypeface(regular);
        btnApproveTimesheets.setTypeface(regular);

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
                firebaseAuth.signOut();
                finish();
                break;
            case R.id.action_help:

                break;

        }

        return super.onOptionsItemSelected(item);
    }

}