package com.project3.onlinedietcounsellingapp.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project3.onlinedietcounsellingapp.Authentication.Login;
import com.project3.onlinedietcounsellingapp.HomeActivity;
import com.project3.onlinedietcounsellingapp.MyAccount;
import com.project3.onlinedietcounsellingapp.R;

import java.util.HashMap;

public class selectPlan extends AppCompatActivity {

    String amount;
    Button mMakePayment;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    public void onRadioButtonClicked3(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.perThousand:
                if (checked)
                    amount = "1000";
                break;
            case R.id.perThreeThousand:
                if (checked)
                    amount = "2500";
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plan);

        mMakePayment   =   findViewById(R.id.makePayment);

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("ClientInfo").child(userId).child("amountPaid");
        final HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("Amount",amount);

        mMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //E D I T   T  H I S   L I N E
//                startActivity(new Intent(getApplicationContext(), paymentPage.class));
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

    }
}