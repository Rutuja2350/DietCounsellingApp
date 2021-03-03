package com.project3.onlinedietcounsellingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MyProfile extends toolbar {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    EditText name,email,phone,address,age,expectations,financialStatus,gender,healthIssues,height,weight,medications,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid(); //get id of current user

        DocumentReference documentReference = fStore.collection("user").document(userId);  //retrieving data using userID
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                name.setText(documentSnapshot.getString("fName"));     //these are keys from firebase, where values are stored as key-value pair
                email.setText(documentSnapshot.getString("email"));
                phone.setText(documentSnapshot.getString("phone"));    //replacing data on main screen
                address.setText(documentSnapshot.getString("address"));
                age.setText(documentSnapshot.getString("age"));
                expectations.setText(documentSnapshot.getString("expectations"));
                financialStatus.setText(documentSnapshot.getString("financialStatus"));
                gender.setText(documentSnapshot.getString("gender"));
                healthIssues.setText(documentSnapshot.getString("healthIssues"));
                height.setText(documentSnapshot.getString("height"));
                weight.setText(documentSnapshot.getString("weight"));
                medications.setText(documentSnapshot.getString("medications"));

            }
        });
    }
}