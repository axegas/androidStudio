package com.example.proyinstituto;

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

        profesores = (ArrayList<Profesor>) (getIntent().getSerializableExtra("profesores"));

        nombre = findViewById(R.id.insertNombre);
        libro = findViewById(R.id.insertLibro);
        profesor = findViewById(R.id.insertProfesor);
    }
    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        salir.putExtra("profesores",profesores);
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
                alta.putExtra("nombre",nom);
                alta.putExtra("libro",lib);
                alta.putExtra("profesor",profe);
                alta.putExtra("profesores",profesores);
                setResult(Activity.RESULT_OK, alta);
                finish();
            }else{
                Toast.makeText(this,"No se encuentra el profesor", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }
    public Profesor buscaProfesor(String nombre) {
        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while (iter.hasNext()) {
            prof = iter.next();
            if (prof.getNombre().equals(nombre)) {
                return prof;
            }
        }
        return null;
    }
}
