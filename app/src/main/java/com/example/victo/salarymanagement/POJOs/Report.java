package com.example.victo.salarymanagement.POJOs;

/**
 * Created by victo on 1/20/2017.
 */

public class Report {
    private String totalHours;
    private String paymentRate;
    private String startDate;
    private String endDate;
    private String payment;

    public Report(String totalHours, String paymentRate, String startDate, String endDate, String payment) {
        this.totalHours = totalHours;
        this.paymentRate = paymentRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payment = payment;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Report{" +
                "totalHours='" + totalHours + '\'' +
                ", paymentRate='" + paymentRate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}