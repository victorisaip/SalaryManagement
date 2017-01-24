package com.example.victo.salarymanagement.Fragments;


import android.content.Context;
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

import com.example.victo.salarymanagement.Adapters.EmployeesAdapter;
import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.Interfaces.EmployeeComm;
import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailEmployeeFragment extends Fragment {

    TextView tvEmpMail, tvEmpName, tvEmpStatus,tvEmpExpLevel,tvEmpInformation;
    EditText etEmpMail, etEmpName, etEmpStatus, etEmpExpLevel;
    Button btnDeleteEmployee, btnUpdateEmployee;


    public DetailEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_employee, container, false);
        tvEmpInformation = (TextView) view.findViewById(R.id.tvEmpInformation);
        tvEmpMail = (TextView) view.findViewById(R.id.tvEmpMail);
        tvEmpName = (TextView) view.findViewById(R.id.tvEmpName);
        tvEmpStatus = (TextView) view.findViewById(R.id.tvEmpState);
        tvEmpExpLevel = (TextView) view.findViewById(R.id.tvEmpExpLevel);
        btnDeleteEmployee = (Button) view.findViewById(R.id.btnDeleteEmployee);
        btnUpdateEmployee = (Button) view.findViewById(R.id.btnUpdateEmployee);
        etEmpMail = (EditText) view.findViewById(R.id.etEmployeeEmail);
        etEmpName = (EditText) view.findViewById(R.id.etEmployeeName);
        etEmpStatus = (EditText) view.findViewById(R.id.etEmployeeState);
        etEmpExpLevel = (EditText) view.findViewById(R.id.etEmployeeExperience);

        AssetManager assetManager = getActivity().getAssets();
        Typeface bold = Typeface.createFromAsset(assetManager,"SourceSansPro-Bold.otf");
        Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");
        tvEmpMail.setTypeface(regular);
        tvEmpName.setTypeface(regular);
        tvEmpStatus.setTypeface(regular);
        tvEmpExpLevel.setTypeface(regular);
        tvEmpInformation.setTypeface(bold);
        btnDeleteEmployee.setTypeface(regular);
        btnUpdateEmployee.setTypeface(regular);

        etEmpMail.setTypeface(regular);
        etEmpName.setTypeface(regular);
        etEmpStatus.setTypeface(regular);
        etEmpExpLevel.setTypeface(regular);
        //Deleting an employee

        btnDeleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                email = etEmpMail.getText().toString();
                boolean result = DatabaseManager.getInstance().deleteEmployee(email);

                if(result){
                    Toast.makeText(getActivity(), "Employee Deleted", Toast.LENGTH_SHORT).show();
                    EmployeeComm employeeComm = (EmployeeComm) getActivity();
                    employeeComm.onEmployeeDeleted(email);
                    etEmpExpLevel.setText("");
                    etEmpMail.setText("");
                    etEmpName.setText("");
                    etEmpStatus.setText("");
                } else {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnUpdateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, state, expLevel, name;
                email = etEmpMail.getText().toString();
                state = etEmpStatus.getText().toString();
                expLevel = etEmpExpLevel.getText().toString();
                name = etEmpName.getText().toString();

                boolean result = DatabaseManager.getInstance().updateEmployee(
                        email,name,expLevel,state
                );
                if (result){
                    Toast.makeText(getActivity(), "Information updated", Toast.LENGTH_SHORT).show();
                    EmployeeComm employeeComm = (EmployeeComm) getActivity();
                    employeeComm.onEmployeeUpdated(name,email,state,expLevel);
                } else {
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void setEmployeeInfo(String name, String mail, String status, String experienceLevel){
        etEmpName.setText(name);
        etEmpMail.setText(mail);
        etEmpStatus.setText(status);
        etEmpExpLevel.setText(experienceLevel);
        btnUpdateEmployee.setVisibility(View.VISIBLE);
        btnDeleteEmployee.setVisibility(View.VISIBLE);
        tvEmpExpLevel.setVisibility(View.VISIBLE);
        tvEmpStatus.setVisibility(View.VISIBLE);
        tvEmpMail.setVisibility(View.VISIBLE);
        tvEmpName.setVisibility(View.VISIBLE);
        etEmpExpLevel.setVisibility(View.VISIBLE);
        etEmpMail.setVisibility(View.VISIBLE);
        etEmpStatus.setVisibility(View.VISIBLE);
        etEmpName.setVisibility(View.VISIBLE);
    }


}
