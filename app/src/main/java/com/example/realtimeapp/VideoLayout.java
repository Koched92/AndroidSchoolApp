package com.example.realtimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VideoLayout extends YouTubeBaseActivity {

    private static final String TAG = "VideoLayout";
    YouTubePlayerView youTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_layout);
        Log.d(TAG, "onCreate: Starting.");
        btnPlay = (Button) findViewById(R.id.btnPlay);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youTubePlayer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: ");
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
                                                String value = childSnapshot.child(cours.replaceAll("\\s+","")).child(c.replaceAll("\\s+","")).child("videoCours").getValue(String.class);

                                                youTubePlayer.loadVideo(value);

                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: ");
            }
        };


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Initializing YouTube Player.");
                youTubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInitializedListener);

                Log.d(TAG, "onClick: Done Initializing.");
            }
        });

        }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    }




