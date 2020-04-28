package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private final int ACTIVITY_ALTACURSOS = 4;
    private final int ACTIVITY_VERALUMNOS = 5;
    private final int ACTIVITY_VERPROFESORES = 6;
    private final int ACTIVITY_VERASIGNATURAS = 7;
    private final int ACTIVITY_VERCURSOS = 8;
    private final int ACTIVITY_MATRICULAR = 9;
    private final int ACTIVITY_EXAMINAR = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        try {
            conectaBD cbd = new conectaBD(getApplicationContext());
            cbd.borraTabla();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }*/
    }

    public void verAlumnos(View view){
        try {
            Intent ver = new Intent(this, verAlumnos.class);
            startActivityForResult(ver, ACTIVITY_VERALUMNOS);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void verProfesores(View view){
        try {
            Intent ver = new Intent(this, verProfesores.class);
            startActivityForResult(ver, ACTIVITY_VERPROFESORES);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void verAsignaturas(View view){
        try {
            Intent ver = new Intent(this, verAsignaturas.class);
            startActivityForResult(ver, ACTIVITY_VERASIGNATURAS);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void matricular(View view){
        try {
            Intent ver = new Intent(this, matricular.class);
            startActivityForResult(ver, ACTIVITY_MATRICULAR);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void examinar(View view){
        Intent ver = new Intent(this, examinar.class);
        startActivityForResult(ver, ACTIVITY_EXAMINAR);
    }
}
