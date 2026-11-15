package com.example.app10;

import java.io.Serializable;

public class Carte implements Serializable {

    String Titlu;
    double Pret;
    int NrPagini;
    String Categorie;

    public Carte(String titlu, double pret, int nrPagini, String categorie) {
        Titlu = titlu;
        Pret = pret;
        NrPagini = nrPagini;
        Categorie = categorie;
    }

    public Carte() {
        Titlu="";
        Pret=0;
        NrPagini=0;
        Categorie="";
    }

    @Override
    public String toString() {
        return Titlu + '\'' +
                ", Pret=" + Pret +
                ", NrPagini=" + NrPagini +
                "," + Categorie;
    }
}
