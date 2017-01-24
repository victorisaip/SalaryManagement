package com.example.victo.salarymanagement.Fragments;


import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victo.salarymanagement.Adapters.EmployeesAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.Interfaces.EmployeeComm;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeesFragment extends Fragment implements EmployeesAdapter.ListItemClickListener{
    private static final String TAG = "employees";
    public static RecyclerView recyclerView;
    TextView tvLabelEmployee;
    public static EmployeesAdapter employeesAdapter;
    private static ArrayList<User> myEmployees;
    private static Context context;
    private static EmployeesFragment employeesFragment;

    public EmployeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employees, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_employees);
        tvLabelEmployee = (TextView) view.findViewById(R.id.tv_title_employee);
        AssetManager assetManager = getActivity().getAssets();
        Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Bold.otf");
        tvLabelEmployee.setTypeface(regular);
        context = getActivity();
        employeesFragment = this;
        setListenerUsers();
        Log.d(TAG, "Calling add employees");
        addingEmployees();
        return view;
    }

    public static void addingEmployees(){
        employeesAdapter = new EmployeesAdapter(DatabaseManager.getInstance().employees,employeesFragment,context);
        employeesAdapter.notifyDataSetChanged();
        Log.d(TAG, "addingEmployees: "+"in");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(employeesAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        List<User> userList = new ArrayList<>();
        userList = employeesAdapter.getEmployeesList();
        User user = new User();
        user = userList.get(clickedItemIndex);
        String expLevel = "";
        EmployeeComm employeeComm = (EmployeeComm) getActivity();
        Log.d(TAG, "onListItemClick: "+"before switch");
        switch (user.getExperienceLevel()){
            case 0:
                expLevel = "jr";
                break;
            case 1:
                expLevel = "sr";
                break;
            case 2:
                expLevel = "exp";
                break;
        }
        employeeComm.setEmployeeInformation(user.getName(),user.getEmail(),user.getState(),expLevel);

    }

    public void updateRecyclerView(String s){
        int pos = positionEmployee(s);
        employeesAdapter.removeItem(pos);
    }

    public void onEmployeeUpdated(String name, String email, String status, String expLevel){
        int pos = positionEmployee(email);
        employeesAdapter.updateItem(pos,name,email,status,expLevel);
    }

    public int positionEmployee(String email){
        int i = 0;
        int pos = 0;
        boolean flag = false;
        ArrayList<User> l = new ArrayList<>();
        l = DatabaseManager.getInstance().employees;
        while(i<DatabaseManager.getInstance().employees.size() && flag == false){

            if(l.get(i).getEmail().equals(email)){
                pos = i;
                flag = true;
            } else {
                i++;
            }
        }
        return pos;
    }

    public static void setListenerUsers(){
        //Listening for any change in the users
        DatabaseManager.getInstance().myRefusers.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                myEmployees = new ArrayList<User>();


                Log.d(TAG, "Listening for registered users " + dataSnapshot.getChildrenCount());
                Log.d(TAG, "===========================================");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User post = postSnapshot.getValue(User.class);
                    if(post.getBusinessRole().equals("employee")){
                        myEmployees.add(post);
                    }
                }
                Log.d(TAG, "Calling adding employees on dataChange");
                addingEmployees();
                Log.d("RameshMangerRecycles",myEmployees.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: "+ "operation with error!");
                Log.d(TAG, "===========================================");
            }
        });
    }


}
