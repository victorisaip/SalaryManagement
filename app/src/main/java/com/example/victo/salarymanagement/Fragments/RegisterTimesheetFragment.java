package com.example.victo.salarymanagement.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import java.util.Calendar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.victo.salarymanagement.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTimesheetFragment extends Fragment {
    //Layout variables
    Button btnRegisterTimesheet;
    EditText etEndDate,etApprover,etStatus,etNumHours;



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
        etStatus.setText("created");
        btnRegisterTimesheet = (Button) view.findViewById(R.id.btnRegisterTimeSheet);

        btnRegisterTimesheet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }



}
