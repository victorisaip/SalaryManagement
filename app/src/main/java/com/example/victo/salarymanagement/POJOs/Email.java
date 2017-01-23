package com.example.victo.salarymanagement.POJOs;

/**
 * Created by User on 1/22/2017.
 */

public class Email {

    String toEmail;
    String Subject;
    String Body;

    public Email(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        Subject = subject;
        Body = body;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
}
