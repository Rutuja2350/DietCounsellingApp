package com.project3.onlinedietcounsellingapp;

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

    public String getAge() { return Age; }

    public String getHeight() {
        return Height;
    }

    public String getWeight() {
        return Weight;
    }

    public String getGender() { return Gender; }

    public String getFinancialStatus() { return FinancialStatus; }

    public String getHealthIssues() {
        return HealthIssues;
    }

    public String getMedications() {
        return Medications;
    }

    public String getExpectations() {
        return Expectations;
    }

}
