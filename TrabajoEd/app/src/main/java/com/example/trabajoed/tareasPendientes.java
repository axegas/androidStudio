package com.example.trabajoed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class tareasPendientes extends AppCompatActivity {

    ArrayList<tarea> tareas;
    private ScrollView scrollView;
    private ViewGroup layout;

    Button acabadas;
    Button todas;
    Button pendientes;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        scrollView = findViewById(R.id.scrollviewVer);
        layout = findViewById(R.id.contentVer);

        iniciaBotones();

        try {
            conectaBD bd = new conectaBD(this);
            tareas = bd.mostrarTareas(2);
            mostrar();
            bd.close();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void mostrar(){
        muestraLayouts ml;
        tarea tar;
        Iterator<tarea> iter = tareas.iterator();
        while(iter.hasNext()){
            tar = iter.next();
            ml = new muestraLayouts(tar,R.layout.tarea,scrollView,layout,this, this.getClass());
            ml.addChild();
        }
    }

    public void altaTarea(){
        Intent alta = new Intent(this, altaTarea.class);
        startActivity(alta);
    }

    public void irTodas(){
        Intent alta = new Intent(this, tareasTodas.class);
        startActivity(alta);
    }

    public void irAcabadas(){
        Intent alta = new Intent(this, tareasAcabadas.class);
        startActivity(alta);
    }

    public void irPendientes(){
        Intent alta = new Intent(this, tareasPendientes.class);
        startActivity(alta);
    }

    public void iniciaBotones(){
        acabadas = findViewById(R.id.acabadas);
        todas = findViewById(R.id.todas);
        pendientes = findViewById(R.id.pendientes);
        fab = findViewById(R.id.fab);
        pendientes.setBackgroundColor(0xFF0000DD);
        acabadas.setTextColor(0xFF000000);
        todas.setTextColor(0xFF000000);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaTarea();
            }
        });

        acabadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAcabadas();
            }
        });

        todas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irTodas();
            }
        });

        pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irPendientes();
            }
        });
    }
}
