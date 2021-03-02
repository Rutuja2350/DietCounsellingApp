package com.project3.onlinedietcounsellingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.project3.onlinedietcounsellingapp.Authentication.Login;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
//
//        myChildToolbar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutDropDownOpt:
                logout();
                return true;

            case R.id.myProfileDropDownOpt:
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
