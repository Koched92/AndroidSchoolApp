package com.example.realtimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoixParentEtudiantInscri extends AppCompatActivity {

    ImageView StudentPic, parentPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choix_parent_etudiant_inscri);
        StudentPic = findViewById(R.id.StudentPic);
        parentPic = findViewById(R.id.parentPic);


        StudentPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixParentEtudiantInscri.this, ParentSignUp.class);
                startActivity(intent);
            }
        });

        parentPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoixParentEtudiantInscri.this, ParentSignUp.class);
                startActivity(intent);
            }
        });

    }
}
