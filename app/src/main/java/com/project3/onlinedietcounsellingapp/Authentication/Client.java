package com.project3.onlinedietcounsellingapp.Authentication;

import android.widget.EditText;

public class Client {
    private String Address, Age, Height, Weight, Gender, FinancialStatus, HealthIssues, Medications, Expectations;

    //Alt+ins
    public Client(String address, String age, String height, String weight, String gender, String financialStatus, String healthIssues, String medications, String expectations) {
        this.Address = address;
        Age = age;
        Height = height;
        Weight = weight;
        HealthIssues = healthIssues;
        Medications = medications;
        Expectations = expectations;
        Gender = gender;
        FinancialStatus = financialStatus;
    }


    public Client(){

    }

    public String getAddress() { return Address; }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAge() { return Age; }

    public void setAge(String age) {
        Age = age;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getGender() { return Gender; }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getFinancialStatus() { return FinancialStatus; }

    public void setFinancialStatus(String financialStatus) {
        FinancialStatus = financialStatus;
    }

    public String getHealthIssues() {
        return HealthIssues;
    }

    public void setHealthIssues(String healthIssues) {
        HealthIssues = healthIssues;
    }

    public String getMedications() {
        return Medications;
    }

    public void setMedications(String medications) {
        Medications = medications;
    }

    public String getExpectations() {
        return Expectations;
    }

    public void setExpectations(String expectations) {
        Expectations = expectations;
    }
}
