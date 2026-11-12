package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

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
    }

    public void onSave(View view) {
        EditText etNume=findViewById(R.id.etNume);
        EditText etVarsta=findViewById(R.id.etVarsta);
        EditText etMedie=findViewById(R.id.etMedie);
        EditText etGrupa=findViewById(R.id.etGrupa);

        if(etNume.getText().toString().trim().isEmpty() || etGrupa.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "Nume/Grupa nespecificate", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.parseInt(etVarsta.getText().toString())<18 && Integer.parseInt(etVarsta.getText().toString())>120){
            Toast.makeText(this,"Varsta trebuie sa fie intre 18 si 120",Toast.LENGTH_SHORT).show();
            return;
        }

        if(Double.parseDouble(etMedie.getText().toString())<1 && Double.parseDouble(etMedie.getText().toString())>10)
        {
            Toast.makeText(this,"Media trebuie sa fie intre 1 si 10",Toast.LENGTH_SHORT).show();
            return;
        }

        Student s=new Student();
        s.nume=etNume.getText().toString();
        s.grupa=etGrupa.getText().toString();
        s.varsta=Integer.parseInt(etVarsta.getText().toString());
        s.medie=Double.parseDouble(etMedie.getText().toString());

        Intent intent=getIntent();
        intent.putExtra("addStudent",s);
        setResult(RESULT_OK,intent);
        finish();



    }
}