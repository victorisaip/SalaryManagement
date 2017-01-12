package com.example.victo.salarymanagement.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.victo.salarymanagement.Adapters.TimesheetsAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesheetsFragment extends Fragment {
    private static final String TAG = "Timesheets";
    private static ArrayList<Timesheet> timesheets = new ArrayList<>();
    private RecyclerView recyclerView;
    private TimesheetsAdapter timesheetsAdapter;

    public TimesheetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timesheets, container, false);
        /*recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_timesheets);
        timesheets = DatabaseManager.getInstance().getTimesheets();
        timesheetsAdapter = new TimesheetsAdapter(timesheets,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(timesheetsAdapter);*/
        return view;
    }

}
