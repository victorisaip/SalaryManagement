package com.example.victo.salarymanagement.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.victo.salarymanagement.Fragments.DetailEmployeeFragment;
import com.example.victo.salarymanagement.Fragments.EmployeesFragment;
import com.example.victo.salarymanagement.Interfaces.EmployeeComm;
import com.example.victo.salarymanagement.R;
import com.google.firebase.auth.FirebaseAuth;

public class EmployeeManagementActivity extends AppCompatActivity implements EmployeeComm {

    Toolbar toolbar;
    FragmentManager fm;
    FragmentTransaction ft;
    static EmployeesFragment employeesFragment;
    DetailEmployeeFragment detailEmployeesFragment;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_management);

        toolbar = (Toolbar) findViewById(R.id.my_tool_bar);
        setSupportActionBar(toolbar);

        employeesFragment = new EmployeesFragment();
        detailEmployeesFragment = new DetailEmployeeFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.add(R.id.fr_employees,employeesFragment);
        ft.add(R.id.fr_detail_employees,detailEmployeesFragment);
        ft.commit();
    }

    @Override
    public void setEmployeeInformation(String name, String email, String states, String experienceLevel) {
        detailEmployeesFragment.setEmployeeInfo(name,email,states,experienceLevel);
    }

    @Override
    public void onEmployeeDeleted(String employeeEmail) {
        employeesFragment.updateRecyclerView(employeeEmail);

    }

    @Override
    public void onEmployeeUpdated(String name, String email, String status, String experienceLevel) {
        employeesFragment.onEmployeeUpdated(name,email,status,experienceLevel);
    }


}
