package com.example.victo.salarymanagement.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.Calendar;
import java.util.Date;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTimesheetFragment extends Fragment {
    //Layout variables
    Button btnRegisterTimesheet;
    EditText startDate, etEndDate, etApprover, etStatus, etMonday, etTuesday, etWednesday, etThursday, etFriday,etTotalHours;
    DatePickerFragment dialog;
    String mstartDate, mEndDate, mApprover, mMonday, mTuesday, mWednesday, mThursday, mFriday,mTotalHours;
    double hoursMonday, hoursTuesday, hoursWednesday, hoursThursday, hoursFriday;
    double finalSum = 0;
    private static final String DIALOG_DATE = "DialogDate";


    public RegisterTimesheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_timesheet, container, false);

        etEndDate = (EditText) view.findViewById(R.id.etEndDate);
        etApprover = (EditText) view.findViewById(R.id.etApprover);
        startDate = (EditText) view.findViewById(R.id.etStartDate);
        etStatus.setText("created");
        etTotalHours = (EditText) view.findViewById(R.id.etNumberOfHours);
        btnRegisterTimesheet = (Button) view.findViewById(R.id.btnRegisterTimeSheet);
        etMonday = (EditText) view.findViewById(R.id.etMonday);
        etTuesday = (EditText) view.findViewById(R.id.etTuesday);
        etWednesday = (EditText) view.findViewById(R.id.etWednesday);
        etThursday = (EditText) view.findViewById(R.id.etThursday);
        etFriday = (EditText) view.findViewById(R.id.etFriday);

        /*
        mstartDate = startDate.getText().toString();
        mEndDate = etEndDate.getText().toString();
        mApprover = etApprover.getText().toString();
        mMonday = etMonday.getText().toString();
        mTuesday = etTuesday.getText().toString();
        mWednesday = etWednesday.getText().toString();
        mThursday = etThursday.getText().toString();
        mFriday = etFriday.getText().toString();
        hoursMonday = Double.parseDouble(mMonday);
        hoursTuesday = Double.parseDouble(mThursday);
        hoursWednesday = Double.parseDouble(mWednesday);
        hoursThursday = Double.parseDouble(mThursday);
        hoursFriday = Double.parseDouble(mFriday);
        */

        mstartDate = startDate.getText().toString();
        mEndDate = etEndDate.getText().toString();
        mApprover = etApprover.getText().toString();
        etMonday.setText("0.0");
        etTuesday.setText("0.0");
        etWednesday.setText("0.0");
        etThursday.setText("0.0");
        etFriday.setText("0.0");
        etTotalHours.setText("0.0");
        hoursTuesday = Double.parseDouble(mThursday);
        hoursWednesday = Double.parseDouble(mWednesday);
        hoursThursday = Double.parseDouble(mThursday);
        hoursFriday = Double.parseDouble(mFriday);

        Date actualDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(actualDate);
        final String parsedActualDate = c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.YEAR);

        btnRegisterTimesheet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("Onclick et: ", startDate.getText().toString() + "\n\n" + etEndDate.getText().toString());
                DatabaseManager.getInstance().createTimeSheet(mstartDate, mEndDate,
                        mApprover, parsedActualDate, mMonday, mTuesday, mWednesday, mThursday, mFriday,etTotalHours.getText().toString());
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        etMonday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        return view;
    }

    public void setEtEndDate(String text) {
        etEndDate.setText(text);
    }

    public void showDatePicker() {
        FragmentManager manager = getFragmentManager();
        Date date = new Date();
        dialog = DatePickerFragment.newInstance(date);
        dialog.show(manager, DIALOG_DATE);
    }

    public void setStartDate(String text) {
        startDate.setText(text);
    }

    public double sumHoursMonday() {
        hoursMonday = Double.parseDouble(mMonday);
        finalSum = finalSum + hoursMonday;
        return finalSum;
    }
    public double sumHoursTuesday() {
        hoursMonday = Double.parseDouble(mMonday);
        finalSum = finalSum + hoursMonday;
        return finalSum;
    }
    public double sumHoursWednesday() {
        hoursMonday = Double.parseDouble(mMonday);
        finalSum = finalSum + hoursMonday;
        return finalSum;
    }
    public double sumHoursThursday() {
        hoursMonday = Double.parseDouble(mMonday);
        finalSum = finalSum + hoursMonday;
        return finalSum;
    }
    public double sumHoursFriday() {
        hoursMonday = Double.parseDouble(mMonday);
        finalSum = finalSum + hoursMonday;
        return finalSum;
    }

}
