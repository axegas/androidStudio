package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        }*/
    }



    public void verAlumnos(View view){
        try {
            Intent ver = new Intent(this, verAlumnos.class);
            startActivity(ver);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void verProfesores(View view){
        try {
            Intent ver = new Intent(this, verProfesores.class);
            startActivity(ver);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void verAsignaturas(View view){
        try {
            Intent ver = new Intent(this, verAsignaturas.class);
            startActivity(ver);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void matricular(View view){
        try {
            Intent ver = new Intent(this, matricular.class);
            startActivity(ver);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void examinar(View view){
        Intent ver = new Intent(this, examinar.class);
        startActivity(ver);
    }
}
