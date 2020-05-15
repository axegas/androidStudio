package com.example.pruebatab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    private TabLayout tabLayout;
    FloatingActionButton fab;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);



        tabLayout.setupWithViewPager(viewPager);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        /*
        try {
            conectaBD bd = new conectaBD(this);
            bd.borraTabla();
            bd.close();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }*/

        crearAdaptador();

    }

    private void crearAdaptador() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_todas(), "Todas");
        adapter.addFragment(new Fragment_pendientes(),"Pendientes");
        adapter.addFragment(new Fragment_acabadas(), "Acabadas");
        viewPager.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        Intent alta;
        alta = new Intent(this, altaTarea.class);
        startActivity(alta);
    }
}
