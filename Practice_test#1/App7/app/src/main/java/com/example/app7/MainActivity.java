package com.example.app7;

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
import androidx.core.graphics.ColorKt;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        TextView tv=findViewById(R.id.textView);
        tv.setTextColor(Color.GRAY);

        ImageView iv=findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.stop);

        Switch sw=findViewById(R.id.switch1);
        sw.setVisibility(TextView.INVISIBLE);

        Button bt=findViewById(R.id.button);
        bt.setVisibility(TextView.INVISIBLE);
    }

    public void doImageClick(View view) {
        if(app_on)
        {
            TextView tv=findViewById(R.id.textView);
            tv.setTextColor(Color.GRAY);

            ImageView iv=findViewById(R.id.imageView);
            iv.setImageResource(R.drawable.stop);

            Switch sw=findViewById(R.id.switch1);
            sw.setVisibility(TextView.INVISIBLE);

            Button btn=findViewById(R.id.button);
            btn.setVisibility(TextView.INVISIBLE);

            Toast.makeText(this,"Aplicatia e OFF",Toast.LENGTH_SHORT).show();

            app_on=false;
        }
        else
        {
            TextView tv=findViewById(R.id.textView);
            tv.setTextColor(Color.RED);

            ImageView iv=findViewById(R.id.imageView);
            iv.setImageResource(R.drawable.start);

            Switch sw=findViewById(R.id.switch1);
            sw.setVisibility(TextView.VISIBLE);

            Button btn=findViewById(R.id.button);
            if(sw.isChecked())
                btn.setVisibility(View.VISIBLE);
            else
                btn.setVisibility(View.INVISIBLE);

            Toast.makeText(this,"Aplicatia e ON",Toast.LENGTH_SHORT).show();

            app_on=true;
        }
    }

    public void doSwitchClick(View view) {
        Switch sw=findViewById(R.id.switch1);
        Button btn=findViewById(R.id.button);

        if(sw.isChecked())
            btn.setVisibility(View.VISIBLE);
        else
            btn.setVisibility(View.INVISIBLE);
    }
    public void doButtonClick(View view) {
        Random rnd=new Random();
        Intent A2=new Intent(this, MainActivity2.class);
        A2.putExtra(Intent.EXTRA_TEXT,"Activitatea 2"+rnd.nextInt(1000));
        startActivity(A2);
    }


}