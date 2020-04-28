package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class examinar extends AppCompatActivity {

    ArrayList<Alumno> alumnos;
    ArrayList<Asignatura> asignaturas;
    EditText nombre;
    EditText dni;
    EditText fecha;
    EditText nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinar);

        conectaBD cbd = new conectaBD(getApplicationContext());
        alumnos = cbd.mostrarAlumnos();
        asignaturas = cbd.mostrarAsignaturas();

        nombre = findViewById(R.id.insertNombre);
        dni = findViewById(R.id.insertDNI);
        fecha = findViewById(R.id.insertFecha);
        nota = findViewById(R.id.insertNota);
    }

    public void aceptar(View view) {

        Intent alta = new Intent(this, MainActivity.class);
        String nom = nombre.getText().toString();
        String DNI = dni.getText().toString();
        String data = fecha.getText().toString();
        double NOTA = Double.parseDouble(nota.getText().toString());

        Alumno alu;
        Asignatura asig;

        if (!nom.equals("") && !DNI.equals("") && !data.equals("")) {
            alu = buscaAlumno(DNI);
            asig = buscaAsignatura(nom);
            if(alu!=null && asig!=null){
                if(!alu.tieneAsig(asig)){
                    try {
                        conectaBD cbd = new conectaBD(getApplicationContext());
                        cbd.examinar(nom,DNI,data,NOTA);
                        setResult(Activity.RESULT_OK, alta);
                        finish();
                    } catch (SQLException e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "El alumno no esta matriculado en esa asignatura." , Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "El alumno o la asignatura no se encuentran.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }
    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_CANCELED, salir);
        finish();
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
