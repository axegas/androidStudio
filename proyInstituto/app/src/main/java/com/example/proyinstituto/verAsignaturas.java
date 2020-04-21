package com.example.proyinstituto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        asignaturas = (ArrayList<Asignatura>) (getIntent().getSerializableExtra("asignaturas"));

        obligatoria = findViewById(R.id.obligatoria);
        nombres = findViewById(R.id.nombresAsig);
        libros = findViewById(R.id.librosAsig);
        profes = findViewById(R.id.profesAsig);
        mostrar();
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        salir.putExtra("asignaturas",asignaturas);
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
