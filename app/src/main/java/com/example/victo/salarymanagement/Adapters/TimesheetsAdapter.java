package com.example.victo.salarymanagement.Adapters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.victo.salarymanagement.POJOs.Timesheet;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;

/**
 * Created by victo on 1/11/2017.
 */

public class TimesheetsAdapter extends RecyclerView.Adapter<TimesheetsAdapter.ViewHolder>{
    private static final String TAG = "Adapter";
    private List<Timesheet> timesheetList;
    final private ListItemClickListener mOnClickListener;

    Context context;


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView actualDate,approver,totalHours;

        public LinearLayout linearLayout;
        private CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_timesheet);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.timesheetLinearLayout);
            actualDate = (TextView) itemView.findViewById(R.id.tvRActualDate);
            approver = (TextView) itemView.findViewById(R.id.tvRApprover);
            totalHours = (TextView) itemView.findViewById(R.id.tvRTotalHours);
            itemView.setOnClickListener(this);
            AssetManager assetManager = context.getAssets();
            Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");
            actualDate.setTypeface(regular);
            approver.setTypeface(regular);
            totalHours.setTypeface(regular);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }


    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public TimesheetsAdapter(List<Timesheet> timesheetList, Context context,ListItemClickListener listener) {
        Log.d(TAG, "TimesheetsAdapter: "+timesheetList);
        mOnClickListener = listener;
        this.timesheetList = new ArrayList<>();
        this.timesheetList = timesheetList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.timesheet_card,parent,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Timesheet timesheet = timesheetList.get(position);
        holder.actualDate.setText(timesheet.getActualDate());
        holder.approver.setText(timesheet.getApprover());
        holder.totalHours.setText(timesheet.getTotalHours());
        holder.linearLayout.removeAllViews();
        holder.linearLayout.addView(holder.actualDate);
        holder.linearLayout.addView(holder.approver);
        holder.linearLayout.addView(holder.totalHours);

    }
    @Override
    public int getItemCount() {
        return timesheetList.size();
    }
    public List<Timesheet> getTimesheetList(){
        return this.timesheetList;
    }

    public void updateItem(String key,String status){
        int pos = getPos(key);
        timesheetList.get(pos).setStatus(status);
        notifyDataSetChanged();
    }

    private int getPos(String key){
        int i = 0;
        int pos = 0;
        boolean flag = false;
        while (i<timesheetList.size() && flag == false){
            if(timesheetList.get(i).getKey().equals(key)){
                pos = i;
                flag = true;
            }  else {
                i = i + 1;
            }
        }
        return pos;
    }


}
