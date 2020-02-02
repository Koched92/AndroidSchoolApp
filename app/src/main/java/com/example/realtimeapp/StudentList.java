package com.example.realtimeapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class StudentList extends ArrayAdapter<Student> {
    Button btnStudentCours;


    private Activity context;
    private List<Student> studentList;

    public StudentList(Activity context, List<Student> studentList) {
        super(context, R.layout.activity_student_list, studentList);
        this.context = context;
        this.studentList = studentList;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_student_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewLevel = (TextView) listViewItem.findViewById(R.id.textViewlevel);
        TextView textViewMP = (TextView) listViewItem.findViewById(R.id.textViewMP);
        Button btnStudentCours = (Button) listViewItem.findViewById(R.id.btnStudentCours);



        Student student = studentList.get(position);
        textViewName.setText(student.getStudentLName());
        textViewLevel.setText(student.getLevel());
        textViewEmail.setText(student.getStudentEmail());
        textViewMP.setText(student.getStudentPW());

        btnStudentCours.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                    Intent intent = new Intent(view.getContext(), SignInActivity.class);
                    context.startActivity(intent);
            }
        });

        return listViewItem;
    }
}
