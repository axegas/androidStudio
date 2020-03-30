package com.example.acalculadora3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText num1,num2;
    private TextView res;
    private CheckBox sumar,restar,multi,divi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        res = findViewById(R.id.resultado);
        sumar = findViewById(R.id.sumar);
        restar = findViewById(R.id.restar);
        multi = findViewById(R.id.multiplicar);
        divi = findViewById(R.id.dividir);
    }

    public void calcular(android.view.View v){
        if(!String.valueOf(num1.getText()).equals("") && !String.valueOf(num2.getText()).equals("") ){
            Double a = Double.parseDouble(num1.getText().toString());
            Double b = Double.parseDouble(num2.getText().toString());
            num1.setText("");
            num2.setText("");
            String resultado = "";
            String suma = "";
            String resta = "";
            String multiplicacion = "";
            String division = "";

            if(sumar.isChecked()){
                suma = "La suma es: " + Double.toString(a + b) + "\n";
            }
            if(restar.isChecked()){
                resta = "La resta es: " + Double.toString(a - b) + "\n";
            }
            if(multi.isChecked()){
                multiplicacion = "La multiplicacion es: " + Double.toString(a * b) + "\n";
            }
            if(divi.isChecked()){
                if(b==0){
                    Toast.makeText(this,"No se puede dividir entre 0", Toast.LENGTH_LONG).show();
                }else{
                    division = "La division es: " + Double.toString(a / b) + "\n";
                }
            }

            resultado = suma + resta + multiplicacion + division;
            if(resultado.equals("")){
                resultado = "No has seleccionado ninguna operación";
            }
            res.setText(resultado);

        }else {
            Toast.makeText(this,"Tiene que haber algun valor en los campos 'numero1' y 'numero2'", Toast.LENGTH_LONG).show();
        }
    }
}
