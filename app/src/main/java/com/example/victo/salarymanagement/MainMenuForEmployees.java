package com.example.victo.salarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuForEmployees extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_for_employees);
    }

    public void registerTimeSheet(View view) {
        Intent i = new Intent(this,RegisterTimeSheet.class);
        startActivity(i);
    }

    public void seeHistory(View view) {

    }
}
