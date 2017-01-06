package com.example.victo.salarymanagement;

/**
 * Created by victo on 1/1/2017.
 */

public class User {
    private String id;
    private String email;
    private String password;
    private String name;
    private String businessRole;
    private String state;
    private int attemptsOfLogIn;

    public User(String email, String password, String name, String businessRole) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.businessRole = businessRole;
        this.state = "active";
        this.attemptsOfLogIn = 0;
    }

    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessRole() {
        return businessRole;
    }

    public void setBusinessRole(String businessRole) {
        this.businessRole = businessRole;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAttemptsOfLogIn() {
        return attemptsOfLogIn;
    }

    public void setAttemptsOfLogIn(int attemptsOfLogIn) {
        this.attemptsOfLogIn = attemptsOfLogIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", businessRole='" + businessRole + '\'' +
                ", state='" + state + '\'' +
                ", attemptsOfLogIn=" + attemptsOfLogIn +
                '}';
    }
}
