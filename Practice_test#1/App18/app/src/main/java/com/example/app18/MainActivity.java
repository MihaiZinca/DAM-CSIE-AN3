package com.example.app18;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    ArrayList<Carte> listaCarti=new ArrayList<>();
    ListView listaEcran;

    int REQUEST_ADD=111;
    int REQUEST_EDIT=222;

    int pozitie;

    String appCode;
    boolean verificationCodeOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        SharedPreferences SP=getSharedPreferences("APP_ACCES",MODE_PRIVATE);
        appCode=SP.getString("app_code","");

        AlertDialog.Builder AD=new AlertDialog.Builder(this);
        if(appCode.isEmpty())
            AD.setTitle("Introduceti un cod de acces");
        else
            AD.setTitle("Introduceti codul de acces");
        AD.setCancelable(false);

        EditText ET=new EditText(this);
        ET.setHint("Introduceti codul de acces");
        ET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        AD.setView(ET);

        AD.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                verificationCodeOk=true;
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
                    ET.setHint("Introduceti codul de acces");
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

        AD.setOnCancelListener(this);
        AD.setOnDismissListener(this);
        AD.show();
    }

    public void onAdd(View view) {
        Intent A2=new Intent(this, MainActivity2.class);
        startActivityForResult(A2,REQUEST_ADD);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent A2=new Intent(this, MainActivity2.class);
        A2.putExtra("editCarte",listaCarti.get(position));
        startActivityForResult(A2,REQUEST_EDIT);
        pozitie=position;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Carte element=listaCarti.get(position);
        AlertDialog AD=new AlertDialog.Builder(this)
                .setTitle(element.toString())
                .setMessage("Sigur Doriti Stergerea?")
                .setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,element.toString(),Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaCarti.remove(element);
                        ArrayAdapter<Carte> adaptor=(ArrayAdapter<Carte>) listaEcran.getAdapter();
                        adaptor.notifyDataSetChanged();
                        dialog.cancel();
                    }
                })
                .create();
        AD.show();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK && data != null) {
            Carte c = (Carte) data.getSerializableExtra("addCarte");
            if (c != null) {
                ArrayAdapter<Carte> adaptor = (ArrayAdapter<Carte>) listaEcran.getAdapter();
                listaCarti.add(c);

                Collections.sort(listaCarti, new Comparator<Carte>() {
                    @Override
                    public int compare(Carte o1, Carte o2) {
                        if (o1.Pret > o2.Pret)
                            return -1;
                        else if (o1.Pret < o2.Pret)
                            return 1;
                        else
                            return 0;
                    }
                });

                adaptor.notifyDataSetChanged();
            }
        }

        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK && data != null) {
            Carte c = (Carte) data.getSerializableExtra("editCarte");
            listaCarti.get(pozitie).Titlu = c.Titlu;
            listaCarti.get(pozitie).Pret = c.Pret;
            listaCarti.get(pozitie).NrPagini = c.NrPagini;
            listaCarti.get(pozitie).Categorie = c.Categorie;

            ArrayAdapter<Carte> adaptor = (ArrayAdapter<Carte>) listaEcran.getAdapter();

            Collections.sort(listaCarti, new Comparator<Carte>() {
                @Override
                public int compare(Carte o1, Carte o2) {
                    if (o1.Pret > o2.Pret)
                        return -1;
                    else if (o1.Pret < o2.Pret)
                        return 1;
                    else
                        return 0;
                }
            });

            adaptor.notifyDataSetChanged();
        }
    }

    public void onCategorii(View view) {
        Random r=new Random();
        Intent A3=new Intent(this, MainActivity3.class);
        A3.putExtra(Intent.EXTRA_TEXT,"Test"+r.nextInt(100));
        startActivity(A3);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        verificationCodeOk=false;
        ((AlertDialog)dialog).show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

        if(!verificationCodeOk)
            return;

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Random rnd=new Random();
        String[] categorii={"Roman","Tehnica","Istorie"};
        for(int i=0;i<10;i++)
        {
            Carte c=new Carte();
            c.Titlu="Titlu#"+rnd.nextInt(100);
            c.Pret=rnd.nextInt(9999)/100;
            c.NrPagini=rnd.nextInt(999);
            c.Categorie=categorii[rnd.nextInt(categorii.length)];
            listaCarti.add(c);
        }

        Collections.sort(listaCarti, new Comparator<Carte>() {
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
        ArrayAdapter<Carte>adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaCarti);
        listaEcran.setAdapter(adaptor);


        ImageView iv=findViewById(R.id.img1);

        Switch sw=findViewById(R.id.switch1);
        if(sw.isChecked())
            iv.setVisibility(View.VISIBLE);
        else
            iv.setVisibility(View.INVISIBLE);
    }

    public void doSwitch(View view) {

        ImageView iv=findViewById(R.id.img1);

        Switch sw=findViewById(R.id.switch1);
        if(sw.isChecked())
            iv.setVisibility(View.VISIBLE);
        else
            iv.setVisibility(View.INVISIBLE);

    }
}