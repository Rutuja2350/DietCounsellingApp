package com.project3.onlinedietcounsellingapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.project3.onlinedietcounsellingapp.Authentication.Client;
import com.project3.onlinedietcounsellingapp.Authentication.Login;
import com.project3.onlinedietcounsellingapp.Authentication.Register;
import com.project3.onlinedietcounsellingapp.Authentication.Users;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MyProfile extends toolbar {

    FirebaseFirestore fStore;
    String userId,upassword;
    private DatabaseReference mDatabase;
    EditText name,email,phone,address,age,expectations,financialStatus,gender,healthIssues,height,weight,medications;
    Button update,delete;
    DatabaseReference dataa;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.pname);
        email = findViewById(R.id.pemail);
        phone = findViewById(R.id.pphone);
//        password = findViewById(R.id.password);
        address = findViewById(R.id.paddress);
        age = findViewById(R.id.page);
        expectations = findViewById(R.id.pexpectations);
        financialStatus = findViewById(R.id.pfinancialStatus);
        gender = findViewById(R.id.pgender);
        healthIssues = findViewById(R.id.phealthIssues);
        height = findViewById(R.id.pheight);
        weight = findViewById(R.id.pweight);
        medications = findViewById(R.id.pmedications);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
        userId = fAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();;

        try {
            databaseReference.child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users users = snapshot.getValue(Users.class);
                    name.setText(users.getFname());
                    Log.i("OOOOO","users.getFname()="+users.getFname());
                    email.setText(users.getEmail());
                    phone.setText(users.getPhone());
                    upassword = users.getPassword();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("EROORRR","ERROR: "+error);
                }
            });

            databaseReference.child("ClientInfo").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Client client = snapshot.getValue(Client.class);
                    address.setText(client.getAddress());
                    Log.i("OOOOO","client.getAddress()="+client.getAddress());
                    age.setText(client.getAge());
                    expectations.setText(client.getExpectations());
                    financialStatus.setText(client.getFinancialStatus());
                    gender.setText(client.getGender());
                    healthIssues.setText(client.getHealthIssues());
                    height.setText(client.getHeight());
                    weight.setText(client.getWeight());
                    medications.setText(client.getMedications());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.i("EROORRR","ERROR: "+error);
                }
            });

            //update user
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //updating user
                    String uemail = email.getText().toString().trim();
                    String uname = name.getText().toString();
                    String uphone = phone.getText().toString().trim();

                    Map<String,Object> user = new HashMap<>(); //retrieving the information using the retrieved userID
                    user.put("name", uname);
                    user.put("email", uemail);
                    user.put("phone", uphone);
                    user.put("password",upassword);

                    Users users = new Users(uname,upassword,uemail,uphone);
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    databaseReference.child("Users").child(userId).setValue(users);

                    //updating client
                    String uaddress = address.getText().toString().trim();
                    String uage = age.getText().toString().trim();
                    String uexpectations = expectations.getText().toString();
                    String ufinancialstatus = financialStatus.getText().toString().trim();
                    String ugender = gender.getText().toString().trim();
                    String uhealthissues = healthIssues.getText().toString().trim();
                    String umedications = medications.getText().toString().trim();
                    String uheight = height.getText().toString().trim();
                    String uweight = weight.getText().toString().trim();

                    Map<String,Object> client = new HashMap<>(); //retrieving the information using the retrieved userID
                    client.put("address", uaddress);
                    client.put("age", uage);
                    client.put("expectations", uexpectations);
                    client.put("financialStatus", ufinancialstatus);
                    client.put("gender", ugender);
                    client.put("healthIssues", uhealthissues);
                    client.put("medications", umedications);
                    client.put("height", uheight);
                    client.put("weight", uweight);

                    Client client1 = new Client(uaddress,uage,uheight,uweight,ugender,ufinancialstatus,uhealthissues,umedications,uexpectations);
                    databaseReference.child("ClientInfo").child(userId).setValue(client1);

                    Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            });

        }
        catch (Exception e){
            Log.i("TAG","ERROR IN MyProfile.java = "+e);
        }

    }

}