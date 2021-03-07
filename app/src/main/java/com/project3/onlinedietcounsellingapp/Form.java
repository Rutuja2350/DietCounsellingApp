package com.project3.onlinedietcounsellingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project3.onlinedietcounsellingapp.Authentication.Client;
import com.project3.onlinedietcounsellingapp.payment.selectPlan;

public class Form extends AppCompatActivity {

    Button mFormDoneBtn;
    EditText mAddress, mAge, mHeight, mWeight,mhealthIssueText,mmedicationsText,mexpectationsText;
    String address, age, height, weight, healthIssues, medications, expectations, gender, financialStatus;
//    String role="client";

    FirebaseDatabase firebaseDatabase;
    FirebaseAuth Fauth;
    DatabaseReference databaseReference,data;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.femaleRadioBtn:
                if (checked)
                    gender =  "Female";
                    break;
            case R.id.maleRadioBtn:
                if (checked)
                    gender =  "Male";
                    break;
            case R.id.otherRadioBtn:
                if (checked)
                    gender =  "Other";
                    break;
        }
    }

    public void onRadioButtonClicked2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.financialGoodRadioBtn:
                if (checked)
                    financialStatus = "Good";
                    break;
            case R.id.financialFairRadioBtn:
                if (checked)
                    financialStatus = "Fair";
                    break;
            case R.id.financialPoorRadioBtn:
                if (checked)
                    financialStatus = "Poor";
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mAddress                = findViewById(R.id.addressText);
        mAge                    = findViewById(R.id.ageText);
        mHeight                 = findViewById(R.id.heightText);
        mWeight                 = findViewById(R.id.weightText);
        mFormDoneBtn            = findViewById(R.id.formDoneBtn);
        mhealthIssueText        = findViewById(R.id.healthIssueText);
        mmedicationsText        = findViewById(R.id.medicationsText);
        mexpectationsText       = findViewById(R.id.expectationsText);
        Fauth                   = FirebaseAuth.getInstance();

//        if(isValid()) {

        mFormDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address          = mAddress.getText().toString().trim();
                age              = mAge.getText().toString().trim();
                height           = mHeight.getText().toString().trim();
                weight           = mWeight.getText().toString().trim();
                healthIssues     = mhealthIssueText.getText().toString().trim();
                medications      = mmedicationsText.getText().toString().trim();
                expectations     = mexpectationsText.getText().toString().trim();

                Client client = new Client(address,age,height,weight,gender,financialStatus,healthIssues,medications,expectations);

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference=FirebaseDatabase.getInstance().getReference();
                databaseReference.child("ClientInfo").child(userId).setValue(client);
                startActivity(new Intent(getApplicationContext(), selectPlan.class));

            }
        });
    }

    //  VALIDATION REMAINING

//    public boolean isValid(){
//        mAddress.setErrorEnabled(false);
//        mAddress.setError("");
//        mAge.setErrorEnabled(false);
//        mAge.setError("");
//        mHeight.setErrorEnabled(false);
//        mHeight.setError("");
//        mWeight.setErrorEnabled(false);
//        mWeight.setError("");
//
//        boolean isValid=false,isValidAddress=false,isValidlAge=false,isValidEHeight=false,isValidWeight=false;
//`
//        if(TextUtils.isEmpty(mAddress))
//        {
//            Fname.setErrorEnabled(true);
//            Fname.setError("Enter First Name");
//        }
//        else
//        {
//            isValidName=true;
//        }
//    };
}