package com.example.victo.salarymanagement.DatabaseManager;

import android.util.Log;

import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.POJOs.User;
import com.google.firebase.auth.FirebaseAuth;
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
    public static ArrayList<User> users;
    public static ArrayList<User> employees;
    public static ArrayList<User> managers;
    public static ArrayList<Timesheet> timesheets;
    private static String emailUser;
    private static String businessRole;
    //Firebase attributes
    private FirebaseDatabase database;
    private  DatabaseReference myRef;
    //References to the database
    public static DatabaseReference myRefusers;
    private  static DatabaseReference myRefTimesheets;


    //Constructor
    private DatabaseManager(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Salary_Management_DB");
        myRefusers = myRef.child("Users");
        myRefTimesheets = myRef.child("Timesheets");
        users = new ArrayList<>();
        timesheets = new ArrayList<>();
        employees = new ArrayList<>();
        managers = new ArrayList<>();
        Log.d(TAG, "DatabaseManager created successfully");
        Log.d(TAG, "===========================================");
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            emailUser = "";
        } else {
            emailUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        }
        setListenerUsers();
        setListenerTimesheets();
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

    private static void setListenerTimesheets(){
        //Listening for timesheets
        myRefTimesheets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d(TAG, "Listening for timesheets");
                Log.d(TAG, "===========================================");
                Log.d(TAG, "onDataChange: " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                timesheets = new ArrayList<Timesheet>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Timesheet post = postSnapshot.getValue(Timesheet.class);
                    if(post.getEmail().equals(emailUser)){
                        timesheets.add(post);
                    }
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

    private static void setListenerUsers(){
        //Listening for any change in the users
        myRefusers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "Listening for registered users " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User post = postSnapshot.getValue(User.class);
                    if(emailUser.equals(post.getEmail())){
                        String businessRoleReceived = post.getBusinessRole();
                        businessRole = businessRoleReceived;
                        Log.d(TAG, "Business Role: "+businessRole);
                    }
                    users.add(post);
                    Log.d(TAG, "Email : " + post.getEmail()+", Name: "+post.getName());
                    if(post.getBusinessRole().equals("employee")){
                        employees.add(post);
                    } else {
                        if(post.getBusinessRole().equals("manager")){
                            managers.add(post);
                        }
                    }
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
    }

    public void createUser(String email, String password,String name,String businessRole){
        Log.d(TAG, "Creating user....");
        User myUser = new User(email,password,name,businessRole);
        myRefusers.push().setValue(myUser);
        Log.d(TAG, "User created");
        Log.d(TAG, "===========================================");
    }

    public void createTimeSheet(String startDate,String endDate,String approver,String actualDate,
                                String monday,String tuesday,String wednesday,String thursday,String friday,String totalHours,String email){
        Log.d(TAG, "Total hours " + totalHours);
        Timesheet myTimeSheet = new Timesheet(startDate,endDate,approver,actualDate,monday,
                tuesday,wednesday,thursday,friday,totalHours,email);
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

    public String getBusinessRole() {
        return businessRole;
    }
}