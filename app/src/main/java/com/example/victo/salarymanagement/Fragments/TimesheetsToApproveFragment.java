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

import com.example.victo.salarymanagement.Adapters.TimesheetsAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.Interfaces.TimesheetApproveIcomm;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesheetsToApproveFragment extends Fragment implements TimesheetsAdapter.ListItemClickListener{
    private static final String TAG = "Timesheets";
    public RecyclerView recyclerView;
    private TimesheetsAdapter timesheetsAdapter;
    private Toast mToast;

    public TimesheetsToApproveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timesheets_to_approve, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_timesheets_toApprove);
        timesheetsAdapter = new TimesheetsAdapter(DatabaseManager.getInstance().timesheetsToApprove,getActivity(),this);
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
        TimesheetApproveIcomm timesheetComm = (TimesheetApproveIcomm) getActivity();
        timesheetComm.setTextToTimesheet(
                timesheet.getStartDate(),
                timesheet.getEndDate(),
                timesheet.getApprover(),
                timesheet.getStatus(),
                timesheet.getMonday(),
                timesheet.getTuesday(),
                timesheet.getWednesday(),
                timesheet.getThursday(),
                timesheet.getFriday(),
                timesheet.getTotalHours(),
                timesheet.getEmail(),
                timesheet.getTotalHours()
        );
    }
}
