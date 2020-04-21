package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class verAlumnos extends AppCompatActivity {
    TextView nombres;
    TextView edades;
    TextView cursos;
    TextView dnis;
    ArrayList<Alumno> alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumnos);

        conectaBD bd = new conectaBD(getApplicationContext());
        alumnos = bd.mostrarAlumnos();

        nombres = findViewById(R.id.nombresAlumnos);
        edades = findViewById(R.id.edadesAlumnos);
        cursos = findViewById(R.id.cursos);
        dnis = findViewById(R.id.dnisAlumnos);
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
