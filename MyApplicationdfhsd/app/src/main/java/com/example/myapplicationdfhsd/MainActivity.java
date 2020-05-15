package com.example.myapplicationdfhsd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView vista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vista = (TextView) findViewById(R.id.vista);
    }

    public void ir(View v){
        Intent intent = new Intent(this,spinner.class);
        startActivity(intent);
    }
}
