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

import com.example.victo.salarymanagement.POJOs.User;
import com.example.victo.salarymanagement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victo on 1/16/2017.
 */

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {
    private static String TAG = "EmployeeAdapter";
    private List<User> employeesList;
    final private ListItemClickListener mOnClickListener;
    Context context;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvEmployeeName;
        private TextView tvEmployeeMail;
        public LinearLayout linearLayout;
        private CardView cv;


        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv_employee);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.employeeInformation1);
            tvEmployeeName = (TextView) itemView.findViewById(R.id.tvEmployeeName);
            tvEmployeeMail = (TextView) itemView.findViewById(R.id.tvEmployeeMail);
            itemView.setOnClickListener(this);
            AssetManager assetManager = context.getAssets();
            Typeface regular = Typeface.createFromAsset(assetManager,"SourceSansPro-Regular.otf");
            tvEmployeeName.setTypeface(regular);
            tvEmployeeMail.setTypeface(regular);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: ");
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public EmployeesAdapter(List<User> employeesList, ListItemClickListener mOnClickListener, Context context) {
        this.employeesList = employeesList;
        this.mOnClickListener = mOnClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = employeesList.get(position);
        holder.tvEmployeeName.setText(user.getName());
        holder.tvEmployeeMail.setText(user.getEmail());
        holder.linearLayout.removeAllViews();
        holder.linearLayout.addView(holder.tvEmployeeName);
        holder.linearLayout.addView(holder.tvEmployeeMail);
    }

    @Override
    public int getItemCount() {
        return employeesList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public List<User> getEmployeesList(){
        return this.employeesList;
    }

    public void removeItem(int position) {
        employeesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,employeesList.size());
    }

    public void updateItem(int position,String name,String email,String status,String expLevel){
        employeesList.get(position).setEmail(email);
        employeesList.get(position).setName(name);
        if(expLevel.equals("jr")){
            employeesList.get(position).setExperienceLevel(0);
        } else {
            if(expLevel.equals("sr")){
                employeesList.get(position).setExperienceLevel(1);
            } else {
                if ( expLevel.equals("exp")){
                    employeesList.get(position).setExperienceLevel(2);
                } else {
                    employeesList.get(position).setExperienceLevel(-1);
                }
            }
        }
        employeesList.get(position).setState(status);
        notifyItemChanged(position);
    }




}
