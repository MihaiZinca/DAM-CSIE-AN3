package com.example.app18;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    boolean edit_mode=false;
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

        Spinner spCategorie=findViewById(R.id.spinner);
        String[] categorii={"Alege categoria","Roman","Tehnica","Istorie"};
        ArrayAdapter<String> adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,categorii);
        spCategorie.setAdapter(adaptor);

        Intent intent=getIntent();
        if(intent.hasExtra("editCarte"))
        {
            Carte c=(Carte)intent.getSerializableExtra("editCarte");

            EditText etTitlu=findViewById(R.id.etTitlu);
            EditText etPret=findViewById(R.id.etPret);
            EditText etNrPagini=findViewById(R.id.etNrPagini);

            etTitlu.setText(c.Titlu);
            etPret.setText(String.valueOf(c.Pret));
            etNrPagini.setText(String.valueOf(c.NrPagini));

            for(int i=0;i<spCategorie.getCount();i++)
            {
                if(spCategorie.getItemAtPosition(i).equals(c.Categorie))
                {
                    spCategorie.setSelection(i);
                    break;
                }
            }
            edit_mode=true;
        }
    }

    public void doSave(View view) {
        EditText etTitlu=findViewById(R.id.etTitlu);
        EditText etPret=findViewById(R.id.etPret);
        EditText etNrPagini=findViewById(R.id.etNrPagini);
        Spinner spCategorie=findViewById(R.id.spinner);

        if(etTitlu.getText().toString().trim().isEmpty() || etPret.getText().toString().trim().isEmpty() || etNrPagini.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this,"Titlu/Pret/NrPagini nespecificate",Toast.LENGTH_SHORT).show();
            Log.e("VALIDARE","Titlu/Pret/NrPagini nespecificate");
            return;
        }
        if(etTitlu.getText().toString().trim().length()<=3)
        {
            Toast.makeText(this,"Titlu trebuie sa fie mai lung de 3 caractere",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Double.parseDouble(etPret.getText().toString())<0)
        {
            Toast.makeText(this,"Pretul nu trebuie sa fie negativ",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Integer.parseInt(etNrPagini.getText().toString())<0)
        {
            Toast.makeText(this,"Nu exista nr de pagini negative",Toast.LENGTH_SHORT).show();
            return;
        }
        if(spCategorie.getSelectedItemPosition()<=0)
        {
            Toast.makeText(this,"Alege Categoria",Toast.LENGTH_SHORT).show();
            return;
        }

        Carte c=new Carte();
        c.Titlu=etTitlu.getText().toString();
        c.Pret=Double.parseDouble(etPret.getText().toString());
        c.NrPagini=Integer.parseInt(etNrPagini.getText().toString());
        c.Categorie=spCategorie.getSelectedItem().toString();

        Intent intent=getIntent();
        if(edit_mode)
            intent.putExtra("editCarte",c);
        else
            intent.putExtra("addCarte",c);
        setResult(RESULT_OK,intent);
        finish();
    }
}