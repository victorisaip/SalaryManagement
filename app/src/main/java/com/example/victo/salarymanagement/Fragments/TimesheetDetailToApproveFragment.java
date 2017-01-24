package com.example.victo.salarymanagement.Fragments;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesheetDetailToApproveFragment extends Fragment {

    Button btnApprove,btnReject;
    EditText etTimesheetStartDate,etTimesheetEndDate,etTimesheetApprover,etTimesheetStatus,
    etTimesheetMonday,etTimesheetTuesday,etTimesheetWednesday,etTimesheetThursday,etTimesheetFriday,
    etTimesheetActualDate,etTimesheetTotalHours,etTimesheetEmail;


    public TimesheetDetailToApproveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timesheet_detail_to_approve, container, false);

        etTimesheetStartDate = (EditText) view.findViewById(R.id.etTimesheetStartDate);
        etTimesheetEndDate = (EditText) view.findViewById(R.id.etTimesheetEndDate);
        etTimesheetApprover = (EditText) view.findViewById(R.id.etTimesheetApprover);
        etTimesheetStatus = (EditText) view.findViewById(R.id.etTimesheetStatus);
        etTimesheetMonday = (EditText) view.findViewById(R.id.etTimesheetMonday);
        etTimesheetTuesday = (EditText) view.findViewById(R.id.etTimesheetTuesday);
        etTimesheetWednesday = (EditText) view.findViewById(R.id.etTimesheetWednesday);
        etTimesheetThursday = (EditText) view.findViewById(R.id.etTimesheetThursday);
        etTimesheetFriday = (EditText) view.findViewById(R.id.etTimesheetFriday);
        etTimesheetTotalHours = (EditText) view.findViewById(R.id.etTimesheetTotalHours);
        etTimesheetActualDate = (EditText) view.findViewById(R.id.etTimesheetDate);
        etTimesheetEmail = (EditText) view.findViewById(R.id.etTimesheetEmployee);

        AssetManager assetManager = getActivity().getAssets();
        Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");

        etTimesheetStartDate.setTypeface(regular);
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


        btnApprove = (Button) view.findViewById(R.id.btnApprove);
        btnReject = (Button) view.findViewById(R.id.btnReject);

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager.getInstance().updateStateTimesheet(etTimesheetStartDate.getText().toString(),
                        etTimesheetEndDate.getText().toString(),etTimesheetEmail.getText().toString(),
                        "Approved");
                etTimesheetStatus.setText("Approved");
                Toast.makeText(getActivity(), "Timesheet approved", Toast.LENGTH_SHORT).show();
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseManager.getInstance().updateStateTimesheet(etTimesheetStartDate.getText().toString(),
                        etTimesheetEndDate.getText().toString(),etTimesheetEmail.getText().toString(),
                        "Rejected");
                etTimesheetStatus.setText("Rejected");
                Toast.makeText(getActivity(), "Timesheet rejected", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
    public void setText(String startDate,
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
        etTimesheetEndDate.setText(endDate);
        etTimesheetMonday.setText(monday);
        etTimesheetTuesday.setText(tuesday);
        etTimesheetWednesday.setText(wednesday);
        etTimesheetThursday.setText(thursday);
        etTimesheetFriday.setText(friday);
        etTimesheetTotalHours.setText(totalHours);
        etTimesheetEmail.setText(email);
        etTimesheetActualDate.setText(actualDate);
    }
}
