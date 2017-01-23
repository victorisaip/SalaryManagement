package com.example.victo.salarymanagement.Interfaces;

/**
 * Created by victo on 1/16/2017.
 */

public interface EmployeeComm {

    void setEmployeeInformation(String name, String email, String states, String experienceLevel);

    void onEmployeeDeleted(String employeeEmail);

}
