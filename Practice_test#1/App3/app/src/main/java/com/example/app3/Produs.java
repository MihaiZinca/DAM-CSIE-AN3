package com.example.app3;

import java.io.Serializable;

public class Produs implements Serializable {

    String Nume;
    double Pret;

    public Produs(String nume, double pret) {
        Nume = nume;
        Pret = pret;
    }

    public Produs() {
        Nume="";
        Pret=0;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "Nume='" + Nume + '\'' +
                ", Pret=" + Pret +
                '}';
    }
}
