package com.example.realtimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PhysiqueM extends AppCompatActivity {

    Button c1, c2;
    TextView textPhysique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_physique_m);


        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        textPhysique = (TextView) findViewById(R.id.textPhysique);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PhysiqueM.this, "Bienvenue au cours c1",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), OptionsCours.class));


                String cours = textPhysique.getText().toString();
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
                Toast.makeText(PhysiqueM.this, "Bienvenue au cours c2",Toast.LENGTH_SHORT).show();
                String cours = textPhysique.getText().toString();
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
