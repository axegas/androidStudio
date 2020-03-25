package com.example.instituto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import java.util.*;
import java.sql.*;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        







    }

    public void verAlumnos(android.view.View v){
        TextView TV = findViewById(R.id.textView);
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
        ArrayList<Examen> examenes = new ArrayList<Examen>();
        try {
            iniciarDatos(profesores,examenes,alumnos,asignaturas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String alum = "holaaaa";
        for(int i=0;i<alumnos.size();i++){
            alum+="reeryeryeyrz";
            alum += alumnos.get(i).toString();
        }/*
        for(int i=0;i<3;i++){
            alum+="reeryeryeyrz";
        }*/
       // alum+="hola";
        TV.setText(alum );

    }

    public static void iniciarDatos(ArrayList<Profesor> profesores,ArrayList<Examen> examenes, ArrayList<Alumno> alumnos,	ArrayList<Asignatura> asignaturas) {
        conexion conec = new conexion();
        Connection cn = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        Statement stm = null;
        Alumno alu;
        Examen ex;
        Asignatura asig;
        Profesor p;
        try {
            cn = conec.conectar();
            stm = cn.createStatement();

            rs = stm.executeQuery("SELECT * FROM profesor");
            while (rs.next()) {
                profesores.add(new Profesor(rs.getString(2), rs.getString(3), rs.getInt(4)));
            }

            rs2 = stm.executeQuery("SELECT * FROM asignatura");
            while (rs2.next()) {
                p = buscaProfesorPorID(rs2.getInt(5), profesores);
                asignaturas.add(new Asignatura(rs2.getString(2), rs2.getInt(3), rs2.getString(4), p));
            }
            rs3 = stm.executeQuery("SELECT * FROM alumno");
            while (rs3.next()) {
                alumnos.add(new Alumno(rs3.getString(2), rs3.getString(3), rs3.getInt(4), rs3.getString(5)));
            }
            rs4 = stm.executeQuery("SELECT * FROM matricula");
            while (rs4.next()) {
                alu = buscaAlumnoPorID(rs4.getInt(1), alumnos);
                asig = buscaAsignaturaPorID(rs4.getInt(2), asignaturas);
                alu.getAsignaturas().add(asig);
            }
            rs5 = stm.executeQuery("SELECT * FROM examen");
            while (rs5.next()) {
                alu = buscaAlumnoPorID(rs5.getInt(2), alumnos);
                asig = buscaAsignaturaPorID(rs5.getInt(3), asignaturas);
                ex = new Examen(alu, asig, rs5.getDouble(4));
                alu.examinar(ex);
                examenes.add(ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (rs3 != null) {
                    rs3.close();
                }
                if (rs4 != null) {
                    rs4.close();
                }
                if (rs5 != null) {
                    rs5.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static Alumno buscaAlumnoPorID(int id, ArrayList<Alumno> alumnos) {
        Alumno alu;
        Iterator<Alumno> iter = alumnos.iterator();
        while (iter.hasNext()) {
            alu = iter.next();
            if (alu.idalu == id) {
                return alu;
            }
        }
        return null;
    }

    public static Asignatura buscaAsignaturaPorID(int id, ArrayList<Asignatura> asignaturas) {
        Asignatura asig;
        Iterator<Asignatura> iter = asignaturas.iterator();
        while (iter.hasNext()) {
            asig = iter.next();
            if (asig.idasig == id) {
                return asig;
            }
        }
        return null;
    }

    public static Profesor buscaProfesorPorID(int id, ArrayList<Profesor> profesores) {
        Profesor prof;
        Iterator<Profesor> iter = profesores.iterator();
        while (iter.hasNext()) {
            prof = iter.next();
            if (prof.idprof == id) {
                return prof;
            }
        }
        return null;
    }
}
