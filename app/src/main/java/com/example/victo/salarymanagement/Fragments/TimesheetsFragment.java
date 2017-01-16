package com.example.victo.salarymanagement.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.victo.salarymanagement.Adapters.TimesheetsAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesheetsFragment extends Fragment implements TimesheetsAdapter.ListItemClickListener{
    private static final String TAG = "Timesheets";
    public RecyclerView recyclerView;
    private TimesheetsAdapter timesheetsAdapter;
    private Toast mToast;


    public TimesheetsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timesheets, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_timesheets);
        timesheetsAdapter = new TimesheetsAdapter(DatabaseManager.getInstance().timesheets,getActivity(),this);
        timesheetsAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(timesheetsAdapter);
        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        List<Timesheet> timesheetArrayList = new ArrayList<>();
        timesheetArrayList = timesheetsAdapter.getTimesheetList();
        Timesheet timesheet = new Timesheet();
        timesheet = timesheetArrayList.get(clickedItemIndex);
        TimesheetComm timesheetComm = (TimesheetComm) getActivity();
        timesheetComm.setTextToTimeSheet("Approver: " + timesheet.getApprover() + "\n" +
        "Actual date: "+ timesheet.getActualDate()+ "\n" +
        "Start date: "+ timesheet.getStartDate() + "\n" +
        "Hours monday: "+timesheet.getMonday() + "\n" +
                "Hours tuesday: "+timesheet.getTuesday() + "\n" +
                "Hours wednesday: "+timesheet.getWednesday() + "\n" +
                "Hours thursday: "+timesheet.getThursday() + "\n" +
                "Hours friday: "+timesheet.getFriday() + "\n" +
        "Total hours: "+timesheet.getTotalHours() );
    }
}
