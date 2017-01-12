package com.example.victo.salarymanagement.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 1/11/2017.
 */

public class TimesheetsAdapter extends RecyclerView.Adapter<TimesheetsAdapter.ViewHolder>{
    private static final String TAG = "Adapter";
    private List<Timesheet> timesheetList ;
    Context context;


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView actualDate,approver,totalHours;

        public ViewHolder(View itemView) {
            super(itemView);
            actualDate = (TextView) itemView.findViewById(R.id.tvRActualDate);
            approver = (TextView) itemView.findViewById(R.id.tvRApprover);
            totalHours = (TextView) itemView.findViewById(R.id.tvRTotalHours);
        }
    }

    public TimesheetsAdapter(List<Timesheet> timesheetList, Context context) {
        Log.d(TAG, "TimesheetsAdapter: "+timesheetList);

        this.timesheetList = timesheetList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.timesheet_row,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Timesheet timesheet = timesheetList.get(position);
        holder.actualDate.setText(timesheet.getActualDate());
        holder.approver.setText(timesheet.getApprover());
        holder.totalHours.setText(timesheet.getTotalHours());
    }
    @Override
    public int getItemCount() {
        return timesheetList.size();
    }


}
