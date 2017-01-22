package com.example.victo.salarymanagement.POJOs;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victo on 1/6/2017.
 */

public class Timesheet implements Serializable{
    private String key;
    private String startDate;
    private String endDate;
    private String approver;
    private String status;
    private String Monday;
    private String Friday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String actualDate;
    private String totalHours;
    private String email;

    public Timesheet(){
    }

    public Timesheet(String startDate,String endDate,String approver,String actualDate,
    String Monday,String Tuesday,String Wednesday,String Thursday,String Friday,String totalHours,String email) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.actualDate = actualDate;
        this.approver = approver;
        this.Monday = Monday;
        this.Tuesday = Tuesday;
        this.Wednesday = Wednesday;
        this.Thursday = Thursday;
        this.Friday = Friday;
        this.totalHours = totalHours;
        this.status = "registered";
        this.email = email;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
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
    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String thursday) {
        Thursday = thursday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", approver='" + approver + '\'' +
                ", status='" + status + '\'' +
                ", actualDate=" + actualDate +
                '}';
    }

}
