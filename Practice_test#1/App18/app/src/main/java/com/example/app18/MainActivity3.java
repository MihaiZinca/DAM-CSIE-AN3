package com.example.app18;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String parametru=getIntent().getStringExtra(Intent.EXTRA_TEXT);
        TextView tv=findViewById(R.id.textView);
        tv.setText(parametru);


    }

    public void Genereaza(View view) {

        LinearLayout ll=findViewById(R.id.LinLayA3);
        Button btn1=new Button(this);
        Button btn2=new Button(this);
        Button btn3=new Button(this);

        btn1.setText("Roman");
        btn2.setText("Tehnica");
        btn3.setText("Istorie");

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        ll.addView(btn1);
        ll.addView(btn2);
        ll.addView(btn3);



    }

    @Override
    public void onClick(View v) {
        Button apasat=(Button) v;
        Toast.makeText(this,apasat.getText(),Toast.LENGTH_SHORT).show();
    }
}