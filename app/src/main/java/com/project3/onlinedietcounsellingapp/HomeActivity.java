package com.project3.onlinedietcounsellingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.project3.onlinedietcounsellingapp.Authentication.Login;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();  //logout user
        startActivity(new Intent(getApplicationContext(), Login.class)); //send user to login activity
        finish();
    }
}
