package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class verAsignaturas extends AppCompatActivity {
    TextView nombres;
    TextView libros;
    TextView profes;
    TextView obligatoria;
    ArrayList<Asignatura> asignaturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_asignaturas);

        obligatoria = findViewById(R.id.obligatoria);
        nombres = findViewById(R.id.nombresAsig);
        libros = findViewById(R.id.librosAsig);
        profes = findViewById(R.id.profesAsig);

        try{
            conectaBD bd = new conectaBD(getApplicationContext());
            asignaturas = bd.mostrarAsignaturas();
            mostrar();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, salir);
        finish();
    }
    public void mostrar() {
        String stNombre = "NOMBRE\n--------\n";
        String stLibro = "LIBRO\n-------\n";
        String stProfesor = "PROFESOR\n-----\n";
        String stObligatoria = "OBLIGATORIA\n-----\n";

        Asignatura asig;
        Iterator<Asignatura> iter = asignaturas.iterator();
        while(iter.hasNext()){
            asig = iter.next();
            stNombre += asig.getNombre() + "\n";
            stLibro += asig.getLibro() + "\n";
            stProfesor += asig.getProfesor().getNombre() + "\n";
            stObligatoria += asig.getObligatoria()+"\n";
        }
        nombres.setText(stNombre);
        libros.setText(stLibro);
        profes.setText(stProfesor);
        obligatoria.setText(stObligatoria);
    }
}
