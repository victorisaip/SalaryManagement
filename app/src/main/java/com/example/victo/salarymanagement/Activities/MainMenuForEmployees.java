package com.example.victo.salarymanagement.Activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainMenuForEmployees extends AppCompatActivity {

    private static final String TAG = "STATUS";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    Button btnRegisterTimesheet,btnSeeHistory;
    TextView tv;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_for_employees);

        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: ");
        ArrayList<Timesheet> timesheets = DatabaseManager.getInstance().timesheets;

        AssetManager assetManager = getAssets();
        Typeface typeface = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");

        btnRegisterTimesheet = (Button) findViewById(R.id.btnRegisterTimeSheet);
        btnSeeHistory = (Button) findViewById(R.id.btnSeeHistory);
        btnRegisterTimesheet.setTypeface(typeface);
        btnSeeHistory.setTypeface(typeface);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        mAuth.signOut();
    }

    public void registerTimeSheet(View view) {
        Intent i = new Intent(this,RegisterTimeSheet.class);
        startActivity(i);
    }

    public void seeHistory(View view) {
        Intent i = new Intent(this,SeeTimeSheetHistoryActivity.class);
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
                mAuth.signOut();
                finish();
                break;
            case R.id.action_help:

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
