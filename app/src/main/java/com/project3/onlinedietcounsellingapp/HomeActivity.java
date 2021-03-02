package com.project3.onlinedietcounsellingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.project3.onlinedietcounsellingapp.Authentication.Login;

public class HomeActivity extends AppCompatActivity {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View v = inflater.inflate(R.layout.activity_main,null);
        return v;
    }

    public void myAcc(View view) {
        startActivity(new Intent(getApplicationContext(), MyProfile.class));
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();  //logout user
        startActivity(new Intent(getApplicationContext(), Login.class)); //send user to login activity
        finish();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dropdown,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idd = item.getItemId();
        if (idd == R.id.logoutDropDownOpt) {
            logout();
            return true;
        }
        if (idd == R.id.myProfileDropDownOpt) {
            myProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout (){
        FirebaseAuth.getInstance().signOut();  //logout user
        startActivity(new Intent(getApplicationContext(), Login.class)); //send user to login activity
        finish();
    }

    private void myProfile(){
        startActivity(new Intent(getApplicationContext(), MyProfile.class));
    }

}
