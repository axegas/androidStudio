package com.example.institutosqlv2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class verAsignaturas extends AppCompatActivity {

    private final int ACTIVITY_ALTAASIGNATURA = 3;

    ArrayList<Asignatura> asignaturas;
    private ViewGroup layout;
    private ScrollView scrollView;
    private Asignatura asignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               altaAsignatura();
            }
        });

        layout = (ViewGroup) findViewById(R.id.contentAsignaturas);
        scrollView = (ScrollView) findViewById(R.id.scrollAsignaturas);
        try {
            conectaBD bd = new conectaBD(getApplicationContext());
            asignaturas = bd.mostrarAsignaturas();
            bd.close();
            mostrar();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void mostrar(){
        Asignatura asig;
        Iterator<Asignatura> iter = asignaturas.iterator();
        while(iter.hasNext()){
            asig = iter.next();
            addChild(asig);
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

    public void altaAsignatura(){
        Intent alta = new Intent(this, altaAsignatura.class);
        startActivityForResult(alta, ACTIVITY_ALTAASIGNATURA);
    }
}
