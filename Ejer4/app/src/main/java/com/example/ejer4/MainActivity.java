package com.example.ejer4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        password = findViewById(R.id.pass);
    }

    public void Validar(View view){
       if( nombre.length()==0){
            Toast.makeText(this,"Tiene que haber algun valor en el campo 'nombre'", Toast.LENGTH_LONG).show();
        }
        if( password.length()==0){
            Toast.makeText(this,"Tiene que haber algun valor en el campo 'password'", Toast.LENGTH_LONG).show();
        }
        if( nombre.length()!=0 && password.length()!=0){

        }

    }


}
