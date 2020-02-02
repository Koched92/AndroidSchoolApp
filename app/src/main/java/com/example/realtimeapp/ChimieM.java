package com.example.realtimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChimieM extends AppCompatActivity {

    Button c1, c2;
    TextView textChimie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_chimie_m);


        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        textChimie = (TextView) findViewById(R.id.textChimie);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChimieM.this, "Bienvenue au cours c1",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), OptionsCours.class));


                String cours = textChimie.getText().toString();
                String c = c1.getText().toString();
                //startActivity(new Intent(getApplicationContext(), OptionsCours.class));

                Intent ii = new Intent(getApplicationContext(), OptionsCours.class);
                ii.putExtra("cours", cours);
                ii.putExtra("c", c);
                startActivity(ii);

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChimieM.this, "Bienvenue au cours c2",Toast.LENGTH_SHORT).show();
                String cours = textChimie.getText().toString();
                String c = c2.getText().toString();
                //startActivity(new Intent(getApplicationContext(), OptionsCours.class));

                Intent ii = new Intent(getApplicationContext(), OptionsCours.class);
                ii.putExtra("cours", cours);
                ii.putExtra("c", c);
                startActivity(ii);
            }
        });

    }
}
