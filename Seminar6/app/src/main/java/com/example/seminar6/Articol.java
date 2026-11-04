package com.example.seminar6;

import java.io.Serializable;

public class Articol implements Serializable {
    String Nume;
    double Pret;

    public Articol(String nume, double pret) {
        Nume = nume;
        Pret = pret;
    }

    public Articol() {
        Nume="";
        Pret=0;
    }

    @Override
    public String toString() {
        return "Articol{" + "Nume='" + Nume + '\'' + ", Pret=" + Pret + '}';
    }

}
