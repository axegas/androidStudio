package com.example.trabajoed;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class muestraLayouts implements View.OnClickListener   {

    int id;
    tarea tar;
    ScrollView scrollView;
    ViewGroup viewGroup;
    Context context;
    Class clase;

    public muestraLayouts(tarea tar, int id, ScrollView scrollView, ViewGroup viewGroup, Context context, Class clase)   {
        this.tar = tar;
        this.id = id;
        this.scrollView = scrollView;
        this.viewGroup = viewGroup;
        this.context = context;
        this.clase = clase;

    }

    public void addChild()
    {
        LayoutInflater inflater = LayoutInflater.from(context);

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView asignatura = (TextView) relativeLayout.findViewById(R.id.vistaAsignatura);
        TextView fecha = (TextView) relativeLayout.findViewById(R.id.vistaFecha);
        TextView descripcion = (TextView) relativeLayout.findViewById(R.id.vistaDescripcion);

        asignatura.setText("Asignatura: " + tar.getAsignatura());
        fecha.setText("Fecha: " + tar.getFechaEntrega());
        descripcion.setText("Descripción: " + tar.getDescripcion());

        ToggleButton toggleButton = relativeLayout.findViewById(R.id.toggleButton);
        if(tar.getEstado().equals("si")){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }

        toggleButton.setOnClickListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.topMargin = 15;

        relativeLayout.setPadding(5, 3, 5, 3);
        relativeLayout.setLayoutParams(params);

        viewGroup.addView(relativeLayout);

        scrollView.post(new Runnable() { public void run() { scrollView.fullScroll(ScrollView.FOCUS_UP); } }  );
    }

    private void marcar(String marcar, int id) {
        conectaBD cbd = new conectaBD(context);
        cbd.marcar(marcar,id);
        cbd.close();
    }

    @Override
    public void onClick(View v) {
        if (tar.getEstado().equals("no")) {
            marcar("si", tar.getId());
            tar.realizar();
        } else {
            marcar("no", tar.getId());
            tar.noRealizar();
        }
        volver();
    }
    public void volver(){
        Intent alta = new Intent(context, clase);
        context.startActivity(alta);
    }
}
