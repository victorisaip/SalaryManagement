package com.example.victo.salarymanagement.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.victo.salarymanagement.Adapters.EmployeesAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeesFragment extends Fragment implements EmployeesAdapter.ListItemClickListener{
    private static final String TAG = "employees";
    public RecyclerView recyclerView;
    private EmployeesAdapter employeesAdapter;
    private Toast toast;


    public EmployeesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employees, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_employees);
        employeesAdapter = new EmployeesAdapter(DatabaseManager.getInstance().employees,this,getActivity());
        employeesAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(employeesAdapter);
        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
            List<User> userList = new ArrayList<>();
        userList = employeesAdapter.getEmployeesList();

    }
}
