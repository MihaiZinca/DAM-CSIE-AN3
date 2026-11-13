package com.example.app2;

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
        if (intent.hasExtra("editAuto"))
        {
            Auto a=(Auto) intent.getSerializableExtra("editAuto");

            EditText etMarca=findViewById(R.id.etMarca);
            EditText etPret=findViewById(R.id.etPret);

            etMarca.setText(a.Marca);
            etPret.setText(String.valueOf(a.Pret));

            edit_mode=true;

        }
    }

    public void onSave(View view) {
        EditText etMarca=findViewById(R.id.etMarca);
        EditText etPret=findViewById(R.id.etPret);

        if(etMarca.getText().toString().trim().isEmpty() || etPret.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this,"Marca/Pret nespecificate",Toast.LENGTH_SHORT).show();
            Log.e("VALIDARE","Marca/Pret nespecificate");
            return;
        }

        if(Double.parseDouble(etPret.getText().toString())<0)
        {
            Toast.makeText(this,"Pretul nu trebuie sa fie negativ",Toast.LENGTH_SHORT).show();
            Log.e("VALIDARE","Pretul nu trebuie sa fie negativ");
            return;
        }

        Auto a=new Auto();
        a.Marca=etMarca.getText().toString();
        a.Pret=Double.parseDouble(etPret.getText().toString());

        Intent intent=getIntent();
        if(edit_mode)
            intent.putExtra("editAuto",a);
        else
            intent.putExtra("addAuto",a);
        setResult(RESULT_OK,intent);
        finish();
    }
}