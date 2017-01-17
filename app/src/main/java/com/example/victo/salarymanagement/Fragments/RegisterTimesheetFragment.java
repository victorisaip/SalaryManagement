

package com.example.victo.salarymanagement.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterTimesheetFragment extends Fragment {
    private static final String TAG = "RegisterTM";
    //Layout variables
    Button btnRegisterTimesheet;
    EditText startDate, etEndDate, etStatus, etMonday, etTuesday, etWednesday, etThursday, etFriday,etTotalHours,etActualDate;
    DatePickerFragment dialog;
    Spinner spApprover;
    String mstartDate, mEndDate, mApprover, mMonday, mTuesday, mWednesday, mThursday, mFriday,mTotalHours;
    double hoursMonday, hoursTuesday, hoursWednesday, hoursThursday, hoursFriday;
    double finalSum = 0;
    private static final String DIALOG_DATE = "DialogDate";
    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

    public RegisterTimesheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_timesheet, container, false);

        etEndDate = (EditText) view.findViewById(R.id.etEndDate);

        startDate = (EditText) view.findViewById(R.id.etStartDate);
        etStatus = (EditText) view.findViewById(R.id.etStatus);
        etTotalHours = (EditText) view.findViewById(R.id.etNumberOfHours);
        btnRegisterTimesheet = (Button) view.findViewById(R.id.btnRegisterTimeSheet);
        etMonday = (EditText) view.findViewById(R.id.etMonday);
        etTuesday = (EditText) view.findViewById(R.id.etTuesday);
        etWednesday = (EditText) view.findViewById(R.id.etWednesday);
        etThursday = (EditText) view.findViewById(R.id.etThursday);
        etFriday = (EditText) view.findViewById(R.id.etFriday);
        etActualDate = (EditText) view.findViewById(R.id.etActualDate);
        spApprover = (Spinner)view.findViewById(R.id.SpinSelectApprover);

        mstartDate = startDate.getText().toString();
        mEndDate = etEndDate.getText().toString();
       // mApprover = etApprover.getText().toString();
        etMonday.setText("0.0");
        etTuesday.setText("0.0");
        etWednesday.setText("0.0");
        etThursday.setText("0.0");
        etFriday.setText("0.0");
        etTotalHours.setText("0.0");
        etStatus.setText("created");
        mMonday = etMonday.getText().toString();
        mTuesday = etTuesday.getText().toString();
        mWednesday = etWednesday.getText().toString();
        mThursday = etThursday.getText().toString();
        mFriday = etFriday.getText().toString();


        Date actualDate = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("MM-dd-yyyy");
        final String receivedResult = dt1.format(actualDate);

        etActualDate.setText(receivedResult);
        btnRegisterTimesheet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("Ramesh","OnClick Listners");
                Toast.makeText(getContext(),
                        "OnClickListener : " +

                                "\nYour Approver "+ String.valueOf(spApprover.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();

                String mangerFromSpinner = String.valueOf(spApprover.getSelectedItem());

                mstartDate = startDate.getText().toString();
                mEndDate = etEndDate.getText().toString();
               /// mApprover = etApprover.getText().toString();
                mMonday = etMonday.getText().toString();
                mTuesday = etTuesday.getText().toString();
                mWednesday = etWednesday.getText().toString();
                mThursday = etThursday.getText().toString();
                mFriday = etFriday.getText().toString();
                mTotalHours = etTotalHours.getText().toString();

                DatabaseManager.getInstance().createTimeSheet(mstartDate, mEndDate,
                        mangerFromSpinner, receivedResult, mMonday, mTuesday, mWednesday, mThursday, mFriday,mTotalHours,email);






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

                if(!hasFocus){
                    if(etMonday.getText().toString().equals("")){
                        etMonday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }

            }
        });

        etTuesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etTuesday.getText().toString().equals("")){
                        etTuesday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }

            }
        });

        etWednesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etWednesday.getText().toString().equals("")){
                        etWednesday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }
            }
        });

        etThursday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etThursday.getText().toString().equals("")){
                        etThursday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }
            }
        });

        etFriday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(etFriday.getText().toString().equals("")){
                        etFriday.setText("0.0");
                    }
                    sumHoursWeek();
                    etTotalHours.setText(String.valueOf(finalSum));
                }
            }
        });
       addApprovers();
        return view;
    }
    /*
    * Adding approvers to the list in Register Time sheet fragment*/
    public void addApprovers() {
        ArrayList<String> list = new ArrayList<>();

        ArrayList<User> myMangers = DatabaseManager.getInstance().managers;
        for (User manger:myMangers) {
            String name = manger.getName();
            list.add(name);
            Log.d("\nRamesh",name);
        }
        Log.d("Ramesh", "Approvers List will be Here");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, list);
       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spApprover.setAdapter(dataAdapter);
        Log.d("Ramesh","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+spApprover.getTextAlignment());


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

    public void sumHoursWeek() {
        mMonday = etMonday.getText().toString();
        mTuesday = etTuesday.getText().toString();
        mWednesday = etWednesday.getText().toString();
        mThursday = etThursday.getText().toString();
        mFriday = etFriday.getText().toString();

        finalSum = 0;
        hoursMonday = Double.parseDouble(mMonday);
        hoursTuesday = Double.parseDouble(mTuesday);
        hoursWednesday = Double.parseDouble(mWednesday);
        hoursThursday = Double.parseDouble(mThursday);
        hoursFriday = Double.parseDouble(mFriday);
        finalSum = finalSum + hoursMonday+hoursTuesday+hoursWednesday+hoursThursday+hoursFriday;
    }

}
