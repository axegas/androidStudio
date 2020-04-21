package com.example.proyectoinstituto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void altaAlumno(View view){
        Intent alta = new Intent(this, MainActivity.class);
        startActivity(alta);
    }
    public void verAlumnos(View view){
        Intent alta = new Intent(this, MainActivity.class);
        startActivity(alta);
    }

}
