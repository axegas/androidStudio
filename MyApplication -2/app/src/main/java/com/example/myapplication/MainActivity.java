package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private TextView TV;
    private EditText num1;
    private EditText num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV = findViewById(R.id.resultado);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
    }

    public void clickButton2(android.view.View v){
        double c=0;
        if(!String.valueOf(num1.getText()).equals("") && !String.valueOf(num2.getText()).equals("") ){
            double a = Double.parseDouble(String.valueOf(num1.getText()));
            double b = Double.parseDouble(String.valueOf(num2.getText()));
            num1.setText("");
            num2.setText("");
            switch(v.getId()){
                case R.id.bSuma:
                    c = a+b;
                    break;
                case R.id.bResta:
                    c = a-b;
                    break;
                case R.id.bMult:
                    c = a*b;
                    break;
                case R.id.bDiv:
                    c = a/b;
                    break;
                default:
                    break;
            }
            TV.setText(Double.toString(c) );
        }
    }
}
