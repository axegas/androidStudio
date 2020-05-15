package com.example.tareas;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class altaTarea extends AppCompatActivity {

    EditText asignatura;
    EditText descripcion;
    EditText fecha;
    EditText titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_tarea);

        asignatura = findViewById(R.id.insertAsignatura);
        descripcion = findViewById(R.id.insertDescripcion);
        fecha = findViewById(R.id.insertFecha);
        titulo = findViewById(R.id.insertTitulo);

        Button aceptar = findViewById(R.id.aceptar);
        aceptar.setTextColor(getResources().getColor(R.color.white));
    }

    public void aceptar(View v){

        Intent alta = new Intent(this, MainActivity.class);
        String asig = asignatura.getText().toString();
        String desc = descripcion.getText().toString();
        String fech = fecha.getText().toString();
        String tit = titulo.getText().toString();

        if (!asig.equals("") && !desc.equals("")  && !tit.equals("") && !fech.equals("")){
            try{
                conectaBD cbd = new conectaBD(getApplicationContext());
                cbd.agregarTarea(asig, tit,desc,fech);
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
