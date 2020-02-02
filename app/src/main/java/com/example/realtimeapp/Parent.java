package com.example.realtimeapp;

public class Parent {

    String nom, prenom, dateN, email, tel;



    public Parent() {
    }

    public Parent(String nom, String prenom, String dateN, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateN = dateN;
        this.email = email;
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateN() {
        return dateN;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }
}
