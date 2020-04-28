package com.example.institutosql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;
import java.util.Iterator;

public class verAlumnos extends AppCompatActivity implements OnClickListener {


    ArrayList<Alumno> alumnos;
    private ViewGroup layout;
    private ScrollView scrollView;
    private Alumno alumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        layout = (ViewGroup) findViewById(R.id.content);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        try {
            conectaBD bd = new conectaBD(getApplicationContext());
            alumnos = bd.mostrarAlumnos();
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

    public void mostrar(){
        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while(iter.hasNext()){
            alu = iter.next();
            addChild(alu);
        }
    }
    @SuppressLint("InlinedApi")
    private void addChild(final Alumno alu)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.alumno;

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView nombre = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
        TextView curso = (TextView) relativeLayout.findViewById(R.id.vistaCurso);
        TextView edad = (TextView) relativeLayout.findViewById(R.id.vistaEdad);
        TextView dni = (TextView) relativeLayout.findViewById(R.id.vistaDNI);
        nombre.setText(alu.getNombre());
        curso.setText(alu.getCurso());
        edad.setText(Integer.toString(alu.getEdad()));
        dni.setText(alu.getDNI());

        alumno = alu;

        Button vistaVer = (Button) relativeLayout.findViewById(R.id.vistaVer);

        vistaVer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ver(alu);
                }
            });

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
    private void ver(Alumno alu){
        Intent ver = new Intent(this,vistaAlumno.class);
        ver.putExtra("alumno",alu);
        startActivity(ver);
    }


    @Override
    public void onClick(View v) {


    }
}
