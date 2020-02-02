package com.example.realtimeapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    TextView textViewParentName;
    EditText editTextStudentName, editTextStudentLN, editTextStudentBirth, editTextStudentEmail, editTextStudentMP ;
    Spinner spinnerLevel;
    ListView listViewStudents;
    Button buttonAddStudent, buttonListStudent;

    FirebaseAuth fAuth;
    DatabaseReference databaseStudents, databaseParent;

    List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        getSupportActionBar().hide();

        textViewParentName = (TextView) findViewById(R.id.textViewParentName);
        editTextStudentName = (EditText) findViewById(R.id.editTextStudentName);
        editTextStudentLN = (EditText) findViewById(R.id.editTextStudentLN);
        editTextStudentBirth = (EditText) findViewById(R.id.editTextStudentBirth);
        editTextStudentEmail = (EditText) findViewById(R.id.editTextStudentEmail);
        editTextStudentMP = (EditText) findViewById(R.id.editTextStudentMP);
        spinnerLevel = (Spinner) findViewById(R.id.spinnerLevel);
        buttonAddStudent = (Button) findViewById(R.id.buttonAddStudent);
        buttonListStudent = (Button) findViewById(R.id.buttonListStudent);
        listViewStudents = (ListView) findViewById(R.id.listViewStudents);

        final Intent intent = getIntent();

        students = new ArrayList<>();


        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

/////////////////////////////
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDb = mDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userKey = user.getUid();

        mDb.child("Parent").child(userKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userID = dataSnapshot.child("prenom").getValue(String.class);
                textViewParentName.setText(userID);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

////////////////

        databaseStudents = FirebaseDatabase.getInstance().getReference("students").child(currentuser);
        fAuth = FirebaseAuth.getInstance();

        buttonListStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudentActivity.this, StudentListView.class);
                startActivity(intent);
            }
        });
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudent();
            }
        });

        }

    private void saveStudent(){
        final String studentName = editTextStudentName.getText().toString().trim();
        final String studentLName = editTextStudentLN.getText().toString().trim();
        final String studentBirth = editTextStudentBirth.getText().toString().trim();
        final String studentEmail = editTextStudentEmail.getText().toString().trim();
        final String studentMP = editTextStudentMP.getText().toString();
        final String level = spinnerLevel.getSelectedItem().toString();


        //////////////////////
        fAuth.createUserWithEmailAndPassword(studentEmail, studentMP)
                .addOnCompleteListener(AddStudentActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!TextUtils.isEmpty(studentName)){
                            String id = databaseStudents.push().getKey();

                            Student student = new Student(studentName, studentLName,studentBirth,studentEmail,studentMP, level);
                            String uid = task.getResult().getUser().getUid();

                            databaseStudents.child(uid).setValue(student);

                            Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(getApplicationContext(), "Student name should not be empty", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

        /////////////////////
    }




}
