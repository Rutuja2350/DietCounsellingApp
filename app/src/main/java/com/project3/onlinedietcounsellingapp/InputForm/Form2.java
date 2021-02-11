package com.project3.onlinedietcounsellingapp.InputForm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project3.onlinedietcounsellingapp.Client;
import com.project3.onlinedietcounsellingapp.HomeActivity;
import com.project3.onlinedietcounsellingapp.R;

import java.util.HashMap;

public class Form2 extends AppCompatActivity {

    String healthIssues, medications, expectations;
    String role="client";

    Button mform2DoneBtn;
    TextView mhealthIssueText,mmedicationsText,mexpectationsText;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth Fauth;
    DatabaseReference databaseReference,data;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.financialGoodRadioBtn:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.financialFairRadioBtn:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.financialPoorRadioBtn:
                if (checked)
                    // Pirates are the best
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        mform2DoneBtn            = findViewById(R.id.form2DoneBtn);
        mhealthIssueText         = findViewById(R.id.healthIssueText);
        mmedicationsText         = findViewById(R.id.medicationsText);
        mexpectationsText        = findViewById(R.id.expectationsText);
        Fauth                    = FirebaseAuth.getInstance();

        databaseReference = firebaseDatabase.getInstance().getReference("clientInfo");
        Fauth = FirebaseAuth.getInstance();

        mform2DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                healthIssues     = mhealthIssueText.getText().toString().trim();
                medications      = mmedicationsText.getText().toString().trim();
                expectations     = mexpectationsText.getText().toString().trim();

                Client client = new Client();
                client.setForm2(healthIssues,medications,expectations);

                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                databaseReference=FirebaseDatabase.getInstance().getReference("ClientInfo").child(userId);

                final HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("Role",role);

                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        HashMap<String,String> hashMap1=new HashMap<>();
                        hashMap1.put("Address",client.getAddress());
                        hashMap1.put("Age",client.getAge());
                        hashMap1.put("Height",client.getHeight());
                        hashMap1.put("Weight",client.getWeight());
                        hashMap1.put("Health Issues",healthIssues);
                        hashMap1.put("Medications",medications);
                        hashMap1.put("Expectations",expectations);

//                        firebaseDatabase.getInstance().getReference("clientInfo").
//                                child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
//                                setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                            }
//                        });
                    }
                });

                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }
}