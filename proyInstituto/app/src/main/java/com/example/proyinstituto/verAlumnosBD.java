package com.example.proyinstituto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class verAlumnosBD extends AppCompatActivity {

    TextView nombres;
    TextView edades;
    TextView cursos;
    TextView dnis;
    ArrayList<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumnos_b_d);

       // alumnos = (ArrayList<Alumno>) (getIntent().getSerializableExtra("alumnos")) ;
        conectaBD bd = new conectaBD(getApplicationContext());
        alumnos = bd.mostrarAlumnos();

        nombres = findViewById(R.id.nombresAlumnosBD);
        edades = findViewById(R.id.edadesAlumnosBD);
        cursos = findViewById(R.id.cursosBD);
        dnis = findViewById(R.id.dnisAlumnosBD);
        mostrar();
    }
    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, salir);
        finish();
    }
    public void mostrar(){
        String stNombre = "NOMBRE\n--------\n";
        String stEdad = "EDAD\n-------\n";;
        String stCurso = "CURSO\n------\n";;
        String stDNI = "DNI\n-----\n";;

        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while(iter.hasNext()){
            alu = iter.next();
            stNombre += alu.getNombre() + "\n";
            stEdad += alu.getEdad() + "\n";
            stCurso += alu.getCurso() + "\n";
            stDNI += alu.getDNI() + "\n";
        }
        nombres.setText(stNombre);
        edades.setText(stEdad);
        cursos.setText(stCurso);
        dnis.setText(stDNI);
    }
}
