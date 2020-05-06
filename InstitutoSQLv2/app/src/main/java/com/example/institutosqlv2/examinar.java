package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class examinar extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Alumno> alumnos;
    ArrayList<Asignatura> asignaturas;
    EditText nombre;
    EditText dni;
    EditText fecha;
    EditText nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examinar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conectaBD cbd = new conectaBD(getApplicationContext());
        alumnos = cbd.mostrarAlumnos();
        asignaturas = cbd.mostrarAsignaturas();
        cbd.close();



        nombre = findViewById(R.id.insertNombre);
        dni = findViewById(R.id.insertDNI);
        fecha = findViewById(R.id.insertFecha);
        nota = findViewById(R.id.insertNota);

        fecha.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insertFecha:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void aceptar(View view) {

        Intent alta = new Intent(this, MainActivity.class);
        String nom = nombre.getText().toString();
        String DNI = dni.getText().toString();
        String data = fecha.getText().toString();
        double NOTA = Double.parseDouble(nota.getText().toString());

        Alumno alu;
        Asignatura asig;

        if (!nom.equals("") && !DNI.equals("") && !data.equals("")) {
            alu = buscaAlumno(DNI);
            asig = buscaAsignatura(nom);
            if(alu!=null && asig!=null){
                if(!alu.tieneAsig(asig)){
                    try {
                        conectaBD cbd = new conectaBD(getApplicationContext());
                        cbd.examinar(nom,DNI,data,NOTA);
                        cbd.close();
                        setResult(Activity.RESULT_OK, alta);
                        finish();
                    } catch (SQLException e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "El alumno no esta matriculado en esa asignatura." , Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "El alumno o la asignatura no se encuentran.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Falta rellenar algún campo", Toast.LENGTH_LONG).show();
        }
    }

    public Alumno buscaAlumno(String dni) {
        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while (iter.hasNext()) {
            alu = iter.next();
            if (alu.getDNI().equals(dni)) {
                return alu;
            }
        }
        return null;
    }
    public Asignatura buscaAsignatura(String nombre) {
        Asignatura asig;
        Iterator<Asignatura> iter = asignaturas.iterator();
        while (iter.hasNext()) {
            asig = iter.next();
            if (asig.getNombre().equals(nombre)) {
                return asig;
            }
        }
        return null;
    }


}
