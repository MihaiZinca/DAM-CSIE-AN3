package com.example.app3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

        Intent intent=getIntent();
        if(intent.hasExtra("editProdus"))
        {
            Produs p=(Produs)intent.getSerializableExtra("editProdus");

            EditText etNume=findViewById(R.id.etNume);
            EditText etPret=findViewById(R.id.etPret);

            etNume.setText(p.Nume);
            etPret.setText(String.valueOf(p.Pret));

            edit_mode=true;
        }

    }

    public void doSave(View view) {

        EditText etNume=findViewById(R.id.etNume);
        EditText etPret=findViewById(R.id.etPret);

        if(etNume.getText().toString().trim().isEmpty() || etPret.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this,"Nume/Pret nespecificate",Toast.LENGTH_SHORT).show();
            Log.e("VALIDARE","Nume/Pret nespecificate");
            return;
        }

        if(Double.parseDouble(etPret.getText().toString())<0)
        {
            Toast.makeText(this,"Pretul nu trebuie sa fie negativ",Toast.LENGTH_SHORT).show();
            Log.e("VALIDARE","Pretul nu trebuie sa fie negativ");
            return;
        }

        Produs p=new Produs();
        p.Nume=etNume.getText().toString();
        p.Pret=Double.parseDouble(etPret.getText().toString());

        Intent intent=getIntent();
        if(edit_mode)
            intent.putExtra("editProdus",p);
        else
            intent.putExtra("addProdus",p);

        setResult(RESULT_OK,intent);
        finish();
    }
}