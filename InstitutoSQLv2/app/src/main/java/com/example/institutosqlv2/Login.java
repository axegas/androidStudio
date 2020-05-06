package com.example.institutosqlv2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private final String USUARIO = "administrador";
    private final String PASSWORD = "12345";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void inicia(View v){
        TextView user = findViewById(R.id.usuario);
        TextView pass = findViewById(R.id.password);
        if(!user.getText().toString().equals("")&&!pass.getText().toString().equals("")){
            if(user.getText().toString().equals(USUARIO)&&pass.getText().toString().equals(PASSWORD)){
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
            }else{
                Toast.makeText(this,"Usuario o Password incorrectos", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"Alguno de los campos está vacío.", Toast.LENGTH_LONG).show();
        }
    }
    public void primera(View v){
        conectaBD bd = new conectaBD(getApplicationContext());
        bd.inicial();
        bd.close();
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
