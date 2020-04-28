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

public class altaAsignatura extends AppCompatActivity {


    ArrayList<Profesor> profesores;

    EditText nombre;
    EditText libro;
    EditText profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_asignatura);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conectaBD bd = new conectaBD(getApplicationContext());
        profesores = bd.mostrarProfesores();
        bd.close();
        nombre = findViewById(R.id.insertNombre);
        libro = findViewById(R.id.insertLibro);
        profesor = findViewById(R.id.insertProfesor);
    }

    public void aceptar(View view){
        Intent alta = new Intent(this, MainActivity.class);

        String nom = nombre.getText().toString();
        String lib = libro.getText().toString();
        String prof = profesor.getText().toString();

        if (!nom.equals("") && !lib.equals("") &&!prof.equals("")){
            Profesor profe = buscaProfesor(prof);
            if(profe!=null){
                try {
                    conectaBD cbd = new conectaBD(getApplicationContext());
                    cbd.agregarAsignatura(nom, lib, profe.getDNI());
                    cbd.close();
                    setResult(Activity.RESULT_OK, alta);
                    finish();
                }catch(SQLException e){
                    Toast.makeText(this,"Nombre incorrecto", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"No se encuentra el profesor", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }
    public Profesor buscaProfesor(String dni) {
        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while (iter.hasNext()) {
            prof = iter.next();
            if (prof.getDNI().equals(dni)) {
                return prof;
            }
        }
        return null;
    }
}
