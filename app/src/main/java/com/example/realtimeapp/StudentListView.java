package com.example.realtimeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentListView extends AppCompatActivity {
    ListView listViewStudents;
    List<Student> students;

    DatabaseReference databaseStudents, databaseParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_view);
        getSupportActionBar().hide();

        listViewStudents = (ListView) findViewById(R.id.listViewStudents);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseStudents = FirebaseDatabase.getInstance().getReference("students").child(currentuser);

        students = new ArrayList<>();

    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                students.clear();
                for(DataSnapshot studentSnapshot : dataSnapshot.getChildren()){
                    Student student = studentSnapshot.getValue(Student.class);
                    students.add(student);
                }

                StudentList studentListAdapter = new StudentList(StudentListView.this, students);
                listViewStudents.setAdapter(studentListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
