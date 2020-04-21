package com.example.proyinstituto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class altaProfesor extends AppCompatActivity {

    EditText nombre;
    EditText dni;
    EditText edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_profesor);

        nombre = findViewById(R.id.insertNombre);
        dni = findViewById(R.id.insertDNI);
        edad = findViewById(R.id.insertEdadProfesor);
    }

    public void aceptar(View view){
        Intent alta = new Intent(this, MainActivity.class);
        String nom = nombre.getText().toString();
        String DNI = dni.getText().toString();
        String EDAD = edad.getText().toString();

        if (!nom.equals("") && !DNI.equals("") &&!EDAD.equals("")){
            alta.putExtra("nombre",nom);
            alta.putExtra("edad",Integer.parseInt(EDAD));
            alta.putExtra("dni",DNI);
            setResult(Activity.RESULT_OK, alta);
            finish();
        }else{
            Toast.makeText(this,"Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }

    public void salir(View view){

        Intent salir = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_CANCELED, salir);
        finish();
    }
}
