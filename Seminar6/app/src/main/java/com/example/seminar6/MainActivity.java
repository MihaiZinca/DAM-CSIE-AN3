package com.example.seminar6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ArrayList<Articol> listaArticole=new ArrayList<>();
    ListView listaEcran;

    //putem pune orice valori dar sa fie diferite
    int REQUEST_ADD=111;
    int REQUEST_EDIT=222;

    int pozitie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random rnd=new Random();
        for(int i=0;i<10;i++)
        {
            Articol a=new Articol();
            a.Nume="Articol #"+ rnd.nextInt(1000);
            a.Pret=rnd.nextInt(100)/5.0;
            listaArticole.add(a);
        }

        //sortare lista descr dupa pret
        Collections.sort(listaArticole, new Comparator<Articol>() {
            @Override
            public int compare(Articol o1, Articol o2) {
                if(o1.Pret<o2.Pret)
                    return 1; //daca vrei crescator punem return -1;
                else if(o1.Pret>o2.Pret)
                    return -1;// pt crescator punem return 1
                else
                    return 0;
            }
        });

        listaEcran=findViewById(R.id.listView);
        listaEcran.setOnItemLongClickListener(this);//cand tinem apast pe un element sa se afis cv
        ArrayAdapter<Articol> adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaArticole);
        listaEcran.setAdapter(adaptor);


    }

    public void doAdd(View view) {
        Intent A2=new Intent(this, MainActivity2.class);
        startActivityForResult(A2,REQUEST_ADD);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Intent A2=new Intent(this, MainActivity2.class);
        A2.putExtra("editArticol",listaArticole.get(position)); //pt a nu da eroare trebuie sa facem clasa serializabila
        startActivityForResult(A2,REQUEST_EDIT);

        pozitie=position; //ca sa stim valoarea la edit
        return false;
    }

    //ovveride methods din generate
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_ADD && resultCode==RESULT_OK && data!=null)
        {
            //avem de adaugat un nou element in lista
            Articol a=(Articol) data.getSerializableExtra("addArticol");
            if (a!=null)
            {
                ArrayAdapter<Articol> adaptor=(ArrayAdapter<Articol>) listaEcran.getAdapter();
                listaArticole.add(a);

                Collections.sort(listaArticole, new Comparator<Articol>() {
                            @Override
                            public int compare(Articol o1, Articol o2) {
                                if(o1.Pret<o2.Pret)
                                    return 1; //daca vrei crescator punem return -1;
                                else if(o1.Pret>o2.Pret)
                                    return -1;// pt crescator punem return 1
                                else
                                    return 0;
                            }
                        });

                adaptor.notifyDataSetChanged();

            }

        }

        if(requestCode==REQUEST_EDIT && resultCode==RESULT_OK && data!=null)
        {
            //editam o valoare existenta in lista
            Articol a=(Articol) data.getSerializableExtra("editArticol");
            listaArticole.get(pozitie).Nume=a.Nume;
            listaArticole.get(pozitie).Pret=a.Pret;

            ArrayAdapter<Articol> adaptor=(ArrayAdapter<Articol>) listaEcran.getAdapter();

            Collections.sort(listaArticole, new Comparator<Articol>() {
                @Override
                public int compare(Articol o1, Articol o2) {
                    if(o1.Pret<o2.Pret)
                        return 1; //daca vrei crescator punem return -1;
                    else if(o1.Pret>o2.Pret)
                        return -1;// pt crescator punem return 1
                    else
                        return 0;
                }
            });

            adaptor.notifyDataSetChanged();


        }
    }
}