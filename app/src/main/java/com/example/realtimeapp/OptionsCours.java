package com.example.realtimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OptionsCours extends AppCompatActivity {

    Button btnPdf, btnVideo, btnExo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_options_cours);

        btnPdf = (Button) findViewById(R.id.btnPdf);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnExo = (Button) findViewById(R.id.btnExo);

        Intent ii = getIntent();
        final String cours = ii.getStringExtra("cours");

        final String c = ii.getStringExtra("c");


        btnPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OptionsCours.this, "Bonne lecture !",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), PDFLayout.class));

                Intent iii = new Intent(getApplicationContext(), PDFLayout.class);
                iii.putExtra("cours", cours);
                iii.putExtra("c", c);
                startActivity(iii);
            }
        });

        btnExo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OptionsCours.this, "Bonne lecture !",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), PDFLayout.class));

                Intent iii = new Intent(getApplicationContext(), PDFLayoutExo.class);
                iii.putExtra("cours", cours);
                iii.putExtra("c", c);
                startActivity(iii);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OptionsCours.this, "Bon Visionnage !",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getApplicationContext(), VideoLayout.class));

                Intent iii = new Intent(getApplicationContext(), VideoLayout.class);
                iii.putExtra("cours", cours);
                iii.putExtra("c", c);
                startActivity(iii);
            }
        });

    }
}
