package com.example.realtimeapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword ;
    Button BtnLogin, BtnReg;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        fAuth = FirebaseAuth.getInstance();
        BtnLogin = findViewById(R.id.BtnLogin);
        BtnReg = findViewById(R.id.BtnReg);


        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = txtEmail.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    txtEmail.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    txtPassword.setError("Password required.");
                    return;
                }

                if (password.length()<6){
                    txtPassword.setError("password should be >6 characters");
                    return;

                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {

                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference mDb = mDatabase.getReference();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        final String userKey = user.getUid();

                        DatabaseReference fireReference = FirebaseDatabase.getInstance().getReference("students");


                        fireReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                                    if(childSnapshot.hasChild(userKey)) {

                                        final String userID = childSnapshot.child(userKey).child("studentEmail").getValue(String.class);

                                        if (!email.equals(userID) && task.isSuccessful()){
                                            Toast.makeText(SignInActivity.this, "looool", Toast.LENGTH_SHORT).show();
                                        } else if(task.isSuccessful()  ){
                                            Toast.makeText(SignInActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                        } else{
                                            Toast.makeText(SignInActivity.this, "smt wrong", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





                    }
                });

            }

        });

        BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ParentSignUp.class);
                startActivity(intent);
            }
        });


    }
}