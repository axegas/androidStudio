package com.example.pruebatab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class detalle extends AppCompatActivity implements View.OnClickListener {

    AlertDialog.Builder confirmacion;
    tarea tar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        tar = (tarea) getIntent().getSerializableExtra("tarea");

        TextView asignatura = (TextView) findViewById(R.id.vistaAsignatura);
        TextView fecha = (TextView) findViewById(R.id.vistaFecha);
        TextView titulo = (TextView) findViewById(R.id.vistaTitulo);
        TextView descripcion = (TextView) findViewById(R.id.vistaDescripcion);

        if(!tar.getAsignatura().equals("")){
            asignatura.setText("Asignatura: " + tar.getAsignatura());
        }else {
            asignatura.setText("");
        }

        fecha.setText("Fecha: " + tar.getFechaEntrega());
        titulo.setText("Título: " + tar.getTitulo());
        descripcion.setText("Descripcion:\n" + tar.getDescripcion());

        TextView estado = (TextView) findViewById(R.id.estado);

        if(tar.getEstado().equals("si")){
            estado.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            estado.setText("ACABADA");
        }else{
            estado.setBackgroundColor(getResources().getColor(R.color.red));
            estado.setTextColor(getResources().getColor(R.color.white));
        }

        Button borrar = findViewById(R.id.borrar);

        borrar.setOnClickListener(this);
        borrar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        borrar.setTextColor(getResources().getColor(R.color.white));

        popUpConfirmacion();
    }

    @Override
    public void onClick(View v) {
        confirmacion.show();
    }
    public void volver(){
        Intent alta = new Intent(this, MainActivity.class);
        startActivity(alta);
    }
    public void popUpConfirmacion(){
        confirmacion = new AlertDialog.Builder(this);
        confirmacion.setTitle("Importante");
        confirmacion.setMessage("¿ Está seguro de borrar la tarea ?");
        confirmacion.setCancelable(false);
        confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        confirmacion.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
    }
    public void aceptar(){
        try {
            conectaBD bd = new conectaBD(this);
            bd.borraTarea(tar.getId());
            bd.close();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
        volver();
    }
}
