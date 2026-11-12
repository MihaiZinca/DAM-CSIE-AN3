package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> listaStudenti=new ArrayList<>();
    ListView listaEcran;

    int REQUEST_ADD=111;

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

        Random rnd=new Random();

        for(int i=0;i<10;i++)
        {
            Student s=new Student();
            s.nume="Nume:"+rnd.nextInt(10);
            s.grupa="Grupa:"+rnd.nextInt(10);
            s.varsta=18+rnd.nextInt(8);
            s.medie=5+ rnd.nextInt(51)/10.0;
            listaStudenti.add(s);
        }

        Collections.sort(listaStudenti, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.medie>o2.medie)
                    return -1;
                else if(o1.medie<o2.medie)
                    return 1;
                else
                    return 0;
            }
        });

        listaEcran=findViewById(R.id.listView);
        ArrayAdapter<Student> adaptor=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listaStudenti);
        listaEcran.setAdapter(adaptor);

    }

    public void doAdd(View view) {
        Intent A2=new Intent(this, MainActivity2.class);
        startActivityForResult(A2,REQUEST_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_ADD && resultCode==RESULT_OK && data!=null)
        {
            Student s= (Student) data.getSerializableExtra("addStudent");
            if(s!=null) {
                ArrayAdapter<Student> adaptor = (ArrayAdapter<Student>) listaEcran.getAdapter();
                listaStudenti.add(s);


                Collections.sort(listaStudenti, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        if (o1.medie > o2.medie)
                            return -1;
                        else if (o1.medie < o2.medie)
                            return 1;
                        else
                            return 0;
                    }
                });

                adaptor.notifyDataSetChanged();

            }
        }
    }
}