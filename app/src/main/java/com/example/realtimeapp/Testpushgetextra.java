package com.example.realtimeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Testpushgetextra extends AppCompatActivity {

    TextView nomc, cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpushgetextra);

        nomc = (TextView) findViewById(R.id.nomc);
        cc = (TextView) findViewById(R.id.cc);


        Intent ii = getIntent();
        final String cours = ii.getStringExtra("cours");
        nomc.setText(cours);
        final String c = ii.getStringExtra("c");
        cc.setText(c);
    }
}
