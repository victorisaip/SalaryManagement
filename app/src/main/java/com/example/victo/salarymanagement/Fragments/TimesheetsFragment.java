package com.example.victo.salarymanagement.Fragments;


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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.victo.salarymanagement.Adapters.TimesheetsAdapter.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesheetsFragment extends Fragment implements ListItemClickListener{
    private static final String TAG = "Timesheets";
    public RecyclerView recyclerView;
    private TimesheetsAdapter timesheetsAdapter;
    TimesheetsFragment timesheetsFragment;
    private Toast mToast;
    private static ArrayList<Timesheet> timesheets;

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
        setListenerTimesheets();
        timesheetsFragment = this;
        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        List<Timesheet> timesheetArrayList = new ArrayList<>();
        timesheetArrayList = timesheetsAdapter.getTimesheetList();
        Timesheet timesheet = new Timesheet();
        timesheet = timesheetArrayList.get(clickedItemIndex);
        TimesheetComm timesheetComm = (TimesheetComm) getActivity();
        String startDate = timesheet.getStartDate();
        String endDate = timesheet.getEndDate();
        String approver = timesheet.getApprover();
        String status = timesheet.getStatus();
        String monday = timesheet.getMonday();
        String tuesday = timesheet.getTuesday();
        String wednesday = timesheet.getWednesday();
        String thursday = timesheet.getThursday();
        String friday = timesheet.getFriday();
        String totalHours = timesheet.getTotalHours();
        String email = timesheet.getEmail();
        String actualDate = timesheet.getActualDate();
        timesheetComm.setTextToTimeSheet(startDate,
                endDate,
                approver,
                status,
                monday,
                tuesday,
                wednesday,
                thursday,
                friday,
                totalHours,
                email,
                actualDate);
    }


    private static void setListenerTimesheets(){
        timesheets = new ArrayList<>();
        final String emailUser = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        //Listening for timesheets
        DatabaseManager.getInstance().myRefTimesheets.addValueEventListener(new ValueEventListener() {
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



}
