package com.example.realtimeapp;

public class Student {
    private String studentName;
    private String studentLName;
    private String studentDateN;
    private String studentEmail;
    private String studentPW;
    private String level;

    public Student(){

    }


    public Student( String studentName, String studentLName, String studentDateN, String studentEmail, String studentPW, String level) {
        this.studentName = studentName;
        this.studentLName = studentLName;
        this.studentDateN = studentDateN;
        this.studentEmail = studentEmail;
        this.studentPW = studentPW;
        this.level = level;
    }




    public String getStudentName() {
        return studentName;
    }

    public String getLevel() {
        return level;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public String getStudentDateN() {
        return studentDateN;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentPW() {
        return studentPW;
    }
}
