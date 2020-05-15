package com.example.myapplicationdfhsd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.*;
import android.widget.Spinner;
import android.widget.TextView;

public class spinner extends AppCompatActivity implements OnItemSelectedListener {

    private String name;
    private TextView vista;
    private Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        vista = (TextView) findViewById(R.id.vista);
        spin = (Spinner) findViewById(R.id.spinner);

        spin.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapter, View view, int position,
                               long id) {
        adapter.getItemAtPosition(position);
        vista.setText(adapter.getItemAtPosition(position).toString());
        // Aquí se codifica la lógica que se ejecutará al seleccionar un elemento del Spinner.
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapter) {

    }
}
