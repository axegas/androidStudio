package com.example.institutosqlv2;

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
import android.widget.Toast;

public class muestraLayouts {

    int id;
    Object obj;
    ScrollView scrollView;
    ViewGroup viewGroup;
    Context context;

    public muestraLayouts(Object obj, int id, ScrollView scrollView, ViewGroup viewGroup, Context context){
        this.obj = obj;
        this.id = id;
        this.scrollView = scrollView;
        this.viewGroup = viewGroup;
        this.context = context;
    }

    public void addChild()
    {
        LayoutInflater inflater = LayoutInflater.from(context);

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(id, null, false);

        switch (id){
            case R.layout.asignatura:
                Asignatura asig = (Asignatura) obj;
                TextView nombreAsig = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
                TextView libro = (TextView) relativeLayout.findViewById(R.id.vistaLibro);
                TextView profesor = (TextView) relativeLayout.findViewById(R.id.vistaProfesor);
                TextView obligatoria = (TextView) relativeLayout.findViewById(R.id.vistaObligatoria);
                nombreAsig.setText(asig.getNombre());
                libro.setText(asig.getLibro());
                profesor.setText(asig.getProfesor().getNombre());
                obligatoria.setText("Obligatoria: " + asig.getObligatoria());
                break;
            case R.layout.examen:
                Examen ex = (Examen) obj;
                TextView nombreExamen = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
                TextView fecha = (TextView) relativeLayout.findViewById(R.id.vistaFecha);
                TextView nota = (TextView) relativeLayout.findViewById(R.id.vistaNota);
                TextView calificacion = (TextView) relativeLayout.findViewById(R.id.vistaCalificacion);
                nombreExamen.setText(ex.getAsignatura().getNombre());
                fecha.setText(ex.getFecha());
                nota.setText(Double.toString(ex.getNota()));
                calificacion.setText(ex.getCalificacion());
                break;
            case R.layout.alumno:
                final Alumno alu = (Alumno) obj;
                Button vistaVer = (Button) relativeLayout.findViewById(R.id.vistaVer);
                vistaVer.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        ver(alu);
                    }
                });
                TextView nombreAlu = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
                TextView curso = (TextView) relativeLayout.findViewById(R.id.vistaCurso);
                TextView edad = (TextView) relativeLayout.findViewById(R.id.vistaEdad);
                TextView dni = (TextView) relativeLayout.findViewById(R.id.vistaDNI);
                nombreAlu.setText(alu.getNombre());
                curso.setText(alu.getCurso());
                edad.setText(alu.getEdad() + " años.");
                dni.setText(alu.getDNI());
                break;
            case R.layout.profesor:
                Profesor prof = (Profesor) obj;
                TextView nombreProf = (TextView) relativeLayout.findViewById(R.id.vistaNombre);
                TextView edadProf = (TextView) relativeLayout.findViewById(R.id.vistaEdad);
                TextView dniProf = (TextView) relativeLayout.findViewById(R.id.vistaDNI);
                TextView categoria = (TextView) relativeLayout.findViewById(R.id.vistaCategoria);
                nombreProf.setText(prof.getNombre());
                edadProf.setText(prof.getEdad()+" años.");
                dniProf.setText(prof.getDNI());
                categoria.setText(prof.getCategoria());
                break;
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        params.topMargin = 15;

        relativeLayout.setPadding(5, 3, 5, 3);
        relativeLayout.setLayoutParams(params);

        viewGroup.addView(relativeLayout);

        scrollView.post(new Runnable() { public void run() { scrollView.fullScroll(ScrollView.FOCUS_UP); } }  );
    }
    private void ver(Alumno alu){
        Intent ver = new Intent(context,vistaAlumno.class);
        ver.putExtra("alumno",alu);
        context.startActivity(ver);
    }
}
