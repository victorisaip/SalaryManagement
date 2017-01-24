package com.example.victo.salarymanagement.Activities;
/*
Project name:  Salary Management
Description: Apps to manage the employment salary according to the total hours worked.
Developers: Victor , Saul , Ramesh */

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.widget.LinearLayout;



import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.Fragments.LogInFragment;
import com.example.victo.salarymanagement.Fragments.SignUpFragment;
import com.example.victo.salarymanagement.R;
import com.example.victo.salarymanagement.Adapters.ViewPagerAdapter;

public class HomePage extends AppCompatActivity{
    private static final String TAG = "Home page";
    //Fragments
    SignUpFragment signUpFragment;
    LogInFragment logInFragment;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    LinearLayout myLinearLayout;
    DatabaseManager myManager;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        myLinearLayout = (LinearLayout) findViewById(R.id.activity_home_page);



        LinearLayout ln= (LinearLayout) findViewById(R.id.activity_home_page);


        //Fragments
        logInFragment = new LogInFragment();
        signUpFragment = new SignUpFragment();

        //Layout Elements
        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_logIn);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(logInFragment,"Log in");
        viewPagerAdapter.addFragments(signUpFragment,"Sign up");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);







    }




}
