package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class verProfesores extends AppCompatActivity {

    TextView nombres;
    TextView edades;
    TextView dnis;
    ArrayList<Profesor> profesores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_profesores);

        conectaBD bd = new conectaBD(getApplicationContext());
        profesores = bd.mostrarProfesores();

        nombres = findViewById(R.id.nombresProf);
        edades = findViewById(R.id.edadesProf);
        dnis = findViewById(R.id.dnisProf);
        mostrar();
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, salir);
        finish();
    }
    public void mostrar() {

        String stNombre = "NOMBRE\n--------\n";
        String stEdad = "EDAD\n-------\n";;
        String stDNI = "DNI\n-----\n";;

        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while(iter.hasNext()){
            prof = iter.next();
            stNombre += prof.getNombre() + "\n";
            stEdad += prof.getEdad() + "\n";
            stDNI += prof.getDNI() + "\n";
        }
        nombres.setText(stNombre);
        edades.setText(stEdad);
        dnis.setText(stDNI);
    }
}
