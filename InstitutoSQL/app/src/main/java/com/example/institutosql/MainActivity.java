package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ACTIVITY_ALTAALUMNOS = 1;
    private final int ACTIVITY_ALTAPROFESORES = 2;
    private final int ACTIVITY_ALTAASIGNATURAS = 3;
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
        }
*/
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
        try{
            Intent ver = new Intent(this, verAlumnos.class);
            startActivityForResult(ver, ACTIVITY_VERALUMNOS);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void verProfesores(View view){
        Intent ver = new Intent(this, verProfesores.class);
        startActivityForResult(ver, ACTIVITY_VERPROFESORES);
    }

    public void verAsignaturas(View view){
        Intent ver = new Intent(this, verAsignaturas.class);
        startActivityForResult(ver, ACTIVITY_VERASIGNATURAS);
    }
    public void matricular(View view){
        Intent ver = new Intent(this, matricular.class);
        startActivityForResult(ver, ACTIVITY_MATRICULAR);
    }
    public void examinar(View view){
        Intent ver = new Intent(this, examinar.class);
        startActivityForResult(ver, ACTIVITY_EXAMINAR);
    }
    public void altaCurso(View view){
        Intent ver = new Intent(this, altaCurso.class);
        startActivityForResult(ver, ACTIVITY_ALTACURSOS);
    }
    public void verCursos(View view){
        Intent ver = new Intent(this, verCursos.class);
        startActivityForResult(ver, ACTIVITY_VERCURSOS);
    }


/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }*/
}
