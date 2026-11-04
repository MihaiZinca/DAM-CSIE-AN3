package com.example.seminar6;

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

        Intent intent=getIntent();
        if(intent.hasExtra("editArticol")){
            Articol a=(Articol)intent.getSerializableExtra("editArticol");

            //aici sunt cele 2 zone
            EditText etNume=findViewById(R.id.etNume);
            EditText etPret=findViewById(R.id.etPret);

            etNume.setText(a.Nume);
            etPret.setText(String.valueOf(a.Pret));
            //cand apas long click pe un articol apare direct nume si pretul din lista
        }
    }

    public void onSave(View view) {

        //validare detaliilor introduse
        EditText etNume=findViewById(R.id.etNume);
        EditText etPret=findViewById(R.id.etPret);

        //utilizatorul nu a introdus nimic
        if(etNume.getText().toString().isEmpty() || etPret.getText().toString().isEmpty()){
            Toast.makeText(this, "Nume/Pret nespecificat(e)", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Double.parseDouble(etPret.getText().toString())<0) {
            Toast.makeText(this, "Pretul trebuie sa fie numeric si pozitiv", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}