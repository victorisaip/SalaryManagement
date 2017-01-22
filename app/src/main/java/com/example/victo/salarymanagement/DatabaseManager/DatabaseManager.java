package com.example.victo.salarymanagement.DatabaseManager;

import android.util.Log;
import android.widget.Toast;

import com.example.victo.salarymanagement.POJOs.Report;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.POJOs.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
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
    public static ArrayList<Timesheet> timesheetsToApprove;
    public static ArrayList<Report> reports;
    private static String emailUser;
    private static String businessRole;
    //Firebase attributes
    private FirebaseDatabase database;
    private  DatabaseReference myRef;
    //References to the database
    public static DatabaseReference myRefReports;
    public static DatabaseReference myRefusers;
    private  static DatabaseReference myRefTimesheets;


    //Constructor
    private DatabaseManager(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Salary_Management_DB");
        myRefusers = myRef.child("Users");
        myRefTimesheets = myRef.child("Timesheets");
        myRefReports = myRef.child("Reports");
        users = new ArrayList<>();
        timesheets = new ArrayList<>();
        timesheetsToApprove = new ArrayList<>();
        employees = new ArrayList<>();
        managers = new ArrayList<>();
        reports = new ArrayList<>();
        Log.d(TAG, "DatabaseManager created successfully");
        Log.d(TAG, "===========================================");
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            emailUser = "";
        } else {
            emailUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        }
        setListenerUsers();
        setListenerTimesheets();
        setListenerReports();
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

    private static void setListenerReports(){
        reports = new ArrayList<>();

        myRefReports.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d(TAG, "Listening for reports");
                Log.d(TAG, "===========================================");
                Log.d(TAG, "onDataChange: " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                reports = new ArrayList<Report>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Report post = postSnapshot.getValue(Report.class);
                    reports.add(post);

                }
                Log.d(TAG, "Reports" + reports);
                Log.d(TAG, "===========================================");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private static void setListenerTimesheets(){
        timesheets = new ArrayList<>();
        timesheetsToApprove = new ArrayList<>();
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
                    String key = postSnapshot.getKey();
                    post.setKey(key);
                    if(post.getEmail().equals(emailUser)){
                        timesheets.add(post);
                    }
                    timesheetsToApprove.add(post);
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
                users = new ArrayList<>();
                employees = new ArrayList<>();
                managers = new ArrayList<>();
                Log.d(TAG, "Listening for registered users " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User post = postSnapshot.getValue(User.class);

                    if(emailUser.equals(post.getEmail())){
                        String businessRoleReceived = post.getBusinessRole();
                        businessRole = businessRoleReceived;
                        Log.d(TAG, "Business Role: "+businessRole);
                    }
                    String userId = postSnapshot.getKey();
                    post.setId(userId);
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

    public boolean deleteEmployee(String email){
        boolean flag = false;
        String key = getKeyFromEmployee(email);
        //Deleting employee
        myRefusers.child(key).removeValue();
        flag = true;
        return flag;
    }

    public boolean updateEmployee(String email, String employeeName, String employeeExpLevel, String status){
        boolean flag = false;
        String key = getKeyFromEmployee(email);
        //Updating values
        myRefusers.child(key).child("state").setValue(status);
        myRefusers.child(key).child("email").setValue(email);
        if(employeeExpLevel.equals("jr")){
            myRefusers.child(key).child("experienceLevel").setValue(0);
        } else {
            if (employeeExpLevel.equals("sr")){
                myRefusers.child(key).child("experienceLevel").setValue(1);
            } else {
                if (employeeExpLevel.equals("exp")){
                    myRefusers.child(key).child("experienceLevel").setValue(2);
                } else {
                    myRefusers.child(key).child("experienceLevel").setValue(-1);
                }
            }
        }
        myRefusers.child(key).child("name").setValue(employeeName);
        flag = true;
        return flag;
    }



    public String getKeyFromEmployee(String email){
        String key = "";
        boolean flag = false;
        int i = 0;

        while(i<employees.size() && flag==false){
            if(email.equals(employees.get(i).getEmail())){
                key = employees.get(i).getId();
                flag = true;
            } else {
                i++;
            }
        }
        return key;
    }

    public void createReport(Timesheet t1,Timesheet t2,User employee){
        double numHours = Double.parseDouble(t1.getTotalHours())
                + Double.parseDouble(t2.getTotalHours());
        String numHoursToString = String.valueOf(numHours);
        String startDate = t1.getStartDate();
        String endDate = t2.getEndDate();
        double rate ;
        int experienceLevel = employee.getExperienceLevel();
        if(experienceLevel == 0){
            rate  = 35;
        } else {
            if(experienceLevel == 1){
                rate =45;
            } else {
                if(experienceLevel == 2){
                    rate  = 50;
                } else {
                    rate = -1;
                }
            }
        }
        String paymentRate = String.valueOf(rate);
        double payment = rate * numHours;
        String paymentToString = String.valueOf(payment);
        Report r = new Report(numHoursToString,paymentRate,startDate,endDate,paymentToString);
        myRefReports.push().setValue(r);

    }

    public void updateStateTimesheet(String startDate,String endDate,String email, String status){
        String key = getTimesheetKey(startDate,endDate,email);
        myRefTimesheets.child(key).child("status").setValue(status);

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

    public String getTimesheetKey(String startdate,String endDate,String email){
        int i = 0;
        String key = "";
        boolean flag = false;
        while (i<timesheetsToApprove.size() && flag == false){
            Timesheet t = timesheetsToApprove.get(i);
            if(t.getStartDate().equals(startdate) &&
                    t.getEndDate().equals(endDate)&&
                    t.getEmail().equals(email)){
                key = t.getKey();
                flag = true;
            } else {
                i++;
            }
        }

        return key;
    }
}