package com.example.realtimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class testretirieveCOurse extends AppCompatActivity {
    TextView textViewStudentName, urlview;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testretirieve_course);
        textViewStudentName = (TextView) findViewById(R.id.textViewStudentName);
        urlview = (TextView) findViewById(R.id.urlview);
        String currentuser = FirebaseAuth.getInstance().getUid();

/////////////////////////////
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

                       final String userID = childSnapshot.child(userKey).child("level").getValue(String.class);

                        textViewStudentName.setText(userID);

                        //////////affichage url pour un niveau précis


                        DatabaseReference fireReferencem = FirebaseDatabase.getInstance().getReference("modules2");
                        fireReferencem.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){

                                    if(childSnapshot.getKey().equals(userID)) {
                                       String url = childSnapshot.child("Math").child("c1").child("pdfCours").getValue(String.class);
                                       urlview.setText(url);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                        ////////////
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

}