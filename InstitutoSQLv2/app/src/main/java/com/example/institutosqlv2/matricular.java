package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class matricular extends AppCompatActivity {

    ArrayList<Alumno> alumnos;
    ArrayList<Asignatura> asignaturas;
    EditText nombre;
    EditText dni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricular);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = findViewById(R.id.insertNombre);
        dni = findViewById(R.id.insertDNI);
    }

    public void aceptar(View view){

        Intent alta = new Intent(this, MainActivity.class);
        String nom = nombre.getText().toString();
        String DNI = dni.getText().toString();
        conectaBD cbd = new conectaBD(getApplicationContext());
        alumnos = cbd.mostrarAlumnos();
        asignaturas = cbd.mostrarAsignaturas();

        Asignatura asig;
        Alumno alu;

        if (!nom.equals("") && !DNI.equals("")) {
            alu = buscaAlumno(DNI);
            asig = buscaAsignatura(nom);
            if(alu!=null && asig!=null){
                if(!alu.tieneAsig(asig)){
                    try {
                        cbd.matricular(nom, DNI);
                        setResult(Activity.RESULT_OK, alta);
                        cbd.close();
                        finish();
                    } catch (SQLException e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "El alumno ya está matriculado en esa asignatura.", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "El alumno o la asignatura no se encuentran.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }

    }


    public Alumno buscaAlumno(String dni) {
        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while (iter.hasNext()) {
            alu = iter.next();
            if (alu.getDNI().equals(dni)) {
                return alu;
            }
        }
        return null;
    }
    public Asignatura buscaAsignatura(String nombre) {
        Asignatura asig;
        Iterator<Asignatura> iter = asignaturas.iterator();
        while (iter.hasNext()) {
            asig = iter.next();
            if (asig.getNombre().equals(nombre)) {
                return asig;
            }
        }
        return null;
    }
}
