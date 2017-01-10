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
    EditText startDate;
    EditText etEndDate,etApprover,etStatus,etNumHours;
    DatePickerFragment dialog;
    private static final String DIALOG_DATE = "DialogDate";



    public RegisterTimesheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_timesheet,container,false);

        etEndDate = (EditText) view.findViewById(R.id.etEndDate);
        etApprover = (EditText) view.findViewById(R.id.etApprover);
        etStatus = (EditText) view.findViewById(R.id.etStatus);
        etNumHours = (EditText) view.findViewById(R.id.etNumberOfHours);
        startDate = (EditText) view.findViewById(R.id.etStartDate);
        etStatus.setText("created");
        btnRegisterTimesheet = (Button) view.findViewById(R.id.btnRegisterTimeSheet);

        btnRegisterTimesheet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("Onclick et: ",startDate.getText().toString() +"\n\n"+ etEndDate.getText().toString());
                DatabaseManager.getInstance().createTimeSheet(startDate.getText().toString(),
                        etApprover.getText().toString(),etNumHours.getText().toString(),etEndDate.getText().toString());
            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        return view;
    }

    public void setEtEndDate(String text){
        etEndDate.setText(text);
    }

    public void showDatePicker(){
        FragmentManager manager = getFragmentManager();
        Date date = new Date();
        dialog = DatePickerFragment.newInstance(date);
        dialog.show(manager, DIALOG_DATE);
    }

    public void setStartDate(String text){
        startDate.setText(text);
    }
}
