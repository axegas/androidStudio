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

import java.util.ArrayList;
import java.util.Iterator;

public class verProfesores extends AppCompatActivity {

    ArrayList<Profesor> profesores;
    private ViewGroup layout;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        layout = (ViewGroup) findViewById(R.id.content);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        conectaBD bd = new conectaBD(getApplicationContext());
        profesores = bd.mostrarProfesores();

        mostrar();
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, salir);
        finish();
    }
    public void mostrar() {
        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while(iter.hasNext()){
            prof = iter.next();
            addChild(prof);
        }

    }
    private void addChild(Profesor prof)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        int id = R.layout.profesor;

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView nombre = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
        TextView edad = (TextView) relativeLayout.findViewById(R.id.vistaEdad);
        TextView dni = (TextView) relativeLayout.findViewById(R.id.vistaDNI);
        nombre.setText(prof.getNombre());
        edad.setText(Integer.toString(prof.getEdad()));
        dni.setText(prof.getDNI());

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
