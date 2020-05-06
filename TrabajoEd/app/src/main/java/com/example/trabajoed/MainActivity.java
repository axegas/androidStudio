package com.example.trabajoed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        conectaBD cbd = new conectaBD(this);
        cbd.borraTabla();
        cbd.close();*/
    }

    public void entrar(View v){
        Intent alta = new Intent(this,tareasTodas.class);
        startActivity(alta);
    }
}
