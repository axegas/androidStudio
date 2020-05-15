package com.example.pruebatab;

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
    public muestraLayouts(tarea tar, int id, ScrollView scrollView, ViewGroup viewGroup, Context context)   {
        this.tar = tar;
        this.id = id;
        this.scrollView = scrollView;
        this.viewGroup = viewGroup;
        this.context = context;
    }

    public void addChild()
    {
        LayoutInflater inflater = LayoutInflater.from(context);

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        TextView asignatura = (TextView) relativeLayout.findViewById(R.id.vistaAsignatura);
        TextView fecha = (TextView) relativeLayout.findViewById(R.id.vistaFecha);
        TextView titulo = (TextView) relativeLayout.findViewById(R.id.vistaTitulo);

        if(!tar.getAsignatura().equals("")){
            asignatura.setText("Asignatura: " + tar.getAsignatura());
        }else {
            asignatura.setText("");
        }
        fecha.setText("Fecha: " + tar.getFechaEntrega());
        titulo.setText("Título: " + tar.getTitulo());

        Button but = relativeLayout.findViewById(R.id.detalle);
        Button estado = relativeLayout.findViewById(R.id.estado);

        if(tar.getEstado().equals("si")) {
            estado.setText("Acabada");
            estado.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else{
            estado.setText("Pendiente");
        }

        estado.setOnClickListener(this);
        but.setOnClickListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.topMargin = 15;

        relativeLayout.setPadding(0, 3, 0, 3);
        relativeLayout.setLayoutParams(params);

        viewGroup.addView(relativeLayout);

        scrollView.post(new Runnable() { public void run() { scrollView.fullScroll(ScrollView.FOCUS_UP); } }  );
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.estado:
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
    private void marcar(String marcar, int id) {
        conectaBD cbd = new conectaBD(context);
        cbd.marcar(marcar,id);
        cbd.close();
    }

    public void detalle(){
        Intent deta = new Intent(context, detalle.class);
        deta.putExtra("tarea", tar);
        context.startActivity(deta);
    }
    public void volver(){
        Intent alta = new Intent(context,MainActivity.class);
        context.startActivity(alta);
    }
}
