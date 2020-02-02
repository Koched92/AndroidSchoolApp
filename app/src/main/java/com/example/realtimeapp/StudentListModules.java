package com.example.realtimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentListModules extends AppCompatActivity {

    GridLayout mainGrid;
    TextView textGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_list_modules);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        textGrid = (TextView) findViewById(R.id.textGrid);

        setSingleEvent(mainGrid);


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

                        textGrid.setText("Modules "+userID);

                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for(int i=0;i<mainGrid.getChildCount();i++){

            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours d'Anglais",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), AnglaisM.class));
                    } else if (finalI == 1){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours de Chime",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ChimieM.class));
                    }else if (finalI == 2){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours de Français",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), FrancaisM.class));
                    }else if (finalI == 3){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours de Géographie",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), GeographieM.class));
                    }else if (finalI == 4){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours d'Histoire",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HistoireM.class));
                    }else if (finalI == 5){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours de Math",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MathM.class));
                    }else if (finalI == 6){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours de Physique",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), PhysiqueM.class));
                    }else if (finalI == 7){
                        Toast.makeText(StudentListModules.this, "Bienvenue au cours d'SVT",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SvtM.class));
                    }
                }
            });
        }
    }
}
