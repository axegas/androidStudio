package com.example.institutosqlv2;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;

public class verProfesores extends AppCompatActivity {

    ArrayList<Profesor> profesores;
    private ViewGroup viewGroup;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewGroup = (ViewGroup) findViewById(R.id.contentVer);
        scrollView = (ScrollView) findViewById(R.id.scrollviewVer);

        conectaBD bd = new conectaBD(getApplicationContext());
        profesores = bd.mostrarProfesores();
        bd.close();

        mostrar();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                altaProfesor();
            }
        });
    }

    public void mostrar() {
        muestraLayouts ml;
        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while(iter.hasNext()){
            prof = iter.next();
            ml = new muestraLayouts(prof,R.layout.profesor,scrollView,viewGroup,this);
            ml.addChild();
        }
    }

    public void altaProfesor(){
        Intent alta = new Intent(this, altaProfesor.class);
        startActivity(alta);
    }

}
