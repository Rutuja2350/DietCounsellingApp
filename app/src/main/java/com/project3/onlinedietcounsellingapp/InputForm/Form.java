package com.project3.onlinedietcounsellingapp.InputForm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.project3.onlinedietcounsellingapp.Authentication.Login;
import com.project3.onlinedietcounsellingapp.Client;
import com.project3.onlinedietcounsellingapp.R;

public class Form extends AppCompatActivity {

    Button mformNextBtn;
    EditText mAddress, mAge, mHeight, mWeight;

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.femaleRadioBtn:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.maleRadioBtn:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.otherRadioBtn:
                if (checked)
                    // Pirates are the best
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        mAddress                = findViewById(R.id.adressText);
        mAge                    = findViewById(R.id.ageText);
        mHeight                 = findViewById(R.id.heightText);
        mWeight                 = findViewById(R.id.weightText);

//        if(isValid()) {
            mformNextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String address = mAddress.getText().toString().trim();
                    String age     = mAge.getText().toString().trim();
                    String height  = mHeight.getText().toString().trim();
                    String weight  = mWeight.getText().toString().trim();

                    Client client = new Client();
                    client.setForm1(address, age, height, weight);
                    Log.i("TAG", "CLIENT OBJECT = "+client.toString());

                    startActivity(new Intent(getApplicationContext(), Form2.class));
                }
            });
//        }
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
//
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