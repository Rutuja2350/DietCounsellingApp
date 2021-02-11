package com.project3.onlinedietcounsellingapp;

public class Client {
    private String Address, Age, Height, Weight, HealthIssue, Medications, Expectations;

    //Alt+ins
    public Client(String address, String age, String height, String weight, String healthIssueText, String medicationsText, String expectationsText) {
        this.Address = address;
        Age = age;
        Height = height;
        Weight = weight;
        HealthIssue = healthIssueText;
        Medications = medicationsText;
        Expectations = expectationsText;
    }

    public Client(){

    }

    public void setForm1(String address,String age,String height,String weight){
        this.Address = address;
        Age = age;
        Height = height;
        Weight = weight;
    }

    public void setForm2(String healthIssues,String medications,String expectations){
        this.HealthIssue = healthIssues;
        Medications = medications;
        Expectations = expectations;
    }

    public String getAddress() { return Address; }

    public String getAge() { return Age; }

    public String getHeight() {
        return Height;
    }

    public String getWeight() {
        return Weight;
    }

    public String getHealthIssueText() {
        return HealthIssue;
    }

    public String getMedicationsText() {
        return Medications;
    }

    public String getExpectationsText() {
        return Expectations;
    }

}
