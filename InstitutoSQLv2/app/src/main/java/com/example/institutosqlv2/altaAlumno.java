package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class altaAlumno extends AppCompatActivity {

    EditText nombre;
    EditText dni;
    EditText curso;
    EditText edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumno);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = findViewById(R.id.insertNombre);
        dni = findViewById(R.id.insertDNI);
        edad = findViewById(R.id.insertEdad);
        curso = findViewById(R.id.insertCurso);
    }

    public void aceptar(View view){
        Intent alta = new Intent(this, MainActivity.class);
        String nom = nombre.getText().toString();
        String curs = curso.getText().toString();
        String DNI = dni.getText().toString();
        String EDAD = edad.getText().toString();

        if (!nom.equals("") && !curs.equals("") && !DNI.equals("") &&!EDAD.equals("")){
            try{
                conectaBD cbd = new conectaBD(getApplicationContext());
                cbd.agregarAlumno(DNI, nom, Integer.parseInt(EDAD), curs);
                cbd.close();
                setResult(Activity.RESULT_OK, alta);
                finish();
            }catch(SQLException e){
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }


}
