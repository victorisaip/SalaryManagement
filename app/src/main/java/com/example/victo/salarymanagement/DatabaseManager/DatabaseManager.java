package com.example.victo.salarymanagement.DatabaseManager;

import android.util.Log;

import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.POJOs.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by victo on 1/2/2017.
 */

public class DatabaseManager {

    private static final String TAG ="Database" ;
    private static DatabaseManager dbManager;
    private static ArrayList<User> users;
    public static ArrayList<Timesheet> timesheets;

    //Firebase attributes
    private FirebaseDatabase database;
    private static DatabaseReference myRef;
    private static DatabaseReference myRefusers;
    private static DatabaseReference myRefTimesheets;

    //Constructor
    private DatabaseManager(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Salary_Management_DB");
        myRefusers = myRef.child("Users");
        myRefTimesheets = myRef.child("Timesheets");
        users = new ArrayList<>();
        timesheets = new ArrayList<>();


        Log.d(TAG, "DatabaseManager created successfully");
        Log.d(TAG, "===========================================");
        //Listening for any change in the users
        myRefusers.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Listening for registered users " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User post = postSnapshot.getValue(User.class);
                    users.add(post);
                    Log.d(TAG, "Email : " + post.getEmail()+", Name: "+post.getName());
                }
                Log.d(TAG, "The User " + users);
                Log.d(TAG, "===========================================");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+ "operation with error!");
                Log.d(TAG, "===========================================");
            }
        });

        //Listening for timesheets
        myRefTimesheets.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d(TAG, "Listening for timesheets");
                Log.d(TAG, "===========================================");
                Log.d(TAG, "onDataChange: " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Timesheet post = postSnapshot.getValue(Timesheet.class);
                    timesheets.add(post);

                    Log.d(TAG, "Approver: " + post.getApprover());
                }
                Log.d(TAG, "Timesheets" + timesheets);
                Log.d(TAG, "===========================================");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+ "operation with error!");
                Log.d(TAG, "===========================================");
            }
        });
    }
    /*
    Method for getting an instance of our Database Manager
     */
    public static DatabaseManager getInstance(){
        if(dbManager==null){
            Log.d(TAG, "Getting instance");
            dbManager = new DatabaseManager();
        }
        Log.d(TAG, "getInstance: "+dbManager.myRef.getKey().toString());
        Log.d(TAG, "===========================================");
        return dbManager;
    }

    public void createUser(String email, String password,String name,String businessRole){
        Log.d(TAG, "Creating user....");
        User myUser = new User(email,password,name,businessRole);
        myRefusers.push().setValue(myUser);
        Log.d(TAG, "User created");
        Log.d(TAG, "===========================================");
    }

    public void createTimeSheet(String startDate,String endDate,String approver,String actualDate,
                                String monday,String tuesday,String wednesday,String thursday,String friday,String totalHours){
        Log.d(TAG, "Total hours " + totalHours);
        Timesheet myTimeSheet = new Timesheet(startDate,endDate,approver,actualDate,monday,
                tuesday,wednesday,thursday,friday,totalHours);
        myRefTimesheets.push().setValue(myTimeSheet);
        Log.d(TAG, "Timesheet created");
        Log.d(TAG, "===========================================");

    }

    public static ArrayList<Timesheet> getTimesheets() {
        return timesheets;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}