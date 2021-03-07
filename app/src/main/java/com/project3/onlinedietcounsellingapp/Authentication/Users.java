package com.project3.onlinedietcounsellingapp.Authentication;

import android.widget.EditText;

public class Users {
    private String Name,Password,Email,Phone;

    public Users(String name,String password,String email,String phone){
        Name = name;
        Password = password;
        Email = email;
        Phone = phone;
    }

    public Users(){

    }

    public String getFname() { return Name; }

    public void setFname(String name) {
        Name = name;
    }

    public String getEmail() { return Email; }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() { return Phone; }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() { return Password; }

    public void setPassword(String password) {
        Password = password;
    }
}
