package com.project3.onlinedietcounsellingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.project3.onlinedietcounsellingapp.Authentication.Login;

public class HomeActivity extends toolbar {

    Button llogout, mmyprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //logout and myProfile functions both to be removed after the toolbar conflict is solved... these buttons are temporary
        llogout = findViewById(R.id.llogout);
        mmyprofile = findViewById(R.id.mmyprofile);

        llogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();  //logout user
                startActivity(new Intent(getApplicationContext(), Login.class)); //send user to login activity
                finish();
            }
        });

        mmyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyProfile.class));
            }
        });

    }

}
