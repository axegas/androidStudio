package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class vistaAlumno extends AppCompatActivity {

    Alumno alu;
    private ViewGroup layoutAsignaturas;
    private ViewGroup layoutExamenes;

    private ScrollView scrollAsignaturas;
    private ScrollView scrollExamenes;


    ArrayList<Examen> examenes;

    TextView nombre;
    TextView edad;
    TextView curso;
    TextView dni;
    TextView nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_alumno);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutAsignaturas = (ViewGroup) findViewById(R.id.contentVer);
       layoutExamenes = (ViewGroup) findViewById(R.id.contentExamenes);

        scrollAsignaturas = (ScrollView) findViewById(R.id.scrollviewVer);
        scrollExamenes = (ScrollView) findViewById(R.id.scrollExamenes);



        nombre = findViewById(R.id.vistaNombre);
        edad = findViewById(R.id.vistaEdad);
        curso = findViewById(R.id.vistaCurso);
        dni = findViewById(R.id.vistaDNI);
        nota = findViewById(R.id.vistaNotaMedia);

        alu = (Alumno) getIntent().getSerializableExtra("alumno");

        conectaBD cbd = new conectaBD(getApplicationContext());
        examenes = cbd.mostrarExamenesDeAlumno(alu.getDNI());
        alu.setAsignaturas(cbd.verAsignaturasDeAlumno(alu.getDNI()));
        cbd.close();
        nombre.setText(alu.getNombre());
        edad.setText(alu.getEdad() + " años");
        curso.setText(alu.getCurso());
        dni.setText(alu.getDNI());
        nota.setText(Double.toString(getNotaMedia(examenes)));

        mostrarAsignaturas();
       mostrarExamenes();
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, salir);
        finish();
    }

    public void mostrarAsignaturas() {
        Asignatura asig;
        muestraLayouts ml;
        Iterator<Asignatura> iter = alu.getAsignaturas().iterator();
        while(iter.hasNext()){
            asig = iter.next();
            ml = new muestraLayouts(asig,R.layout.asignatura,scrollAsignaturas,layoutAsignaturas,this);
            ml.addChild();
        }
    }

    public void mostrarExamenes() {
        Examen ex;
        muestraLayouts ml;
        Iterator<Examen> iter = examenes.iterator();
        while(iter.hasNext()){
            ex = iter.next();
            ml = new muestraLayouts(ex,R.layout.examen,scrollExamenes,layoutExamenes,this);
            ml.addChild();
        }
    }
    public double getNotaMedia(ArrayList<Examen> examenes){
        if(examenes.size()==0){
            return 0;
        }
        double notaMedia = 0;
        Iterator<Examen> iter = examenes.iterator();
        while(iter.hasNext()){
            notaMedia += iter.next().getNota();
        }
        return notaMedia / examenes.size();
    }
}
