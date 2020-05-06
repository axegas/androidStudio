package com.example.institutosqlv2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;


public class verAlumnos extends AppCompatActivity {

    ArrayList<Alumno> alumnos;
    private ViewGroup layout;
    private ScrollView scrollView;
    private Alumno alumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        layout = (ViewGroup) findViewById(R.id.contentVer);
        scrollView = (ScrollView) findViewById(R.id.scrollviewVer);
        try {
            conectaBD bd = new conectaBD(getApplicationContext());
            alumnos = bd.mostrarAlumnos();
            bd.close();
            mostrar();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaAlumno();
            }
        });
    }

    public void mostrar(){
        muestraLayouts ml;
        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while(iter.hasNext()){
            alu = iter.next();
            ml = new muestraLayouts(alu,R.layout.alumno,scrollView,layout,this);
            ml.addChild();
        }
    }
    private void ver(Alumno alu){
        Intent ver = new Intent(this,vistaAlumno.class);
        ver.putExtra("alumno",alu);
        startActivity(ver);
    }

    public void altaAlumno(){
        Intent alta = new Intent(this, altaAlumno.class);
        startActivity(alta);
    }

}
