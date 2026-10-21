package com.example.seminar4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    List<String>lista_valori=new ArrayList<>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv=(ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);


        Random rnd=new Random();

        for(int i=0;i<20;i++)
            lista_valori.add("Element"+rnd.nextInt(1000));

        ArrayAdapter<String> aa=new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lista_valori);
        lv.setAdapter(aa);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,lista_valori.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String element=lista_valori.get(position);

        AlertDialog ad=new AlertDialog.Builder(MainActivity2.this)
                .setTitle(element)
                .setMessage("Sigur doriti stergerea?")
                .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lista_valori.remove(element);
                        ArrayAdapter adaptor= (ArrayAdapter) lv.getAdapter();
                        adaptor.notifyDataSetChanged();
                        dialog.cancel();

                    }
                })
                .create();
        ad.show();

        return true;
    }
}