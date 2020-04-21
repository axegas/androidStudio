package com.example.proyinstituto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private final int ACTIVITY_ALTAALUMNOS = 1;
    private final int ACTIVITY_ALTAPROFESORES = 2;
    private final int ACTIVITY_ALTAASIGNATURAS = 3;
    private final int ACTIVITY_VERALUMNOS = 4;
    private final int ACTIVITY_VERPROFESORES = 5;
    private final int ACTIVITY_VERASIGNATURAS = 6;

    private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private ArrayList<Asignatura> asignaturas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case ACTIVITY_ALTAALUMNOS:
                if(resultCode== Activity.RESULT_OK) {
                    String nombre = data.getStringExtra("nombre");
                    String curso = data.getStringExtra("curso");
                    String dni = data.getStringExtra("dni");
                    int edad = data.getIntExtra("edad", 0);
                    alumnos.add(new Alumno(dni, nombre, edad, curso));
                }
                break;
            case ACTIVITY_ALTAPROFESORES:
                if(resultCode== Activity.RESULT_OK) {
                    String nombre = data.getStringExtra("nombre");
                    String dni = data.getStringExtra("dni");
                    int edad = data.getIntExtra("edad", 0);
                    Profesor prof = new Profesor(dni, nombre, edad);
                    profesores.add(prof);
                  //  Toast.makeText(this,prof.toString(), Toast.LENGTH_LONG).show();
                }
                break;
            case ACTIVITY_ALTAASIGNATURAS:
                profesores = (ArrayList<Profesor>) (data.getSerializableExtra("profesores")) ;
                if(resultCode== Activity.RESULT_OK) {
                    String nombre = data.getStringExtra("nombre");
                    String libro = data.getStringExtra("libro");
                    Profesor profesor = (Profesor) (data.getSerializableExtra("profesor"));
                    asignaturas.add(new Asignatura(nombre, libro, profesor));
                }
                break;
            case ACTIVITY_VERALUMNOS:
                alumnos = (ArrayList<Alumno>) (data.getSerializableExtra("alumnos")) ;
                break;
            case ACTIVITY_VERPROFESORES:
                profesores = (ArrayList<Profesor>) (data.getSerializableExtra("profesores")) ;
                break;
            case ACTIVITY_VERASIGNATURAS:
                asignaturas = (ArrayList<Asignatura>) (data.getSerializableExtra("asignaturas")) ;
                break;
            default:
                break;
        }
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
        if(profesores.size()!=0){
            Intent alta = new Intent(this, altaAsig.class);
            alta.putExtra("profesores",profesores);
            startActivityForResult(alta, ACTIVITY_ALTAASIGNATURAS);
        }
    }
    public void verAlumnos(View view){
        Intent ver = new Intent(this, verAlumnos.class);
        ver.putExtra("alumnos",alumnos);
        startActivityForResult(ver, ACTIVITY_VERALUMNOS);
    }
    public void verAlumnosBD(View view){
        Intent ver = new Intent(this, verAlumnosBD.class);
        startActivity(ver);
    }
    public void verProfesores(View view){
        Intent ver = new Intent(this, verProfesores.class);
        ver.putExtra("profesores",profesores);
        startActivityForResult(ver, ACTIVITY_VERPROFESORES);
    }

    public void verAsignaturas(View view){
        Intent ver = new Intent(this, verAsignaturas.class);
        ver.putExtra("asignaturas",asignaturas);
        startActivityForResult(ver, ACTIVITY_VERASIGNATURAS);
    }
}
