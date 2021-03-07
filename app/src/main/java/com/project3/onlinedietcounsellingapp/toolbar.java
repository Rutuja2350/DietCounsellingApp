package com.project3.onlinedietcounsellingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.project3.onlinedietcounsellingapp.Authentication.Login;

public class toolbar extends AppCompatActivity {

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        Log.i("TAG","INTO TOOLBAR.JAVA");
//        getMenuInflater().inflate(R.menu.dropdown, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutDropDownOpt:
                Log.i("TAG","Logout");
                logout();
                return true;

            case R.id.myProfileDropDownOpt:
                Log.i("TAG","my profile");
                myProfile();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
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