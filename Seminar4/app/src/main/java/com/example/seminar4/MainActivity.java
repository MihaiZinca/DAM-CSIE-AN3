package com.example.seminar4;

import static com.example.seminar4.R.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



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


        EditText et=(EditText) findViewById(id.editTextTextMultiLine);
        et.setText(et.getText()+"/n"+"onCreate()");
        Log.e("###A1","onCreate()");
    }
    @Override
    protected void onStart() {
        super.onStart();EditText et=(EditText) findViewById(R.id.editTextTextMultiLine);
        et.setText(et.getText()+"/n"+"onStart()");
        Log.e("###A1","onStart()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        EditText et=(EditText) findViewById(R.id.editTextTextMultiLine);
        et.setText(et.getText()+"/n"+"onStop()");
        Log.e("###A1","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EditText et=(EditText) findViewById(R.id.editTextTextMultiLine);
        et.setText(et.getText()+"/n"+"onDestroy()");
        Log.e("###A1","onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText et=(EditText) findViewById(R.id.editTextTextMultiLine);
        et.setText(et.getText()+"/n"+"onPause()");
        Log.e("###A1","onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText et=(EditText) findViewById(R.id.editTextTextMultiLine);
        et.setText(et.getText()+"/n"+"onResume()");
        Log.e("###A1","onResume()");
    }

    public void doEXIT(View view) {

        AlertDialog ad= new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirmare").
                setMessage("Parasiti Aplicatia?").setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                        System.exit(0);
                    }
                })
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                                Intent A2=new Intent(getApplicationContext(), MainActivity2.class);
                                startActivity(A2);
                            }
                        }).create();

        ad.show();
        //finish();
        //System.exit(0);
    }
}