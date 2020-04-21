package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class altaAsig extends AppCompatActivity {

    ArrayList<Profesor> profesores;

    EditText nombre;
    EditText libro;
    EditText profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_asig);

        conectaBD bd = new conectaBD(getApplicationContext());
        profesores = bd.mostrarProfesores();

        nombre = findViewById(R.id.insertNombre);
        libro = findViewById(R.id.insertLibro);
        profesor = findViewById(R.id.insertProfesor);
    }
    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_CANCELED, salir);
        finish();
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
                    setResult(Activity.RESULT_OK, alta);
                    finish();
                }catch(Exception e){
                    Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
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
