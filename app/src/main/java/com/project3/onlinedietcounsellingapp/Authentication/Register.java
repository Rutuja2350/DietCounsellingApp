package com.project3.onlinedietcounsellingapp.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project3.onlinedietcounsellingapp.Form;
import com.project3.onlinedietcounsellingapp.HomeActivity;
import com.project3.onlinedietcounsellingapp.R;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText mFullName, mPassword, mEmail, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFullName     = findViewById(R.id.fullName);
        mPassword     = findViewById(R.id.password);
        mEmail        = findViewById(R.id.email);
        mRegisterBtn  = findViewById(R.id.registerBtn);
        mLoginBtn     = findViewById(R.id.createText);
        mPhone        = findViewById(R.id.phone);

        fAuth         = FirebaseAuth.getInstance();
        fStore        = FirebaseFirestore.getInstance();
        progressBar   = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            Log.i("TAG","THERE IS ALREADY A CURRENT USER ");
            startActivity(new Intent(getApplicationContext(),Login.class));     //sending user to main activity
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","THERE IS NO CURRENT USER ");
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullName = mFullName.getText().toString();
                final String phone = mPhone.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required!");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password should be of atLeast 6 characters!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user with firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i("TAG","ENTERED THE FUNCTION");
                        if(task.isSuccessful()){
                            Log.i("TAG1","ENTERED IF");
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid(); //this is to retrieve the ID of currently loggedIn user
                            DocumentReference documentReference = fStore.collection("users") .document(userID);
                            Map<String,Object> user = new HashMap<>(); //retrieving the information using the retrieved userID
                            user.put("fName", fullName);
                            user.put("email", email);
                            user.put("phone", phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.i("TAG", "onSuccess: user Profile is created for "+ userID);
                                }
                            });
                            fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), Form.class));
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(Register.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}