package com.example.omanevent.models;

import java.io.Serializable;

public class User implements Serializable {
//    private String username;
    private String Email;
    private String FullName;
    private int mobile_number;
    private String user_type = "Regular";

    public User(){

    }

    public User(String email, String fullName, int mobile_number) {
//        this.username = username;
        Email = email;
        FullName = fullName;
        this.mobile_number = mobile_number;
        this.user_type = user_type;
    }

//    public String getUsername() {
//        return username;
//    }

    public String getEmail() {
        return Email;
    }

    public String getFullName() {
        return FullName;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public String getUser_type() {
        return user_type;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}

