package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.util.*;
import com.google.android.material.tabs.TabLayout;
import org.w3c.dom.Text;

public class Validacion extends AppCompatActivity {

    private TextView verNombre,resultado,total,linea,bingo;
    private ArrayList<TextView> AL = new ArrayList<>();
    private Bolas bolas;
    private Carton carton;
    private boolean cantado;
    private Button marcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion);

        verNombre = findViewById(R.id.verNombre);
        resultado = findViewById(R.id.resultado);
        total = findViewById(R.id.total);
        linea = findViewById(R.id.linea);
        bingo = findViewById(R.id.bingo);
        marcar = findViewById(R.id.BMarcar);

        String nombre = getIntent().getStringExtra("nombre");
        verNombre.setText("Hola " + nombre);

        inicia();
    }

    public void salir(View view){
        Intent salir = new Intent(this, MainActivity.class);
        cantado = false;
        carton.fila = false;
        carton.bingo = false;
        startActivity(salir);
    }

    public void marcar(View view){
        int n = bolas.sacaBola();
        carton.marcar(n);

        int num = Integer.parseInt(total.getText().toString())+1;
        total.setText(Integer.toString(num));

        String res = resultado.getText().toString();
        if(res.length()==0){
            resultado.setText(Integer.toString(n));
       }else{
            resultado.setText(res + " , " + n);
        }

        if(carton.fila&&!cantado){
            Toast.makeText(this,"¡Han cantado línea!", Toast.LENGTH_LONG).show();
            cantado=true;
            linea.setBackgroundColor(0xFF00FF00);
        }
        if(carton.bingo){
            Toast.makeText(this,"¡Han cantado bingo!", Toast.LENGTH_LONG).show();
            bingo.setBackgroundColor(0xFF00FF00);
        }

        if(num==90 || carton.bingo){
            marcar.setEnabled(false);
        }
    }

    public void iniciaCarton(ArrayList<TextView> AL){
        AL.add((TextView) (findViewById(R.id.tv1)));
        AL.add((TextView) (findViewById(R.id.tv2)));
        AL.add((TextView) (findViewById(R.id.tv3)));
        AL.add((TextView) (findViewById(R.id.tv4)));
        AL.add((TextView) (findViewById(R.id.tv5)));
        AL.add((TextView) (findViewById(R.id.tv6)));
        AL.add((TextView) (findViewById(R.id.tv7)));
        AL.add((TextView) (findViewById(R.id.tv8)));
        AL.add((TextView) (findViewById(R.id.tv9)));
        AL.add((TextView) (findViewById(R.id.tv10)));
        AL.add((TextView) (findViewById(R.id.tv11)));
        AL.add((TextView) (findViewById(R.id.tv12)));
        AL.add((TextView) (findViewById(R.id.tv13)));
        AL.add((TextView) (findViewById(R.id.tv14)));
        AL.add((TextView) (findViewById(R.id.tv15)));
        AL.add((TextView) (findViewById(R.id.tv16)));
        AL.add((TextView) (findViewById(R.id.tv17)));
        AL.add((TextView) (findViewById(R.id.tv18)));
        AL.add((TextView) (findViewById(R.id.tv19)));
        AL.add((TextView) (findViewById(R.id.tv20)));
        AL.add((TextView) (findViewById(R.id.tv21)));
        AL.add((TextView) (findViewById(R.id.tv22)));
        AL.add((TextView) (findViewById(R.id.tv23)));
        AL.add((TextView) (findViewById(R.id.tv24)));
        AL.add((TextView) (findViewById(R.id.tv25)));
        AL.add((TextView) (findViewById(R.id.tv26)));
        AL.add((TextView) (findViewById(R.id.tv27)));
    }
    public void inicia(){
        AL.clear();
        iniciaCarton(AL);
        bolas = new Bolas(90);
        carton = new Carton(AL);
        linea.setBackgroundColor(0x00000000);
        bingo.setBackgroundColor(0x00000000);
        cantado = false;
        resultado.setText("");
        total.setText("0");
        marcar.setEnabled(true);
    }
    public void reset(View view){
        inicia();
    }


}
