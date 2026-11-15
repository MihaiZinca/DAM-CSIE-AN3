package com.example.app10;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {

    ArrayList<Carte> listaCarte=new ArrayList<>();
    ListView listaEcran;

    int REQUEST_ADD=111;
    int REQUEST_EDIT=222;
    int pozitie;

    String appCode;
    boolean codeVerifcationOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        SharedPreferences SP=getSharedPreferences("APP_ACCESS",MODE_PRIVATE);
        appCode=SP.getString("app_code","");

        AlertDialog.Builder AD=new AlertDialog.Builder(this);
        if(appCode.isEmpty())
            AD.setTitle("Alegeti un cod de acces");
        else
            AD.setTitle("Introduceti codul de acces");

        AD.setCancelable(false);

        EditText ET=new EditText(this);
        ET.setHint("Scrieti codul de acces aici...");
        ET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        AD.setView(ET);

        AD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                codeVerifcationOK=true;
                if(appCode.isEmpty())
                {
                    appCode=ET.getText().toString();
                    SharedPreferences.Editor editor=SP.edit();
                    editor.putString("app_code",appCode);
                    editor.apply();
                }

                if(!appCode.equals(ET.getText().toString()))
                {
                    ET.setText("");
                    ET.setHint("Scrieti codul de acces aici..");
                    dialog.cancel();
                }
                else
                    dialog.dismiss();
            }
        });

        AD.setNegativeButton("Renunt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        AD.setOnDismissListener(this);
        AD.setOnCancelListener(this);

        AD.show();

    }

    public void onAdd(View view) {
        Intent A2=new Intent(this, MainActivity2.class);
        startActivityForResult(A2,REQUEST_ADD);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent A2=new Intent(this, MainActivity2.class);
        A2.putExtra("editCarte",listaCarte.get(position));
        startActivityForResult(A2,REQUEST_EDIT);
        pozitie=position;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_ADD && resultCode==RESULT_OK && data!=null)
        {
            Carte c=(Carte) data.getSerializableExtra("addCarte");
            if(c!=null)
            {
                ArrayAdapter<Carte>adaptor=(ArrayAdapter<Carte>)listaEcran.getAdapter();
                listaCarte.add(c);

                Collections.sort(listaCarte, new Comparator<Carte>() {
                    @Override
                    public int compare(Carte o1, Carte o2) {
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
            Carte c=(Carte)data.getSerializableExtra("editCarte");
            listaCarte.get(pozitie).Titlu=c.Titlu;
            listaCarte.get(pozitie).Pret=c.Pret;
            listaCarte.get(pozitie).NrPagini=c.NrPagini;
            listaCarte.get(pozitie).Categorie=c.Categorie;

            ArrayAdapter<Carte>adaptor=(ArrayAdapter<Carte>)listaEcran.getAdapter();
            Collections.sort(listaCarte, new Comparator<Carte>() {
                @Override
                public int compare(Carte o1, Carte o2) {
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


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Carte element=listaCarte.get(position);

        AlertDialog AD=new AlertDialog.Builder(MainActivity.this)
                .setTitle(element.toString())
                .setMessage("Siguri Doriti Stergerea?")
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
                        listaCarte.remove(element);
                        ArrayAdapter<Carte>adaptor=(ArrayAdapter<Carte>) listaEcran.getAdapter();
                        adaptor.notifyDataSetChanged();
                        dialog.cancel();
                    }
                })
                .create();
        AD.show();

        return true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

        if(!codeVerifcationOK)
            return;

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random rnd=new Random();
        String[] categorii = {"Roman", "Tehnica", "Istorie"};
        for(int i=0;i<10;i++)
        {
            Carte c=new Carte();
            c.Titlu="Titlu:#"+rnd.nextInt(1000);
            c.Pret=rnd.nextInt(9999)/100;
            c.NrPagini= rnd.nextInt(999);
            c.Categorie=categorii[rnd.nextInt(categorii.length)];
            listaCarte.add(c);
        }

        Collections.sort(listaCarte, new Comparator<Carte>() {
            @Override
            public int compare(Carte o1, Carte o2) {
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
        ArrayAdapter<Carte> adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaCarte);
        listaEcran.setAdapter(adaptor);

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        codeVerifcationOK=false;
        ((AlertDialog)dialog).show();
    }

    public void onCategorii(View view) {
        Intent A3=new Intent(this, MainActivity3.class);
        startActivity(A3);
    }
}