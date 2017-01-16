package com.example.victo.salarymanagement.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    private List<User> employeesList;
    final private ListItemClickListener mOnClickListener;
    Context context;

    public EmployeesAdapter(List<User> employeesList, ListItemClickListener mOnClickListener, Context context) {
        this.employeesList = employeesList;
        this.mOnClickListener = mOnClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_information, parent, false);
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvEmployeeName;
        private TextView tvEmployeeMail;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.employeeInformation);
            tvEmployeeName = (TextView) itemView.findViewById(R.id.tvEmployeeName);
            tvEmployeeMail = (TextView) itemView.findViewById(R.id.tvEmployeeMail);
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

    public List<User> getEmployeesList(){
        return this.employeesList;
    }
}
