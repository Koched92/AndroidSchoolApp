package com.example.realtimeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFLayout extends AppCompatActivity {

    private TextView text1;
    private PDFView pdfView;
    private FirebaseDatabase database=FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pdflayout);
        pdfView=(PDFView)findViewById(R.id.pdfview);
        text1=(TextView)findViewById(R.id.text1);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDb = mDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        final String userKey = user.getUid();
        Intent iii = getIntent();
        final String cours = iii.getStringExtra("cours");
        assert cours!=null;
        final String c = iii.getStringExtra("c");
        assert c!=null;
        DatabaseReference fireReference = FirebaseDatabase.getInstance().getReference("students");

        fireReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    if(childSnapshot.hasChild(userKey)) {

                        final String userID = childSnapshot.child(userKey).child("level").getValue(String.class);


                        //////////affichage url pour un niveau précis
                        DatabaseReference fireReferencem = FirebaseDatabase.getInstance().getReference("modules2");
                        fireReferencem.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()){

                                    if(childSnapshot.getKey().equals(userID)) {
                                        String value = childSnapshot.child(cours.replaceAll("\\s+","")).child(c.replaceAll("\\s+","")).child("pdfCours").getValue(String.class);
                                        text1.setText(value);
                                      String url=text1.getText().toString();
                                      new RetrivePdfStream().execute(url);
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

    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try{
                URL url = new URL (strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode()==200){

                    inputStream=new BufferedInputStream(urlConnection.getInputStream());
                }

            }catch (IOException e){
                return  null;
            }
            return inputStream;
        }
        protected void onPostExecute(InputStream inputStream){
            pdfView.fromStream(inputStream).load();
        }
    }
}
