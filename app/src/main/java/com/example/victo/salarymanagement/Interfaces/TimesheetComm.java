package com.example.victo.salarymanagement.Interfaces;

import com.example.victo.salarymanagement.POJOs.Timesheet;

/**
 * Created by victo on 1/13/2017.
 */

public interface TimesheetComm {
    public void setTextToTimeSheet(String startDate,
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
                                   String actualDate);

}
