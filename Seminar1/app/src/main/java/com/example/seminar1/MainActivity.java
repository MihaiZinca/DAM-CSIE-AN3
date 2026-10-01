package com.example.seminar1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean app_on=false;

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

        TextView tv=(TextView)findViewById(R.id.textView); //toate sunt vew si convertim la textview -izolam element de pe metoda grafica (R. -pentru resurse,ce e in src)
        tv.setTextColor(Color.GRAY);

        ImageView iv=(ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.stop);

        Switch sw=(Switch) findViewById(R.id.switch1);
        sw.setVisibility(TextView.INVISIBLE);

        Button bt=(Button) findViewById(R.id.button);
        bt.setVisibility(TextView.INVISIBLE);
    }

    public void doImageClick(View view) {
        if(app_on)
        {
            TextView tv=(TextView)findViewById(R.id.textView);
            tv.setTextColor(Color.GRAY);

            ImageView iv=(ImageView) findViewById(R.id.imageView);
            iv.setImageResource(R.drawable.stop);

            Switch sw=(Switch) findViewById(R.id.switch1);
            sw.setVisibility(TextView.INVISIBLE);

            Button bt=(Button) findViewById(R.id.button);
            bt.setVisibility(TextView.INVISIBLE);

            Toast.makeText(this,"Aplicatia e OFF",Toast.LENGTH_SHORT).show();

            app_on=false;
        }
        else
        {
            TextView tv=(TextView)findViewById(R.id.textView);
            tv.setTextColor(Color.RED);

            ImageView iv=(ImageView) findViewById(R.id.imageView);
            iv.setImageResource(R.drawable.start);

            Switch sw=(Switch) findViewById(R.id.switch1);
            sw.setVisibility(TextView.VISIBLE);

            Button bt=(Button) findViewById(R.id.button);
            if(sw.isChecked())
                bt.setVisibility(View.VISIBLE);
            else
                bt.setVisibility(View.INVISIBLE);


            Toast.makeText(this,"Aplicatia e ON",Toast.LENGTH_SHORT).show();

            app_on=true;
        }
    }

    public void doSwitchClick(View view) {
        Switch sw=(Switch) findViewById(R.id.switch1);
        Button bt=(Button)findViewById(R.id.button);

        if(sw.isChecked())
            bt.setVisibility(View.VISIBLE);
        else
            bt.setVisibility(View.INVISIBLE);
    }

    public void doButtonClick(View view) {
        //deschidere activitate 2
        Random r=new Random();

        Intent newActivity=new Intent(this,MainActivity2.class);
        newActivity.putExtra(Intent.EXTRA_TEXT,"Activitatea 2 :::"+r.nextInt(1000));
        startActivity(newActivity);
    }
}