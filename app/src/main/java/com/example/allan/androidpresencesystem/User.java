package com.example.allan.androidpresencesystem;

/**
 * Created by Allan on 31/10/2017.
 */

public class User {

    private String email;
    private String status;


    public User() {
    }

    public User(String email, String status) {
        this.email = email;
        this.status = status;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
