package com.example.victo.salarymanagement.Fragments;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTimeSheetFragment extends Fragment {

    EditText etTimesheetStartDate,etTimesheetEndDate,etTimesheetApprover,etTimesheetStatus,
            etTimesheetMonday,etTimesheetTuesday,etTimesheetWednesday,etTimesheetThursday,etTimesheetFriday,
            etTimesheetActualDate,etTimesheetTotalHours,etTimesheetEmail;
    public DetailTimeSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_time_sheet, container, false);

        etTimesheetStartDate = (EditText) view.findViewById(R.id.etDetailTimesheetStartDate);
        etTimesheetEndDate = (EditText) view.findViewById(R.id.etDetailTimesheetEndDate);
        etTimesheetApprover = (EditText) view.findViewById(R.id.etDetailTimesheetApprover);
        etTimesheetStatus = (EditText) view.findViewById(R.id.etDetailTimesheetStatus);
        etTimesheetMonday = (EditText) view.findViewById(R.id.etDetailTimesheetMonday);
        etTimesheetTuesday = (EditText) view.findViewById(R.id.etDetailTimesheetTuesday);
        etTimesheetWednesday = (EditText) view.findViewById(R.id.etDetailTimesheetWednesday);
        etTimesheetThursday = (EditText) view.findViewById(R.id.etDetailTimesheetThursday);
        etTimesheetFriday = (EditText) view.findViewById(R.id.etDetailTimesheetFriday);
        etTimesheetTotalHours = (EditText) view.findViewById(R.id.etDetailTimesheetTotalHours);
        etTimesheetActualDate = (EditText) view.findViewById(R.id.etDetailTimesheetDate);
        etTimesheetEmail = (EditText) view.findViewById(R.id.etDetailTimesheetEmail);


        // Inflate the layout for this fragment
        return view;
    }

    public void setMyTextViewTo(String startDate,
                                String endDate,
                                String approver,
                                String status,
                                String monday,
                                String tuesday,
                                String wednesday,
                                String thursday,
                                String friday,
                                String totalHours,
                                String email,
                                String actualDate){

        etTimesheetStartDate.setText(startDate);
        etTimesheetStatus.setText(status);
        etTimesheetApprover.setText(approver);
        etTimesheetEndDate.setText(endDate);
        etTimesheetMonday.setText(monday);
        etTimesheetTuesday.setText(tuesday);
        etTimesheetWednesday.setText(wednesday);
        etTimesheetThursday.setText(thursday);
        etTimesheetFriday.setText(friday);
        etTimesheetTotalHours.setText(totalHours);
        etTimesheetEmail.setText(email);
        etTimesheetActualDate.setText(actualDate);

        AssetManager assetManager = getActivity().getAssets();
        Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");

        etTimesheetStartDate.setTypeface(regular);
        etTimesheetStatus.setTypeface(regular);
        etTimesheetApprover.setTypeface(regular);
        etTimesheetEndDate.setTypeface(regular);
        etTimesheetMonday.setTypeface(regular);
        etTimesheetTuesday.setTypeface(regular);
        etTimesheetWednesday.setTypeface(regular);
        etTimesheetThursday.setTypeface(regular);
        etTimesheetFriday.setTypeface(regular);
        etTimesheetTotalHours.setTypeface(regular);
        etTimesheetEmail.setTypeface(regular);
        etTimesheetActualDate.setTypeface(regular);
    }

}
