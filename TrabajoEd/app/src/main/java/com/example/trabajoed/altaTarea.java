package com.example.trabajoed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class altaTarea extends AppCompatActivity {

    TextView asignatura;
    TextView descripcion;
    TextView fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_tarea);

        asignatura = findViewById(R.id.insertAsignatura);
        descripcion = findViewById(R.id.insertDescripcion);
        fecha = findViewById(R.id.insertFecha);


    }

    public void aceptar(View v){

        Intent alta = new Intent(this, tareasTodas.class);
        String asig = asignatura.getText().toString();
        String desc = descripcion.getText().toString();
        String fech = fecha.getText().toString();

        if (!asig.equals("") && !desc.equals("") && !fech.equals("")){
            try{
                conectaBD cbd = new conectaBD(getApplicationContext());
                cbd.agregarTarea(asig, desc,fech);
                cbd.close();
                startActivity(alta);
            }catch(SQLException e){
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }
}
