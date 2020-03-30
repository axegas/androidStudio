package com.example.acalculadora2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText num1,num2;
    private TextView resultado;
    private RadioButton sumar,restar,multi,divi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        resultado = findViewById(R.id.resultado);
        sumar = findViewById(R.id.sumar);
        restar = findViewById(R.id.restar);
        multi = findViewById(R.id.multiplicar);
        divi = findViewById(R.id.dividir);
    }

    public void calcular(android.view.View v){

        if(!String.valueOf(num1.getText()).equals("") && !String.valueOf(num2.getText()).equals("") ){
            Double a = Double.parseDouble(num1.getText().toString());
            Double b = Double.parseDouble(num2.getText().toString());
            String c="No has seleccionado ninguna operación";
            num1.setText("");
            num2.setText("");

            if(sumar.isChecked()) {
                c = Double.toString(a + b);
            }else if(restar.isChecked()){
                c = Double.toString(a - b);
            }else if(multi.isChecked()){
                c = Double.toString(a * b);
            }else if(divi.isChecked()){
                if(b==0){
                    Toast.makeText(this,"No se puede dividir entre 0", Toast.LENGTH_LONG).show();
                }else{
                    c =  Double.toString(a / b);
                }

            }
            resultado.setText(c);
        }else {
            Toast.makeText(this,"Tiene que haber algun valor en los campos 'numero1' y 'numero2'", Toast.LENGTH_LONG).show();
        }

    }
}
