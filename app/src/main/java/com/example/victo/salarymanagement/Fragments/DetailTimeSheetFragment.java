package com.example.victo.salarymanagement.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.victo.salarymanagement.DatabaseManager.DatabaseManager;
import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailTimeSheetFragment extends Fragment {

    TextView myTextView;
    public DetailTimeSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_time_sheet, container, false);
        myTextView = (TextView) view.findViewById(R.id.tvDetailTimeSheet);


        // Inflate the layout for this fragment
        return view;
    }

    public void setMyTextViewTo(String s){
        myTextView.setText(s);
    }

}
