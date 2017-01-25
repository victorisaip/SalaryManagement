package com.example.victo.salarymanagement.Activities;

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

public class EmployeeManagementActivity extends AppCompatActivity implements EmployeeComm {

    Toolbar toolbar;
    FragmentManager fm;
    FragmentTransaction ft;
    static EmployeesFragment employeesFragment;
    DetailEmployeeFragment detailEmployeesFragment;

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.action_logout:
//
//
//                break;
//            case R.id.action_help:
//
//                break;
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
