package com.example.app6;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    String appCode;
    boolean codeVerificationOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        SharedPreferences SP=getSharedPreferences("APP_ACCESS",MODE_PRIVATE);
        appCode=SP.getString("app_code","");


        AlertDialog.Builder AD=new AlertDialog.Builder(this);
        if(appCode.isEmpty())
            AD.setTitle("Alegeti un cod de access");
        else
            AD.setTitle("Introduceti codul de access");

        AD.setCancelable(false);

        EditText ET=new EditText(this);
        ET.setHint("Scrieti codul de acces aici...");
        ET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        AD.setView(ET);


        AD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                codeVerificationOK=true;
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
                    ET.setHint("Scrieti codul de acces aici");
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

    @Override
    public void onCancel(DialogInterface dialog) {
        codeVerificationOK=false;
        ((AlertDialog)dialog).show();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if(!codeVerificationOK)
            return;

        //luat de la inceput onCreate cu copy
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),(v,insets) ->{
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tv=findViewById(R.id.textView2);
        tv.setText(appCode);
    }
}