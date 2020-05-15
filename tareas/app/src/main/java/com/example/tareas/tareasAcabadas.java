package com.example.tareas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class tareasAcabadas extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollviewVer);
        layout = findViewById(R.id.contentVer);

        iniciaBotones();

        try {
            conectaBD bd = new conectaBD(this);
            tareas = bd.mostrarTareas(3);
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
            ml = new muestraLayouts(tar,R.layout.tarea,scrollView,layout,this,this.getClass());
            ml.addChild();
        }
    }

    public void iniciaBotones(){
        acabadas = findViewById(R.id.acabadas);
        todas = findViewById(R.id.todas);
        pendientes = findViewById(R.id.pendientes);
        fab = findViewById(R.id.fab);

        acabadas.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        acabadas.setTextColor(getResources().getColor(R.color.white));
        todas.setTextColor(getResources().getColor(R.color.black));
        pendientes.setTextColor(getResources().getColor(R.color.black));

        fab.setOnClickListener(this);
        acabadas.setOnClickListener(this);
        todas.setOnClickListener(this);
        pendientes.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent alta;
        switch (v.getId()){
            case R.id.fab:
                alta = new Intent(this, altaTarea.class);
                break;
            case R.id.acabadas:
                alta = new Intent(this, tareasAcabadas.class);
                break;
            case R.id.todas:
                alta = new Intent(this, MainActivity.class);
                break;
            case R.id.pendientes:
                alta = new Intent(this, tareasPendientes.class);
                break;
            default:
                alta = null;
                break;
        }
        if(alta!=null)
            startActivity(alta);
    }
}
