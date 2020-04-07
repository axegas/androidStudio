package com.example.ejercicio4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

public class validacion extends AppCompatActivity {

    private TextView verNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion);

        verNombre = findViewById(R.id.verNombre);
        String nombre = getIntent().getStringExtra("nombre");
        verNombre.setText("Hola " + nombre);
    }

    public void salir(View view){

        Intent salir = new Intent(this, MainActivity.class);
        startActivity(salir);
    }
}
