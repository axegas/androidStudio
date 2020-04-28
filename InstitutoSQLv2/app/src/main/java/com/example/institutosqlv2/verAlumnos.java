package com.example.institutosqlv2;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;


public class verAlumnos extends AppCompatActivity {
    private final int ACTIVITY_ALTAALUMNOS = 2;

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


        layout = (ViewGroup) findViewById(R.id.contentAsignaturas);
        scrollView = (ScrollView) findViewById(R.id.scrollAsignaturas);
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
        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while(iter.hasNext()){
            alu = iter.next();
            addChild(alu);
        }
    }
    @SuppressLint("InlinedApi")
    private void addChild(final Alumno alu)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.alumno;

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView nombre = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
        TextView curso = (TextView) relativeLayout.findViewById(R.id.vistaCurso);
        TextView edad = (TextView) relativeLayout.findViewById(R.id.vistaEdad);
        TextView dni = (TextView) relativeLayout.findViewById(R.id.vistaDNI);
        nombre.setText(alu.getNombre());
        curso.setText(alu.getCurso());
        edad.setText(Integer.toString(alu.getEdad()) + " años.");
        dni.setText(alu.getDNI());

        alumno = alu;

        Button vistaVer = (Button) relativeLayout.findViewById(R.id.vistaVer);

        vistaVer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ver(alu);
            }
        });

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

    private void ver(Alumno alu){
        Intent ver = new Intent(this,vistaAlumno.class);
        ver.putExtra("alumno",alu);
        startActivity(ver);
    }

    public void altaAlumno(){
        Intent alta = new Intent(this, altaAlumno.class);
        startActivityForResult(alta, ACTIVITY_ALTAALUMNOS);
    }

}
