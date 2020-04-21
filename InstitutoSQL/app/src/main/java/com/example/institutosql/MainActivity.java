package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ACTIVITY_ALTAALUMNOS = 1;
    private final int ACTIVITY_ALTAPROFESORES = 2;
    private final int ACTIVITY_ALTAASIGNATURAS = 3;
    private final int ACTIVITY_VERALUMNOS = 4;
    private final int ACTIVITY_VERPROFESORES = 5;
    private final int ACTIVITY_VERASIGNATURAS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // conectaBD cbd = new conectaBD(getApplicationContext());
      //  cbd.borraTabla("asignaturas");
    }


    public void altaAlumno(View view){
        Intent alta = new Intent(this, altaAlum.class);
        startActivityForResult(alta, ACTIVITY_ALTAALUMNOS);
    }
    public void altaProfesor(View view){
        Intent alta = new Intent(this, altaProfesor.class);
        startActivityForResult(alta, ACTIVITY_ALTAPROFESORES);
    }
    public void altaAsignatura(View view){
        Intent alta = new Intent(this, altaAsig.class);
        startActivityForResult(alta, ACTIVITY_ALTAASIGNATURAS);
    }
    public void verAlumnos(View view){
        Intent ver = new Intent(this, verAlumnos.class);
        startActivityForResult(ver, ACTIVITY_VERALUMNOS);
    }
    public void verProfesores(View view){
        Intent ver = new Intent(this, verProfesores.class);
        startActivityForResult(ver, ACTIVITY_VERPROFESORES);
    }

    public void verAsignaturas(View view){
        Intent ver = new Intent(this, verAsignaturas.class);
        startActivityForResult(ver, ACTIVITY_VERASIGNATURAS);
    }

/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }*/
}
