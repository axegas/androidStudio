package com.example.pruebatab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_pendientes extends Fragment {

    ArrayList<tarea> tareas;
    View view;
    private ScrollView scrollView;
    private ViewGroup layout;

    public Fragment_pendientes() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.ver, container, false);

        scrollView = view.findViewById(R.id.scrollviewVer);
        layout = view.findViewById(R.id.contentVer);

        try {
            conectaBD bd = new conectaBD(view.getContext());
            tareas = bd.mostrarTareas(2);
            mostrar();
            bd.close();
        }catch (Exception e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

    public void mostrar(){
        muestraLayouts ml;
        tarea tar;
        Iterator<tarea> iter = tareas.iterator();
        while(iter.hasNext()){
            tar = iter.next();
            ml = new muestraLayouts(tar,R.layout.tarea,scrollView,layout,view.getContext());
            ml.addChild();
        }
    }
}
