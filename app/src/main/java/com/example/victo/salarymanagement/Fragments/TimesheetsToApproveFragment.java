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
import android.widget.Toast;

import com.example.victo.salarymanagement.Adapters.TimesheetsAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.Interfaces.TimesheetApproveIcomm;
import com.example.victo.salarymanagement.Interfaces.TimesheetComm;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

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
    private static ArrayList<Timesheet> timesheets;

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
        setListenerTimesheets();
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
                timesheet.getActualDate()
        );
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

    public void updateTimesheet(String startDate,String endDate,String email, String status){
        String key = getTimesheetKey(startDate,endDate,email);
        timesheetsAdapter.updateItem(key,status);
    }

    public String getTimesheetKey(String startdate,String endDate,String email){
        int i = 0;
        String key = "";
        boolean flag = false;
        while (i<timesheets.size() && flag == false){
            Timesheet t = timesheets.get(i);
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
