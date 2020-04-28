package com.example.institutosqlv2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class verProfesores extends AppCompatActivity {

    private final int ACTIVITY_ALTAPROFESORES = 2;
    ArrayList<Profesor> profesores;
    private ViewGroup layout;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = (ViewGroup) findViewById(R.id.contentAsignaturas);
        scrollView = (ScrollView) findViewById(R.id.scrollAsignaturas);

        conectaBD bd = new conectaBD(getApplicationContext());
        profesores = bd.mostrarProfesores();
        bd.close();

        mostrar();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaProfesor();
            }
        });
    }

    public void mostrar() {
        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while(iter.hasNext()){
            prof = iter.next();
            addChild(prof);
        }

    }
    private void addChild(Profesor prof)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.profesor;

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView nombre = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
        TextView edad = (TextView) relativeLayout.findViewById(R.id.vistaEdad);
        TextView dni = (TextView) relativeLayout.findViewById(R.id.vistaDNI);
        TextView categoria = (TextView) relativeLayout.findViewById(R.id.vistaCategoria);

        nombre.setText(prof.getNombre());
        edad.setText(Integer.toString(prof.getEdad()));
        dni.setText(prof.getDNI());
        categoria.setText(prof.getCategoria());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.topMargin = 15;

        relativeLayout.setPadding(5, 3, 5, 3);
        relativeLayout.setLayoutParams(params);

        layout.addView(relativeLayout);

        scrollView.post(new Runnable() {
                            public void run() {
                                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        }
        );
    }

    public void altaProfesor(){
        Intent alta = new Intent(this, altaProfesor.class);
        startActivityForResult(alta, ACTIVITY_ALTAPROFESORES);
    }

}
