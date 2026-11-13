package com.example.app2;

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

    ArrayList<Auto>listaAuto=new ArrayList<>();
    ListView listaEcran;

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
            Auto a=new Auto();
            a.Marca="Marca:"+ rnd.nextInt(10);
            a.Pret=rnd.nextInt(9000)/1.0;
            listaAuto.add(a);
        }

        //sortare crescatoare
        Collections.sort(listaAuto, new Comparator<Auto>() {
            @Override
            public int compare(Auto o1, Auto o2) {
                if(o1.Pret>o2.Pret)
                    return 1;
                else if(o1.Pret<o2.Pret)
                    return -1;
                else return 0;
            }
        });

        listaEcran=findViewById(R.id.listView);
        listaEcran.setOnItemClickListener(this);
        listaEcran.setOnItemLongClickListener(this);

        ArrayAdapter<Auto> adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaAuto);
        listaEcran.setAdapter(adaptor);
    }

    public void doAdd(View view) {
        Intent A2=new Intent(this, MainActivity2.class);
        startActivityForResult(A2,REQUEST_ADD);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent A2=new Intent(this, MainActivity2.class);
        A2.putExtra("editAuto",listaAuto.get(position));
        startActivityForResult(A2,REQUEST_EDIT);

        pozitie=position;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Auto element=listaAuto.get(position);
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
                        listaAuto.remove(element);
                        ArrayAdapter<Auto> adaptor=(ArrayAdapter<Auto>) listaEcran.getAdapter();
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
            Auto a=(Auto)data.getSerializableExtra("addAuto");
            if(a!=null)
            {
                ArrayAdapter<Auto>adaptor=(ArrayAdapter<Auto>) listaEcran.getAdapter();
                listaAuto.add(a);

                Collections.sort(listaAuto, new Comparator<Auto>() {
                    @Override
                    public int compare(Auto o1, Auto o2) {
                        if(o1.Pret>o2.Pret)
                            return 1;
                        else if(o1.Pret<o2.Pret)
                            return -1;
                        else return 0;
                    }
                });

                adaptor.notifyDataSetChanged();
            }
        }

        if(requestCode==REQUEST_EDIT && resultCode==RESULT_OK && data!=null)
        {
            Auto a=(Auto)data.getSerializableExtra("editAuto");
            listaAuto.get(pozitie).Marca=a.Marca;
            listaAuto.get(pozitie).Pret=a.Pret;

            ArrayAdapter<Auto> adaptor=(ArrayAdapter<Auto>) listaEcran.getAdapter();

            Collections.sort(listaAuto, new Comparator<Auto>() {
                @Override
                public int compare(Auto o1, Auto o2) {
                    if(o1.Pret>o2.Pret)
                        return 1;
                    else if(o1.Pret<o2.Pret)
                        return -1;
                    else return 0;
                }
            });

            adaptor.notifyDataSetChanged();
        }
    }


}