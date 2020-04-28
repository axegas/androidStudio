package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class vistaAlumno extends AppCompatActivity {

    Alumno alu;
    private ViewGroup layout;
    private ViewGroup layout2;
    private ScrollView scrollView;
    private ScrollView scrollView2;
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

        layout = (ViewGroup) findViewById(R.id.content);
        layout2 = (ViewGroup) findViewById(R.id.content2);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView2 = (ScrollView) findViewById(R.id.scrollView2);

        nombre = findViewById(R.id.vistaNombre);
        edad = findViewById(R.id.vistaEdad);
        curso = findViewById(R.id.vistaCurso);
        dni = findViewById(R.id.vistaDNI);
        nota = findViewById(R.id.vistaNotaMedia);

        alu = (Alumno) getIntent().getSerializableExtra("alumno");

        conectaBD cbd = new conectaBD(getApplicationContext());
        examenes = cbd.mostrarExamenesDeAlumno(alu.getDNI());
        alu.setNotaMedia(getNotaMedia(examenes));

        nombre.setText(alu.getNombre());
        edad.setText(Integer.toString(alu.getEdad()));
        curso.setText(alu.getCurso());
        dni.setText(alu.getDNI());
        nota.setText(Double.toString(alu.getNotaMedia()));

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
        Iterator<Asignatura> iter = alu.getAsignaturas().iterator();
        while(iter.hasNext()){
            asig = iter.next();
            addChild(asig);
        }
    }
    public void mostrarExamenes() {
        Examen ex;
        Iterator<Examen> iter = examenes.iterator();
        while(iter.hasNext()){
            ex = iter.next();
            addChildEx(ex);
        }
    }
    private void addChild(Asignatura asig)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.asignatura;

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView nombre = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
        TextView libro = (TextView) relativeLayout.findViewById(R.id.vistaLibro);
        TextView profesor = (TextView) relativeLayout.findViewById(R.id.vistaProfesor);
        TextView obligatoria = (TextView) relativeLayout.findViewById(R.id.vistaObligatoria);
        nombre.setText(asig.getNombre());
        libro.setText(asig.getLibro());
        profesor.setText(asig.getProfesor().getNombre());
        obligatoria.setText("Obligatoria: " + asig.getObligatoria());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.topMargin = 15;

        relativeLayout.setPadding(5, 3, 5, 3);
        relativeLayout.setLayoutParams(params);

        layout.addView(relativeLayout);


        scrollView.post(new Runnable() {
                            public void run() {
                                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        }
        );

    }
    private void addChildEx(Examen ex)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.examen;

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView nombre = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
        TextView fecha = (TextView) relativeLayout.findViewById(R.id.vistaFecha);
        TextView nota = (TextView) relativeLayout.findViewById(R.id.vistaNota);
        TextView calificacion = (TextView) relativeLayout.findViewById(R.id.vistaCalificacion);

        nombre.setText(ex.getAsignatura().getNombre());
        fecha.setText(ex.getFecha());
        nota.setText(Double.toString(ex.getNota()));
        calificacion.setText(ex.getCalificacion());



        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.topMargin = 15;

        relativeLayout.setPadding(5, 3, 5, 3);
        relativeLayout.setLayoutParams(params);

        layout2.addView(relativeLayout);


        scrollView2.post(new Runnable() {
                            public void run() {
                                scrollView2.fullScroll(ScrollView.FOCUS_DOWN);
                            }
                        }
        );

    }

    public double getNotaMedia(ArrayList<Examen> examenes){
        double notaMedia = 0;
        Iterator<Examen> iter = examenes.iterator();
        while(iter.hasNext()){
            notaMedia += iter.next().getNota();
        }

        return notaMedia/examenes.size();
    }

}
