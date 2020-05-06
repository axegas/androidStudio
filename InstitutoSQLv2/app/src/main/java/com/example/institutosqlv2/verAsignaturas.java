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

public class verAsignaturas extends AppCompatActivity {

    ArrayList<Asignatura> asignaturas;
    private ViewGroup layout;
    private ScrollView scrollView;
    private Asignatura asignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               altaAsignatura();
            }
        });

        layout = (ViewGroup) findViewById(R.id.contentVer);
        scrollView = (ScrollView) findViewById(R.id.scrollviewVer);
        try {
            conectaBD bd = new conectaBD(getApplicationContext());
            asignaturas = bd.mostrarAsignaturas();
            bd.close();
            mostrar();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void mostrar(){
        muestraLayouts ml;
        Asignatura asig;
        Iterator<Asignatura> iter = asignaturas.iterator();
        while(iter.hasNext()){
            asig = iter.next();
            ml = new muestraLayouts(asig,R.layout.asignatura,scrollView,layout,this);
            ml.addChild();
        }
    }
    public void altaAsignatura(){
        Intent alta = new Intent(this, altaAsignatura.class);
        startActivity(alta);
    }
}
