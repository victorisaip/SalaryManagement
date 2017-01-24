package com.example.victo.salarymanagement.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.victo.salarymanagement.Activities.SeeTimeSheetHistoryActivity;
import com.example.victo.salarymanagement.POJOs.Timesheet;

/**
 * Created by victo on 1/24/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Timesheet t = (Timesheet) intent.getSerializableExtra("Timesheet");
        Intent i = new Intent(context, SeeTimeSheetHistoryActivity.class);
        i.putExtra("Registered t",t);
        context.startActivity(i);
    }
}
