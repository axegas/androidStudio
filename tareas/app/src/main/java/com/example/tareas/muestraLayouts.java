package com.example.tareas;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        TextView titulo = (TextView) relativeLayout.findViewById(R.id.vistaTitulo);

        asignatura.setText("Asignatura: " + tar.getAsignatura());
        fecha.setText("Fecha: " + tar.getFechaEntrega());
        titulo.setText("Título: " + tar.getTitulo());

        ToggleButton toggleButton = relativeLayout.findViewById(R.id.toggleButton);
        if(tar.getEstado().equals("si")){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }

        Button but = relativeLayout.findViewById(R.id.detalle);
        toggleButton.setOnClickListener(this);
        but.setOnClickListener(this);


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
        switch (v.getId()){
            case R.id.toggleButton:
                if (tar.getEstado().equals("no")) {
                    marcar("si", tar.getId());
                    tar.realizar();
                } else {
                    marcar("no", tar.getId());
                    tar.noRealizar();
                }
                volver();
                break;
            case R.id.detalle:
                detalle();
                break;
        }

    }
    public void volver(){
        Intent alta = new Intent(context, clase);
        context.startActivity(alta);
    }
    public void detalle(){
        Intent deta = new Intent(context, detalle.class);
        deta.putExtra("tarea", tar);
        context.startActivity(deta);
    }
}
