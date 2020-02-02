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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParentSignUp extends AppCompatActivity {

    EditText txtNom, txtPrenom, txtNaissance, txtEmail, txtMP, txtTel;
    Button btnValider, btnConnexion;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_parent_sign_up);

        txtNom = (EditText)findViewById(R.id.txtNom);
        txtPrenom = (EditText)findViewById(R.id.txtPrenom);
        txtNaissance = (EditText)findViewById(R.id.txtNaissance);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtMP = (EditText)findViewById(R.id.txtMP);
        txtTel = (EditText)findViewById(R.id.txtTel);
        btnValider = (Button)findViewById(R.id.btnValider);
        btnConnexion = (Button)findViewById(R.id.btnConnexion);

        databaseReference = FirebaseDatabase.getInstance().getReference("Parents");
        fAuth = FirebaseAuth.getInstance();

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nom=txtNom.getText().toString();
                final String prenom=txtPrenom.getText().toString();
                final String dateN=txtNaissance.getText().toString();
                final String email=txtEmail.getText().toString();
                String password=txtMP.getText().toString();
                final String tel=txtTel.getText().toString();

                if(TextUtils.isEmpty(nom)){
                    Toast.makeText(ParentSignUp.this, "Please enter nom", Toast.LENGTH_SHORT).show();
                }  if(TextUtils.isEmpty(prenom)){
                    Toast.makeText(ParentSignUp.this, "Please enter prenom", Toast.LENGTH_SHORT).show();
                }  if(TextUtils.isEmpty(dateN)){
                    Toast.makeText(ParentSignUp.this, "Please enter date de naissance", Toast.LENGTH_SHORT).show();
                }  if(TextUtils.isEmpty(email)){
                    Toast.makeText(ParentSignUp.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                }  if(TextUtils.isEmpty(password)){
                    Toast.makeText(ParentSignUp.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }  if(TextUtils.isEmpty(tel)){
                    Toast.makeText(ParentSignUp.this, "Please enter tel", Toast.LENGTH_SHORT).show();
                }

                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(ParentSignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Parent information = new Parent(
                                            nom,
                                            prenom,
                                            dateN,
                                            email,
                                            tel
                                    );

                                    FirebaseDatabase.getInstance().getReference("Parent").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ParentSignUp.this, "Registred", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                                        }
                                    });

                                } else {

                                }
                            }
                        });
            }
        });

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });


    }
}
