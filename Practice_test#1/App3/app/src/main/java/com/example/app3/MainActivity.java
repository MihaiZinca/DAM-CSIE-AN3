package com.example.app3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<Produs> listaProduse=new ArrayList<>();
    ListView listaEcran;

    int REQUEST_ADD;
    int REQUEST_EDIT;
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
            Produs p=new Produs();
            p.Nume="Nume:"+rnd.nextInt(10);
            p.Pret= rnd.nextInt(100)/10.0;
            listaProduse.add(p);
        }

        //sortare desc
        Collections.sort(listaProduse, new Comparator<Produs>() {
            @Override
            public int compare(Produs o1, Produs o2) {
                if(o1.Pret>o2.Pret)
                    return -1;
                else if(o1.Pret<o2.Pret)
                    return 1;
                else
                    return 0;
            }
        });

        listaEcran=findViewById(R.id.listView);
        listaEcran.setOnItemClickListener(this);
        listaEcran.setOnItemLongClickListener(this);

        ArrayAdapter<Produs> adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaProduse);
        listaEcran.setAdapter(adaptor);

    }
    public void doAdd(View view) {
        Intent A2=new Intent(this, MainActivity2.class);
        startActivityForResult(A2,REQUEST_ADD);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent A2=new Intent(this,MainActivity2.class);
        A2.putExtra("editProdus",listaProduse.get(position));
        startActivityForResult(A2,REQUEST_EDIT);

        pozitie=position;

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Produs element=listaProduse.get(position);
        AlertDialog ad=new AlertDialog.Builder(MainActivity.this)
                .setTitle(element.toString())
                .setMessage("Sigur doriti Stergerea?")
                .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,element.toString(),Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaProduse.remove(element);
                        ArrayAdapter<Produs> adaptor=(ArrayAdapter<Produs>) listaEcran.getAdapter();
                        adaptor.notifyDataSetChanged();
                        dialog.cancel();

                    }
                })
                .create();
        ad.show();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_ADD && resultCode==RESULT_OK && data!=null)
        {
            Produs p=(Produs) data.getSerializableExtra("addProdus");
            if(p!=null)
            {
                ArrayAdapter<Produs> adaptor=(ArrayAdapter<Produs>) listaEcran.getAdapter();
                listaProduse.add(p);

                Collections.sort(listaProduse, new Comparator<Produs>() {
                    @Override
                    public int compare(Produs o1, Produs o2) {
                        if(o1.Pret>o2.Pret)
                            return -1;
                        else if(o1.Pret<o2.Pret)
                            return 1;
                        else
                            return 0;
                    }
                });

                adaptor.notifyDataSetChanged();
            }
        }

        if(requestCode==REQUEST_EDIT && resultCode==RESULT_OK && data!=null)
        {
            Produs p=(Produs)data.getSerializableExtra("editProdus");
            listaProduse.get(pozitie).Nume=p.Nume;
            listaProduse.get(pozitie).Pret=p.Pret;

            ArrayAdapter<Produs> adaptor=(ArrayAdapter<Produs>) listaEcran.getAdapter();
            Collections.sort(listaProduse, new Comparator<Produs>() {
                @Override
                public int compare(Produs o1, Produs o2) {
                    if(o1.Pret>o2.Pret)
                        return -1;
                    else if(o1.Pret<o2.Pret)
                        return 1;
                    else
                        return 0;
                }
            });
            adaptor.notifyDataSetChanged();
        }
    }


}