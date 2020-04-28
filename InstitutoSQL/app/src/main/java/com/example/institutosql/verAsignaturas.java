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
import java.util.*;

public class verAsignaturas extends AppCompatActivity {


    ArrayList<Asignatura> asignaturas;
    private ViewGroup layout;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        layout = (ViewGroup) findViewById(R.id.content);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        conectaBD bd = new conectaBD(getApplicationContext());
        asignaturas = bd.mostrarAsignaturas();

        mostrar();
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, salir);
        finish();
    }
    public void mostrar() {
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
}
