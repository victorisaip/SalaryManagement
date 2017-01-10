package com.example.victo.salarymanagement.POJOs;

import java.util.Date;

/**
 * Created by victo on 1/6/2017.
 */

public class Timesheet {
    private String startDate;
    private String endDate;
    private String approver;
    private String status;
    private String numberOfHours;
    private Date actualDate;

    public Timesheet(){

    }

    public Timesheet(String startDate, String approver, String numberOfHours,String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        actualDate = new Date();
        this.approver = approver;
        this.status = "registered";
        this.numberOfHours = numberOfHours;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(String numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", approver='" + approver + '\'' +
                ", status='" + status + '\'' +
                ", numberOfHours='" + numberOfHours + '\'' +
                ", actualDate=" + actualDate +
                '}';
    }

}
