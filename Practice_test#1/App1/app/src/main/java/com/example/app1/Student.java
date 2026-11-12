package com.example.app1;

import java.io.Serializable;

public class Student implements Serializable {

    String nume;
    int varsta;
    double medie;
    String grupa;

    public Student() {
        nume="";
        varsta=0;
        medie=0;
        grupa="";
    }

    public Student(String nume, int varsta, double medie, String grupa) {
        this.nume = nume;
        this.varsta = varsta;
        this.medie = medie;
        this.grupa = grupa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", medie=" + medie +
                ", grupa='" + grupa + '\'' +
                '}';
    }
}
