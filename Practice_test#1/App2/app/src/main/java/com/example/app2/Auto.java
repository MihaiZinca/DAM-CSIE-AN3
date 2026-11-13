package com.example.app2;

import java.io.Serializable;

public class Auto implements Serializable {

    String Marca;
    double Pret;

    public Auto(String marca, double pret) {
        Marca = marca;
        Pret = pret;
    }

    public Auto() {
        Marca="";
        Pret=0;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "Marca='" + Marca + '\'' +
                ", Pret=" + Pret +
                '}';
    }
}
