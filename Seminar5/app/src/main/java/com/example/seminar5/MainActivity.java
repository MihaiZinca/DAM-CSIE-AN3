package com.example.seminar5;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {

    String appCode;
    boolean codeVerificationOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //shared preferences
        SharedPreferences SP=getSharedPreferences("APP_ACCESS",MODE_PRIVATE);
        appCode=SP.getString("app_code","");

        AlertDialog.Builder AD=new AlertDialog.Builder(this);
        if(appCode.isEmpty())
                AD.setTitle("Alegeti un cod de acces....");
        else
            AD.setTitle("Introduceti codul de acces...");

        AD.setCancelable(false);

        EditText ET =new EditText(this);
        ET.setHint("Scrieti codul de acces aici...");
        ET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        AD.setView(ET);

        AD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), "OK apasat", Toast.LENGTH_SHORT).show();

                codeVerificationOK=true;
                if(appCode.isEmpty()) {
                    appCode = ET.getText().toString();
                    SharedPreferences.Editor editor=SP.edit();
                    editor.putString("app_code",appCode);
                    editor.apply();

                }

                if(!appCode.equals(ET.getText().toString()))
                {
                    ET.setText("");
                    ET.setHint("Scrieti codul de acces aici...");
                    dialog.cancel();
                }
                else
                    dialog.dismiss();
            }
        });

        AD.setNegativeButton("Renunt", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dialog.cancel();
                finish();
                System.exit(0);
            }
        });

        AD.setOnDismissListener(this);
        AD.setOnCancelListener(this);

        AD.show();

//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        //Toast.makeText(getApplicationContext(), "DIMISS!!!", Toast.LENGTH_SHORT).show();

        if (!codeVerificationOK)
            return;


        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv=findViewById(R.id.textView2);
        tv.setText(appCode);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        codeVerificationOK=false;
        ((AlertDialog) dialog).show();
    }

    public void doCodeReset(View view) {
        SharedPreferences SP=getSharedPreferences("APP_ACCESS",MODE_PRIVATE);
        SharedPreferences.Editor editor=SP.edit();
        editor.putString("app_code"," ");
        editor.apply();

        TextView tv=findViewById(R.id.textView2);
        tv.setText("");

    }
}